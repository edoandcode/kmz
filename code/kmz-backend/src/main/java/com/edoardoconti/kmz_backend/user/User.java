package com.edoardoconti.kmz_backend.user;

import com.edoardoconti.kmz_backend.role.UserRole;
import jakarta.persistence.*;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
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

    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<UserRole> roles = new HashSet<>();

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles.add(UserRole.GENERIC_USER);
    }

    public void addRole(UserRole role) {
        roles.add(role);
    }

}
