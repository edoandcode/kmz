package com.edoardoconti.kmz_backend.security;

import com.edoardoconti.kmz_backend.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Loads user details by email.
     * This method is called internally by Spring Security when a user tries to authenticate.
     *
     * @param email the email used as username
     * @return UserDetails object (Spring Security representation of a user)
     * @throws UsernameNotFoundException if no user is found with the given email
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User not found"));

        // Return a Spring Security User object with username, password, and authorities
        return new User(
                user.getEmail(),
                user.getPassword(),
                Collections.emptyList() // no roles/authorities assigned here
        );
    }
}
