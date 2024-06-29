package io.github.lucaspicinini.t6_forum_api.dto;

import jakarta.validation.constraints.NotBlank;
import io.github.lucaspicinini.t6_forum_api.controller.AuthenticationController;

import java.io.Serializable;

/**
 * DTO for {@link AuthenticationController}
 */
public record AuthenticationDto(
        @NotBlank(message = "Um email válido precisa ser informado.")
        String email,
        @NotBlank(message = "Uma senha precisa ser informada.")
        String password
) implements Serializable {
}
