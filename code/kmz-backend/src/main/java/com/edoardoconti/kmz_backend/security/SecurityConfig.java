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
                    // Allow setup route for first admin creation
                    auth.requestMatchers(HttpMethod.POST, "/users/setup-admin").permitAll();

                    // Restrict all /admin/** routes to administrators only
                    auth.requestMatchers("/requests/users/**")
                            .hasRole(UserRole.ADMINISTRATOR.name());

                    // POST /requests/contents/publish/** : Only PRODUCER, PROCESSOR, FACILITATOR can request publication of a content
                    auth.requestMatchers(HttpMethod.POST, "/requests/contents/publish/**")
                            .hasAnyRole(UserRole.PRODUCER.name(), UserRole.PROCESSOR.name(), UserRole.FACILITATOR.name());

                    // POST /requests/contents/approve/** and /requests/contents/reject/** : Only CURATOR can approve or reject content publication requests
                    auth.requestMatchers(HttpMethod.POST, "/requests/contents/approve/**",
                            "/requests/contents/reject/**")
                            .hasAnyRole(UserRole.CURATOR.name());

                    // User registration is open
                    auth.requestMatchers(HttpMethod.POST, "/users").permitAll();

                    // GET /users/** : only ADMINISTRATOR can visualize registered users
                    auth.requestMatchers(HttpMethod.GET, "/users/**")
                            .hasRole(UserRole.ADMINISTRATOR.name());

                    // Login & refresh endpoints are public
                    auth.requestMatchers(HttpMethod.POST, "/auth/login").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/auth/refresh").permitAll();

                    // H2 console for development
                    auth.requestMatchers("/h2-console", "/h2-console/**").permitAll();

                    // Permits Swagger UI
                    auth.requestMatchers( "/v3/api-docs/**",
                            "/swagger-ui/**",
                            "/swagger-ui.html",
                            "/swagger-ui/index.html"
                    ).permitAll();

                    // --------- CONTENT ENDPOINTS ---------
                    // Products
                    // - GET /products only CURATOR and ADMINISTRATOR can access all events created
                    auth.requestMatchers(HttpMethod.GET, "/products")
                            .hasAnyRole(UserRole.CURATOR.name(), UserRole.ADMINISTRATOR.name());

                    // - GET /products/me/** accessible by PRODUCER to view their own products
                    auth.requestMatchers(HttpMethod.GET, "/products/me/**")
                            .hasAnyRole(UserRole.PRODUCER.name());

                    // - POST /products: only PRODUCER can upload products
                    auth.requestMatchers(HttpMethod.POST, "/products")
                            .hasRole(UserRole.PRODUCER.name());

                    // Processes
                    // - GET /processes only CURATOR and ADMINISTRATOR can access all events created
                    auth.requestMatchers(HttpMethod.GET, "/processes")
                            .hasAnyRole(UserRole.CURATOR.name(), UserRole.ADMINISTRATOR.name());

                    // - GET /processes/me/** accessible by PROCESSOR to view their own processes
                    auth.requestMatchers(HttpMethod.GET, "/processes/me/**")
                            .hasAnyRole(UserRole.PROCESSOR.name());

                    // - POST /processes: only PROCESSOR
                    auth.requestMatchers(HttpMethod.POST, "/processes")
                            .hasRole(UserRole.PROCESSOR.name());

                    // Events
                    // - GET /events only CURATOR and ADMINISTRATOR can access all events created
                    auth.requestMatchers(HttpMethod.GET, "/events")
                            .hasAnyRole(UserRole.CURATOR.name(), UserRole.ADMINISTRATOR.name());

                    // - GET /events/me/** accessible by FACILITATOR to view their own events
                    auth.requestMatchers(HttpMethod.GET, "/events/me/**")
                            .hasAnyRole(UserRole.FACILITATOR.name());

                    // - POST /events: only FACILITATOR can upload events
                    auth.requestMatchers(HttpMethod.POST, "/events")
                            .hasRole(UserRole.FACILITATOR.name());

                    // All other requests require authentication by default
                    auth.anyRequest().authenticated();
                })

                // Insert JWT filter before Spring's default username/password filter
                // This ensures token validation happens early in the filter chain
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                // Return UNAUTHORIZED status by default if the client try to access a protected endpoint
                .exceptionHandling(c -> {
                    c.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
                    c.accessDeniedHandler((
                            HttpServletRequest httpServletRequest,
                            HttpServletResponse httpServletResponse,
                            AccessDeniedException accessDeniedException
                        ) -> {
                        httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
                    });
                });

        // Return the built SecurityFilterChain
        return http.build();
    }

}
