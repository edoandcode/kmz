package com.edoardoconti.kmz_backend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    // Custom implementation of UserDetailsService injected
    private final UserDetailsService userDetailsService;

    /**
     * Password encoder bean used by Spring Security.
     * BCrypt is the recommended encoder for hashing user passwords.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * AuthenticationManager is the main entry point for authentication.
     * It uses UserDetailsService + PasswordEncoder automatically.
     * AuthenticationConfiguration is a factory that provides the configured AuthenticationManager.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    /**
     * Defines the global security filter chain.
     * This controls how HTTP requests are authorized and how sessions are handled.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // Use stateless sessions (suitable for JWT or token-based authentication)
                .sessionManagement(c ->
                        c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Disable CSRF protection (commonly disabled for stateless APIs)
                .csrf(AbstractHttpConfigurer::disable)
                // Allow iframes only for H2 console (for development only)
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                // Define which endpoints are public and which require authentication
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(HttpMethod.POST, "/users").permitAll();       // allow user registration
                    auth.requestMatchers(HttpMethod.POST, "/auth/login").permitAll(); // allow login
                    auth.requestMatchers("/h2-console", "/h2-console/**").permitAll();// allow H2 console access
                    auth.anyRequest().authenticated(); // all other endpoints require authentication
                });

        return http.build();
    }

}
