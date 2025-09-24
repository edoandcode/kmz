package com.edoardoconti.kmz_backend.system;

import com.edoardoconti.kmz_backend.user.UserRegisterDto;
import com.edoardoconti.kmz_backend.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("system")
public class SystemController {
    private final UserService userService;

    @GetMapping("/status")
    public ResponseEntity<SystemStatusDto> getSystemStatus() {
            var status = new SystemStatusDto();
            status.setSuperAdminExists(this.userService.superAdminExists());
            return ResponseEntity.ok().body(status);
    }
}
