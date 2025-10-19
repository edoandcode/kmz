package com.edoardoconti.kmz_backend.security;

import com.edoardoconti.kmz_backend.role.UserRole;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    // === Dependencies ===
    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    // === Bean Definitions ===

    /**
     * Password encoder using BCrypt (adaptive and secure).
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Exposes the authentication manager.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    /**
     * Defines the main security filter chain for HTTP requests.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // --- Session & CSRF ---
                .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)

                // --- Headers ---
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)) // Allow H2 console

                // --- Authorization Rules ---
                .authorizeHttpRequests(auth -> {

                    // ==========================
                    // 🔐 AUTHENTICATION ROUTES
                    // ==========================
                    auth.requestMatchers(HttpMethod.POST, "/auth/login").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/auth/refresh").permitAll();

                    // ==========================
                    // 👤 USER ROUTES
                    // ==========================
                    // Setup first admin (public)
                    auth.requestMatchers(HttpMethod.POST, "/users/setup-admin").permitAll();

                    // Public registration
                    auth.requestMatchers(HttpMethod.POST, "/users").permitAll();

                    // Restricted access to user list and profiles
                    auth.requestMatchers(HttpMethod.GET, "/users/**")
                            .hasAnyRole(UserRole.ADMINISTRATOR.name(), UserRole.FACILITATOR.name());

                    // ==========================
                    // 📨 REQUEST ROUTES
                    // ==========================
                    // User registration requests
                    auth.requestMatchers("/requests/users/registration")
                            .hasRole(UserRole.ADMINISTRATOR.name());
                    auth.requestMatchers("/requests/users/registration/me")
                            .hasRole(UserRole.GENERIC_USER.name());

                    // Content publication requests
                    auth.requestMatchers(HttpMethod.POST, "/requests/contents/publications")
                            .hasAnyRole(UserRole.ADMINISTRATOR.name(), UserRole.CURATOR.name());

                    // Producers / processors / facilitators can request publication
                    auth.requestMatchers(HttpMethod.POST, "/requests/contents/publish/**")
                            .hasAnyRole(UserRole.PRODUCER.name(), UserRole.PROCESSOR.name(), UserRole.FACILITATOR.name());

                    // Curators can approve/reject publication requests
                    auth.requestMatchers(HttpMethod.POST, "/requests/contents/approve/**", "/requests/contents/reject/**")
                            .hasRole(UserRole.CURATOR.name());

                    // Event participation requests
                    auth.requestMatchers(HttpMethod.GET, "/requests/events/participation")
                            .hasRole(UserRole.ADMINISTRATOR.name());

                    // Event invitation / acceptance / rejection
                    auth.requestMatchers(HttpMethod.POST, "/requests/events/invite/**")
                            .hasRole(UserRole.FACILITATOR.name());
                    auth.requestMatchers(HttpMethod.POST, "/requests/events/accept/**")
                            .hasRole(UserRole.GENERIC_USER.name());
                    auth.requestMatchers(HttpMethod.POST, "/requests/events/reject/**")
                            .hasRole(UserRole.GENERIC_USER.name());

                    // ==========================
                    // 🏪 PRODUCT ROUTES
                    // ==========================
                    auth.requestMatchers(HttpMethod.GET, "/products")
                            .hasAnyRole(UserRole.CURATOR.name(), UserRole.ADMINISTRATOR.name());
                    auth.requestMatchers(HttpMethod.GET, "/products/public")
                            .hasAnyRole(UserRole.PROCESSOR.name(), UserRole.ADMINISTRATOR.name());
                    auth.requestMatchers(HttpMethod.GET, "/products/me/**")
                            .hasRole(UserRole.PRODUCER.name());
                    auth.requestMatchers(HttpMethod.POST, "/products")
                            .hasRole(UserRole.PRODUCER.name());
                    auth.requestMatchers(HttpMethod.DELETE, "/products/**")
                            .hasRole(UserRole.PRODUCER.name());

                    // ==========================
                    // ⚙️ PROCESS ROUTES
                    // ==========================
                    auth.requestMatchers(HttpMethod.GET, "/processes")
                            .hasAnyRole(UserRole.CURATOR.name(), UserRole.ADMINISTRATOR.name());
                    auth.requestMatchers(HttpMethod.GET, "/processes/me/**")
                            .hasRole(UserRole.PROCESSOR.name());
                    auth.requestMatchers(HttpMethod.POST, "/processes")
                            .hasRole(UserRole.PROCESSOR.name());

                    // ==========================
                    // 🎟️ EVENT ROUTES
                    // ==========================
                    auth.requestMatchers(HttpMethod.GET, "/events")
                            .hasAnyRole(UserRole.CURATOR.name(), UserRole.ADMINISTRATOR.name());
                    auth.requestMatchers(HttpMethod.GET, "/events/me/**")
                            .hasRole(UserRole.FACILITATOR.name());
                    auth.requestMatchers(HttpMethod.POST, "/events")
                            .hasRole(UserRole.FACILITATOR.name());

                    // ==========================
                    // 🌍 PUBLIC ROUTES
                    // ==========================
                    auth.requestMatchers(HttpMethod.GET, "/public/contents/**").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/system/status").permitAll();

                    // ==========================
                    // 🧩 DEV & DOCS
                    // ==========================
                    // H2 console (for dev only)
                    auth.requestMatchers("/h2-console", "/h2-console/**").permitAll();

                    // Swagger / OpenAPI docs
                    auth.requestMatchers(
                            "/v3/api-docs/**",
                            "/swagger-ui/**",
                            "/swagger-ui.html",
                            "/swagger-ui/index.html"
                    ).permitAll();

                    // ==========================
                    // 🔒 DEFAULT RULE
                    // ==========================
                    auth.anyRequest().authenticated();
                })

                // --- JWT Filter Integration ---
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

                // --- Exception Handling ---
                .exceptionHandling(c -> {
                    c.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
                    c.accessDeniedHandler((HttpServletRequest req, HttpServletResponse res, AccessDeniedException ex) ->
                            res.setStatus(HttpStatus.FORBIDDEN.value()));
                });

        return http.build();
    }

    /**
     * Configures CORS to allow the frontend (e.g., localhost:3000).
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
