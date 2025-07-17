package com.edoardoconti.kmz_backend.user;

import com.edoardoconti.kmz_backend.role.GenericUserRole;
import com.edoardoconti.kmz_backend.role.UserRole;

import java.util.HashSet;
import java.util.Set;

public final class User {
    private final String firstName;
    private final String lastName;
    private Set<UserRole> roles = new HashSet<>();

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles.add(new GenericUserRole());
    }

    public boolean can() {
        // TODO
        return true;
    }


    // getters and setters
    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }


}
