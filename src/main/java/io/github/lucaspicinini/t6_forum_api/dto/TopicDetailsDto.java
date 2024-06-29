package io.github.lucaspicinini.t6_forum_api.dto;

import io.github.lucaspicinini.t6_forum_api.configuration.DateAndTimeConfigurations;
import io.github.lucaspicinini.t6_forum_api.entity.Topic;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link Topic}
 */
public record TopicDetailsDto(
        @NotNull(message = "Um id válido precisa ser informado.")
        Long id,
        String title,
        String message,
        String user,
        String course,
        String category,
        String registerDate
) implements Serializable {
    public TopicDetailsDto(Topic topic) {
        this(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getUser().getNickname(),
                topic.getCourse().getName(),
                topic.getCourse().getCategory(),
                DateAndTimeConfigurations.formatToBr(topic.getRegisterDate())
        );
    }
}
