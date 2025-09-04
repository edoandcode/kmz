package com.edoardoconti.kmz_backend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
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
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    // Custom UserDetailsService implementation (used to load user data from DB or other source)
    private final UserDetailsService userDetailsService;

    // Custom JWT filter that validates tokens before reaching UsernamePasswordAuthenticationFilter
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * Defines a PasswordEncoder bean for hashing passwords.
     * BCrypt is strong and adaptive, recommended by Spring Security.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Exposes AuthenticationManager as a bean.
     * It delegates authentication to configured providers
     * (UserDetailsService + PasswordEncoder by default).
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    /**
     * Defines the security filter chain for HTTP requests.
     * Configures session policy, CSRF, headers, authorization rules,
     * and integrates the custom JWT authentication filter.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // Stateless: no HTTP session will be created, JWT will be used instead
                .sessionManagement(c ->
                        c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Disable CSRF as it is unnecessary for stateless REST APIs
                .csrf(AbstractHttpConfigurer::disable)

                // Allow H2 console (development only) by disabling frame options
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))

                // Authorization rules for endpoints
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(HttpMethod.POST, "/users").permitAll();       // allow user registration
                    auth.requestMatchers(HttpMethod.POST, "/auth/login").permitAll(); // allow login
                    auth.requestMatchers(HttpMethod.POST, "/auth/refresh").permitAll(); // allow refresh jwt token
                    auth.requestMatchers("/h2-console", "/h2-console/**").permitAll();// allow H2 console
                    auth.anyRequest().authenticated(); // all other endpoints require authentication
                })

                // Insert JWT filter before Spring's default username/password filter
                // This ensures token validation happens early in the filter chain
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(c -> c
                        .authenticationEntryPoint(
                                new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)
                        )
                );

        // Return the built SecurityFilterChain
        return http.build();
    }

}
