package io.github.lucaspicinini.t6_forum_api.dto;

import io.github.lucaspicinini.t6_forum_api.entity.Topic;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link Topic}
 */
public record TopicUpdateDto(
        @NotNull(message = "Um id deve ser informado.")
        Long id,
        String title,
        String message,
        CourseDto course
) implements Serializable {
}
