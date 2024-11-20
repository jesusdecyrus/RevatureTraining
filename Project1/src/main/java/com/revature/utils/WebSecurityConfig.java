package com.revature.utils;

import com.revature.daos.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Class that handles Spring Security such as endpoints access and password encoder
 */
@Configuration //This tells Spring that this class contains configuration bean definitions
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    /** UserDAO */
    private final UserDAO userDAO;

    /** JWT Token Validation */
    private final JwtTokenFilter jwtTokenFilter;

    /** Spring Security Authentication */
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    /**
     * WebSecurityConfig Constructor
     * @param userDAO the UserDAO
     * @param jwtTokenFilter the JWT token validation
     * @param authenticationManagerBuilder the Spring Security authentication
     */
    @Autowired
    public WebSecurityConfig(UserDAO userDAO, JwtTokenFilter jwtTokenFilter, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.userDAO = userDAO;
        this.jwtTokenFilter = jwtTokenFilter;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    /**
     * Configures authentication manager before logging the user
     * @param authentication the Spring Security authentication
     * @throws Exception when username is not found
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authentication) throws Exception {
        authentication.userDetailsService(username -> {
            if(userDAO.findByUsername(username) == null){
                throw new UsernameNotFoundException("User " + username + " not found.");
            } else {
                return userDAO.findByUsername(username);
            }
        });
    }

    /**
     * Returns an authentication manager for login
     * @param config the authentication configuration
     * @return the authentication manager
     * @throws Exception when unable to get authentication manager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Encrypt passwords and sensitive information
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configure security filters, session management, and URL privileges
     * @param http the http security
     * @return SecurityFilterChain
     * @throws Exception when encountering problems
     */
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http.csrf(c -> c.disable())
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers(new AntPathRequestMatcher("/users/**", HttpMethod.POST.toString())).permitAll() // POST "/users" accessible to anyone
                                .requestMatchers("/auth/**").permitAll() // all request to "/auth"
                                .requestMatchers(new AntPathRequestMatcher("/users/**")).hasAuthority("Manager") // only manager can access "/users"
                                .anyRequest().authenticated() // any authenticated can access any other endpoint
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}