package io.github.lucaspicinini.t6_forum_api.service;

import io.github.lucaspicinini.t6_forum_api.configuration.DateAndTimeConfigurations;
import io.github.lucaspicinini.t6_forum_api.controller.AnswerController;
import io.github.lucaspicinini.t6_forum_api.dto.AnswerCreateDto;
import io.github.lucaspicinini.t6_forum_api.dto.AnswerUpdateDto;
import io.github.lucaspicinini.t6_forum_api.entity.Answer;
import io.github.lucaspicinini.t6_forum_api.entity.User;
import io.github.lucaspicinini.t6_forum_api.repository.AnswerRepository;
import io.github.lucaspicinini.t6_forum_api.repository.TopicRepository;
import io.github.lucaspicinini.t6_forum_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for {@link AnswerController}
 */
@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TopicRepository topicRepository;

    @Transactional
    public Answer create(AnswerCreateDto dto) {
        var answer = new Answer();
        var topic = topicRepository.findById(dto.topicId()).orElseThrow();
        var authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var user = userRepository.findById(authenticatedUser.getId()).orElseThrow();
        var now = DateAndTimeConfigurations.generateDateForNow();

        answer.setTopic(topic);
        answer.setUser(user);
        answer.setRegisterDate(now);
        answer.setLastUpdate(now);
        answer.setMessage(dto.message());

        user.addAnswer(answer);
        topic.addAnswer(answer);

        answerRepository.save(answer);

        return answer;
    }

    public Page<Answer> list(Pageable pageable) {
        return answerRepository.findAll(pageable);
    }

    @Transactional
    public Answer update(AnswerUpdateDto dto) {
        Answer answer = null;

        if (isUserAuthorized(dto.id())) {
            answer = answerRepository.findById(dto.id()).orElseThrow();
            var now = DateAndTimeConfigurations.generateDateForNow();
            answer.setLastUpdate(now);
            answer.setMessage(dto.message());
            answerRepository.save(answer);
        }

        return answer;
    }

    @Transactional
    public boolean delete(Long id) {

        if (isUserAuthorized(id)) {
            answerRepository.deleteById(id);
            return true;
        }

        return false;
    }

    public Answer detail(Long id) {
        return answerRepository.findById(id).orElseThrow();
    }

    private boolean isUserAuthorized(Long id) {
        var answer = answerRepository.findById(id).orElseThrow();
        var authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return answerRepository.existsByIdAndUserId(answer.getId(), authenticatedUser.getId());
    }
}
