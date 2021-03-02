package com.kustudents.issuetracker.config;

import com.auth0.jwt.interfaces.Claim;
import com.kustudents.issuetracker.service.AuthenticationService;
import com.kustudents.issuetracker.utility.TokenConstants;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final AuthenticationService authenticationService;
    Logger log = LogManager.getLogger(JwtAuthorizationFilter.class);


    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, AuthenticationService authenticationService) {
        super(authenticationManager);
        this.authenticationService = authenticationService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        var authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith(TokenConstants.TOKEN_PREFIX)) {
            try {
                var decodedJWT = authenticationService.decodeToken(authorizationHeader.substring(TokenConstants.TOKEN_PREFIX.length()));
                Claim login = decodedJWT.getClaim("login");
                var userDetails = authenticationService.loadUserDetails(login.asString());
                var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        null
                );

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // After setting the Authentication in the context, we specify
                // that the current user is authenticated. So it passes the
                // Spring Security Configurations successfully.
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } catch (Exception e) {
                log.info("Failed to authorize user: {}", e.getMessage());
           }
       }
       chain.doFilter(request, response);
   }

}
