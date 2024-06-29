package io.github.lucaspicinini.t6_forum_api.dto;

import io.github.lucaspicinini.t6_forum_api.entity.User;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
public record UserDetailsDto(
        @NotNull(message = "Um id válido precisa ser informado.")
        Long id,
        String nickname,
        String email
) implements Serializable {
    public UserDetailsDto(User user) {
        this(
                user.getId(),
                user.getNickname(),
                user.getEmail()
        );
    }
}
