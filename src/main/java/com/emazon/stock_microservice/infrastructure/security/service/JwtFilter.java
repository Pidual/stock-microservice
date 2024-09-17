package com.emazon.stock_microservice.infrastructure.security.service;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtFilter(final JwtService jwtService) {
        this.jwtService = jwtService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String jwtToken = null;
        String username = null;

        // Check if Authorization header has Bearer token
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwtToken = authHeader.substring(7);
            username = jwtService.extractUsername(jwtToken);  // Extract username from JWT
        }

        // If username is extracted and there is no authentication already in the security context
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Validate the token
            if (jwtService.validateToken(jwtToken)) {
                // Extract authorities (roles) from the token
                List<GrantedAuthority> authorities = jwtService.extractAuthorities(jwtToken);

                // Create UsernamePasswordAuthenticationToken with authorities (credentials are null)
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(username, null, authorities);

                // Set authentication in the security context
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Proceed with the filter chain
        filterChain.doFilter(request, response);
    }
}