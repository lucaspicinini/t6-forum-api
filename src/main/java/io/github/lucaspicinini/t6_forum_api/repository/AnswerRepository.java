package io.github.lucaspicinini.t6_forum_api.repository;

import io.github.lucaspicinini.t6_forum_api.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Boolean existsByIdAndUserId(Long id, Long userId);
}