package com.revature.utils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.revature.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * Ensures every request has a JWT in the Authorization header.
 * It extends OncePerRequestFilter to guarantee single execution per request.
 */
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    /** JwtTokenUtil */
    private final JwtTokenUtil jwtUtil;

    /**
     * JwtTokenFilter Constructor
     * @param jwtUtil the JWT token util
     */
    @Autowired
    public JwtTokenFilter(JwtTokenUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    /**
     * Checks if the request has a valid JWT in the Authorization header
     * @param request the HTTP request
     * @param response the HTTP response
     * @param filterChain the filter chain
     * @throws ServletException the servlet exception
     * @throws IOException when there is an IO problem
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // If the Authorization header of the request doesn’t contain a Bearer token
        // Run the filter without doing anything else (The request will fail later if JWT is needed)
        if (!hasAuthorizationBearer(request)) {
            filterChain.doFilter(request, response);
            System.out.println("Missing a Bearer token");
            return;
        }

        // Extract the Bearer token
        String token = getAccessToken(request);

        // If the token is not valid (expired, malformed, etc.)
        // Run the filter without doing anything else (The request will fail later if JWT is needed)
        if (!jwtUtil.validateAccessToken(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        //Update the authentication context with the user details then continue the filter chain (The request will pass through)
        setAuthenticationContext(token, request);
        filterChain.doFilter(request, response);
    }

    /**
     * Checks for Authorization header in the HTTP request
     * @param request the HTTP request
     * @return true when it contains the Authorization header; otherwise, false
     */
    private boolean hasAuthorizationBearer(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        return !ObjectUtils.isEmpty(header) && header.startsWith("Bearer");
    }

    /**
     * Extracts the JWT from the Authorization header
     * @param request the HTTP request
     * @return the JWT token
     */
    private String getAccessToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        return header.split(" ")[1].trim();
    }

    /**
     * Extracts user details from the JWT token, creates an Authentication object with those
     * details, and sets the Authentication object in the security context
     * @param token the JWT token
     * @param request the HTTP request
     */
    private void setAuthenticationContext(String token, HttpServletRequest request) {
        // Extracts user details from the JWT token
        User user = (User) getUserDetails(token);
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole()));

        // Creates an Authentication object with user details
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                user,
                null,
                authorities);

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        // Sets the Authentication object in the security context
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    //This is used to extract the userId, username, and role from the JWT in the method above

    /**
     * Used to extract user id, username, and role from the JWT token
     * @param token the JWT token
     * @return the user details
     */
    private UserDetails getUserDetails(String token) {
        User userDetails = new User();

        // Use JwtTokenUtil to extract user details
        userDetails.setUserId(jwtUtil.extractUserId(token));
        userDetails.setUsername(jwtUtil.extractUsername(token));
        userDetails.setRole(jwtUtil.extractRole(token));

        System.out.println("User: " + userDetails);
        System.out.println("Role: " + userDetails.getRole());

        return userDetails;
    }

}