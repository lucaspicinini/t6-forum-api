package io.github.lucaspicinini.t6_forum_api.service;

import io.github.lucaspicinini.t6_forum_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import io.github.lucaspicinini.t6_forum_api.controller.AuthenticationController;
import org.springframework.stereotype.Service;

/**
 * Service for {@link AuthenticationController}
 */
@Service
public class AuthenticationService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }
}
