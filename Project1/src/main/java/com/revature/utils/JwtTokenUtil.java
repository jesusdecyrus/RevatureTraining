package com.revature.utils;

import com.revature.models.User;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * Generates, manages, and validates our JWTs
 * JSW (JSON Web Token) to store a user's information and confirm their identity
 */
@Component
public class JwtTokenUtil {

    /** 24 Hours Lifetime for JWT */
    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000;

    /** From application.properties */
    @Value("${app.jwt.secret}") //taken out of application.properties
    private String SECRET_KEY;

    /** Logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);

    /**
     * Verifies a given JWT and returns true if the JWT is verified; otherwise, false
     * @param token the JWT token
     * @return true if the JWT is verified; otherwise, false
     */
    public boolean validateAccessToken(String token) {
        System.out.println("Validating Access Token...");
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException ex) {
            LOGGER.error("Expired JWT", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            LOGGER.error("Null, Empty or Whitespace JWT", ex.getMessage());
        } catch (MalformedJwtException ex) {
            LOGGER.error("Malformed JWT", ex);
        } catch (UnsupportedJwtException ex) {
            LOGGER.error("Unsupported JWT", ex);
        } catch (SignatureException ex) {
            LOGGER.error("Signature Validation Failed");
        }

        return false;
    }

    /**
     * Creates the user's unique identifier token
     * @param u the user
     * @return the JWT token string
     */
    public String generateAccessToken(User u) {
        return Jwts.builder()
                .setSubject(String.format("%s", u.getUserId())) //subject is typically ID
                .claim("username", u.getUsername()) //any other data can be set as a claim
                .claim("role", u.getRole())
                .setIssuer("Project1")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    /**
     * Extracts the user id
     * @param token the JWT token
     * @return the UUID
     */
    public int extractUserId(String token) {
        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
        String subject = claimsJws.getBody().getSubject();
        return Integer.parseInt(subject);
    }

    /**
     * Extracts the username
     * @param token the JWT token
     * @return the username string
     */
    public String extractUsername(String token) {
        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
        return claimsJws.getBody().get("username", String.class);
    }

    /**
     * Extracts the role
     * @param token the JWT token
     * @return the role string
     */
    public String extractRole(String token) {
        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
        return claimsJws.getBody().get("role", String.class);
    }

}