package io.github.lucaspicinini.t6_forum_api.service;

import io.github.lucaspicinini.t6_forum_api.dto.RegisterUserDto;
import io.github.lucaspicinini.t6_forum_api.entity.User;
import io.github.lucaspicinini.t6_forum_api.repository.ProfileRepository;
import io.github.lucaspicinini.t6_forum_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import io.github.lucaspicinini.t6_forum_api.controller.RegisterUserController;

/**
 * Service for {@link RegisterUserController}
 */
@Service
public class RegisterUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;

    @Transactional
    public User register(RegisterUserDto dto) {
        var userProfile = profileRepository.findByName("user");
        var user = new User();
        var encoder = new BCryptPasswordEncoder();
        var encodedPassword = encoder.encode(dto.password());

        user.addProfile(userProfile);
        user.setNickname(dto.nickname());
        user.setEmail(dto.email());
        user.setPassword(encodedPassword);
        userRepository.save(user);

        return user;
    }
}
