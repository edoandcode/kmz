package com.edoardoconti.kmz_backend.system;

import com.edoardoconti.kmz_backend.role.UserRole;
import com.edoardoconti.kmz_backend.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
@Component
public class SetupAdminInterceptor implements HandlerInterceptor {
    private final UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        if(httpServletRequest.getMethod().equals("GET") &&
            httpServletRequest.getRequestURI().equals("/system/status")) {
            return true;
        }

        var adminExists = this.userRepository.existsByRolesContaining(UserRole.ADMINISTRATOR);

        if(!adminExists) {
            if(httpServletRequest.getMethod().equals("POST") &&
                httpServletRequest.getRequestURI().equals("/users/setup-admin")) {
                return true;
            } else {
                throw new NoAdminFoundException();
            }
        }

        return true;
    }

}
