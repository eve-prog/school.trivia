package com.tim04.school.trivia.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class UserNamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private static final String BEARER = "Bearer";
    private static final Logger LOGGER = LoggerFactory.getLogger(UserNamePasswordAuthenticationFilter.class);

    private DatabaseUserDetailsService databaseUserDetailsService;

    protected UserNamePasswordAuthenticationFilter( DatabaseUserDetailsService databaseUserDetailsService) {
        this.databaseUserDetailsService = databaseUserDetailsService;
    }

    /**
     * Determine if there is a JWT as part of the HTTP Request Header.
     * If it is valid then set the current context With the Authentication(user and roles) found in the token
     *
     * @param req ServletRequest
     * @param res ServletResponse
     * @param filterChain Filter Chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter( ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {
        //Check for Authorization:Bearer JWT
        LOGGER.info("Process request to check for a JSON Web Token ");
        String headerValue = ((HttpServletRequest)req).getHeader("Authorization");
        getBearerToken(headerValue).ifPresent(token-> {
            //Pull the Username and Roles from the JWT to construct the user details
            databaseUserDetailsService.loadUserByJwtToken(token).ifPresent(userDetails -> {
                //Add the user details (Permissions) to the Context for just this API invocation
                SecurityContextHolder.getContext().setAuthentication(
                        new PreAuthenticatedAuthenticationToken(userDetails, "", new ArrayList<>()));
            });
        });

        //move on to the next filter in the chains
        filterChain.doFilter(req, res);
    }

    /**
     * if present, extract the jwt token from the "Bearer <jwt>" header value.
     *
     * @param headerVal
     * @return jwt if present, empty otherwise
     */
    private Optional<String> getBearerToken( String headerVal) {
        if (headerVal != null && headerVal.startsWith(BEARER)) {
            return Optional.of(headerVal.replace(BEARER, "").trim());
        }
        return Optional.empty();
    }

}

