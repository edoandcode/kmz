package com.edoardoconti.kmz_backend.user;

import com.edoardoconti.kmz_backend.role.GenericUserRole;
import com.edoardoconti.kmz_backend.role.UserRole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@ToString
public final class User {
    @Getter
    @Setter
    private Long id;
    @Getter
    private final String firstName;
    @Getter
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

    public void addRole(UserRole role) {
        roles.add(role);
    }



}
