package com.edoardoconti.kmz_backend.user;

import com.edoardoconti.kmz_backend.role.UserRoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByRolesContaining(UserRoleType role);
}
