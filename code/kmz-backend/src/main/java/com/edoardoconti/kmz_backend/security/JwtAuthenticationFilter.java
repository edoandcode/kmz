package com.edoardoconti.kmz_backend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // Service responsible for extracting and validating JWT tokens
    private final JwtService jwtService;

    /**
     * Custom authentication logic executed for each request.
     * Intercepts the HTTP request, checks for a valid JWT token,
     * and sets authentication in the SecurityContext if valid.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain ) throws ServletException, IOException {

        // Extract "Authorization" header from the request
        var authHeader = request.getHeader("Authorization");

        // If no header or it does not start with "Bearer", skip and continue filter chain
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extract token from header (remove "Bearer " prefix)
        var token = authHeader.replace("Bearer ", "");

        // Validate token: if invalid, skip and continue filter chain
        if(!this.jwtService.validateToken(token)){
            filterChain.doFilter(request, response);
            return;
        }


        var userId = this.jwtService.getUserIdFromToken(token);
        var grantedAuthorities = this.jwtService.getRolesFromToken(token).stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.name()))
                .toList();

        /**
         * If the token is valid:
         * - Create an Authentication object with user identity (userId from token)
         * - Authorities/roles are set to null here (could be extracted from token)
         */
        var authentication = new UsernamePasswordAuthenticationToken(
                userId, // principal (user identity)
                null,                                     // no credentials (JWT already validated)
                grantedAuthorities                        // no authorities (can be extended)
        );

        // Attach request details (IP, session ID, etc.)
        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        // Store authentication in the SecurityContext (used by Spring Security)
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Continue request processing
        filterChain.doFilter(request, response);
    }
}
