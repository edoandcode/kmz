package com.edoardoconti.kmz_backend.system;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NoAdminFoundException extends RuntimeException {
    public NoAdminFoundException() {
        super("No administrator configured. Please POST to /users/setup-admin to create the first admin user.");
    }
}
