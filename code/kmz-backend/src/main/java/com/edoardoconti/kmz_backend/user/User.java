package com.edoardoconti.kmz_backend.user;

import com.edoardoconti.kmz_backend.role.GenericUserRole;
import com.edoardoconti.kmz_backend.role.UserRole;

import com.edoardoconti.kmz_backend.role.UserRoleType;
import jakarta.persistence.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@ToString
@Entity
@Table(name="users")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public final class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private Long id;

    private String firstName;
    private String lastName;

    // Specifies that this field is a collection of basic or embeddable types stored in a separate table.
    @ElementCollection(targetClass = UserRoleType.class, fetch = FetchType.EAGER)
    // Defines how the enum values are stored in the database: as their String names (e.g., "ADMINISTRATOR").
    @Enumerated(EnumType.STRING)
    // Specifies the name of the table that will store the collection elements ("user_roles").
    // Also defines how this table is linked back to the owning entity with a foreign key column named "user_id".
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    // Defines the name of the column in the "user_roles" table that holds the enum values.
    @Column(name = "role")
    // Field that holds a set of UserRoleType enums for the user.
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
        roles.add(role);
    }



}
