package io.github.lucaspicinini.t6_forum_api.dto;

import java.io.Serializable;
import io.github.lucaspicinini.t6_forum_api.controller.AuthenticationController;

/**
 * DTO for {@link AuthenticationController}
 */
public record JwtTokenDto(String token) implements Serializable {
}
