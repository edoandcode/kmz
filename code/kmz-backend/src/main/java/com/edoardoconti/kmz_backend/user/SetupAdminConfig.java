package com.edoardoconti.kmz_backend.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
@RequiredArgsConstructor
public class SetupAdminConfig implements WebMvcConfigurer {

    private final SetupAdminInterceptor setupAdminInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.setupAdminInterceptor);
    }
}
