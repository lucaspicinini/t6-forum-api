package io.github.lucaspicinini.t6_forum_api.dto;

import java.io.Serializable;

/**
 * DTO for {@link io.github.lucaspicinini.t6_forum_api.controller.AuthenticationController}
 */
public record JwtTokenDto(String token) implements Serializable {
}
