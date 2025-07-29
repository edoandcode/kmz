package com.edoardoconti.kmz_backend.user;

import com.edoardoconti.kmz_backend.role.GenericUserRole;
import com.edoardoconti.kmz_backend.role.UserRole;

import com.edoardoconti.kmz_backend.role.UserRoleType;
import jakarta.persistence.*;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@ToString
@Entity
@Table(name="users")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public final class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private Long id;

    private String firstName;
    private String lastName;

    @ElementCollection(targetClass = UserRoleType.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<UserRoleType> roles = new HashSet<>();

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles.add(UserRoleType.GENERIC_USER);
    }

    public boolean can() {
        // TODO
        return true;
    }

    public void addRole(UserRoleType role) {
        System.out.println("role " + role);
        roles.add(role);
    }



}
