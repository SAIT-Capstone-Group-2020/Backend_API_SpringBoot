package hha.spring.data.dataapi.security;


import hha.spring.data.dataapi.domain.Users;

import hha.spring.data.dataapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.springframework.security.core.userdetails.User.withUsername;

/**
 * Token Utilities for UserDetailsService
 * UserDetailsService: Spring Security interface which is used to
 * retrieve the user's authentication and authorization *
 */
@Component
public class JwtService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private JwtUtil service;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

            Users user = repository.findByEmail(s);

            if(user == null) {
                throw new UsernameNotFoundException("User not found with username: "+ s); }

            return withUsername(user.getEmail())
                    .password(user.getPassword())
                    .authorities(user.getRoles())
                    .accountExpired(false)
                    .accountLocked(false)
                    .credentialsExpired(false)
                    .disabled(false)
                    .build();
    }

    /**
     * Load user by jwt token optional.
     *
     * @param token the token
     * @return the optional
     */
    public Optional<UserDetails> loadUserByJwtToken(String token) {
        if(service.isValidToken(token)) {
            return Optional.of(
                    withUsername(service.getUsername(token))
                            .authorities(service.getRoles(token))
                            .password("")
                            .accountExpired(false)
                            .accountLocked(false)
                            .credentialsExpired(false)
                            .disabled(false)
                            .build());
        }

        return Optional.empty();
    }

    /**
     * Load user by jwt token and db optional.
     *
     * @param token the token
     * @return the optional
     */
    public Optional<UserDetails> loadUserByJwtTokenAndDb(String token) {
        if(service.isValidToken(token)) {
            return Optional.of(loadUserByUsername(service.getUsername(token)));
        }
        else {
            return Optional.empty();
        }
    }
}
