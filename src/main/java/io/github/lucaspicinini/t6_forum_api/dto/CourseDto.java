package io.github.lucaspicinini.t6_forum_api.dto;

import io.github.lucaspicinini.t6_forum_api.entity.Course;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link Course}
 */
public record CourseDto(
        @Size(message = "O nome não pode ultrapassar 255 caracteres.", max = 255)
        @NotBlank(message = "O curso precisa ter algum nome.")
        String name,

        @Size(message = "A categoria não pode ultrapassar 255 caracteres.", max = 255)
        String category
) implements Serializable {
}
