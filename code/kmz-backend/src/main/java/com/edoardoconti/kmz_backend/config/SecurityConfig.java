package com.edoardoconti.kmz_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Global filter chain for all other requests.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // We need to tell Spring to use stateless session (token-based authentication)
            .sessionManagement( c ->
                    c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // Disable CSRF (Cross Site Request Forgery) (more: https://it.wikipedia.org/wiki/Cross-site_request_forgery)
            .csrf(AbstractHttpConfigurer::disable)
             // permit iframes only for visualize h2-console (must be removed in production environment)
            .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
            // Authorize a bunch of http requests, we define which endpoint are public or private
            .authorizeHttpRequests(auth -> {
                // permit post requests to /users endpoint
                auth.requestMatchers(HttpMethod.POST, "/users").permitAll();
                // permit post requests to /auth/login endpoint
                auth.requestMatchers(HttpMethod.POST, "/auth/login").permitAll();
                // permit access to h2-console endpoint
                auth.requestMatchers("/h2-console", "/h2-console/**").permitAll();
                // any other requests must be authenticated
                auth.anyRequest().authenticated();
            });
        return http.build();
    }

}
