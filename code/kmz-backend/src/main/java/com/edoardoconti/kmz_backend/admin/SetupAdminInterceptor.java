package com.edoardoconti.kmz_backend.admin;

import com.edoardoconti.kmz_backend.role.UserRoleType;
import com.edoardoconti.kmz_backend.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
@Component
public class SetupAdminInterceptor implements HandlerInterceptor {
    private final UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        var adminExists = this.userRepository.existsByRolesContaining(UserRoleType.ADMINISTRATOR);

        if(!adminExists) {
            if(httpServletRequest.getMethod().equals("POST") &&
                httpServletRequest.getRequestURI().equals("/admin/setup")) {
                return true;
            } else {
                throw new NoAdminFoundException();
            }
        }

        return true;
    }

}
