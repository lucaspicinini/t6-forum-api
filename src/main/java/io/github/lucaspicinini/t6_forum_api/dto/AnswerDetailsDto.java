package io.github.lucaspicinini.t6_forum_api.dto;

import io.github.lucaspicinini.t6_forum_api.controller.AnswerController;
import io.github.lucaspicinini.t6_forum_api.entity.Answer;

import java.io.Serializable;

/**
 * DTO for {@link AnswerController}
 */
public record AnswerDetailsDto(
        Long id,
        Long userId,
        String nickname,
        Long topicId,
        String title,
        String message
) implements Serializable {
    public AnswerDetailsDto(Answer answer) {
        this(
                answer.getId(),
                answer.getUser().getId(),
                answer.getUser().getNickname(),
                answer.getTopic().getId(),
                answer.getTopic().getTitle(),
                answer.getMessage()
        );
    }
}
