package io.github.lucaspicinini.t6_forum_api.service;

import io.github.lucaspicinini.t6_forum_api.configuration.DateAndTimeConfigurations;
import io.github.lucaspicinini.t6_forum_api.dto.TopicCreateDto;
import io.github.lucaspicinini.t6_forum_api.dto.TopicUpdateDto;
import io.github.lucaspicinini.t6_forum_api.entity.Topic;
import io.github.lucaspicinini.t6_forum_api.entity.User;
import io.github.lucaspicinini.t6_forum_api.repository.CourseRepository;
import io.github.lucaspicinini.t6_forum_api.repository.TopicRepository;
import io.github.lucaspicinini.t6_forum_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Service for {@link Topic}
 */
@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Topic create(TopicCreateDto dto) {
        var topic = new Topic();
        var course = courseRepository.findByName(dto.course().name());
        var authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var user = userRepository.findById(authenticatedUser.getId());
        var now = DateAndTimeConfigurations.generateDateForNow();

        topic.setStatus(true);
        topic.setRegisterDate(now);
        topic.setTitle(dto.title());
        topic.setMessage(dto.message());
        course.ifPresent(topic::setCourse);
        user.ifPresent(topic::setUser);

        course.ifPresent(c -> c.addTopic(topic));
        user.ifPresent(u -> u.addTopic(topic));

        topicRepository.save(topic);

        return topic;
    }

    public Page<Topic> list(Pageable pageable) {
        return topicRepository.findAll(pageable);
    }

    @Transactional
    public Topic update(TopicUpdateDto dto) {
        Topic topic = null;

        if (isUserAuthorized(dto.id())) {
            topic = topicRepository.findById(dto.id()).orElseThrow();

            if (dto.title() != null && !dto.title().isBlank()) {
                topic.setTitle(dto.title());
            }

            if (dto.message() != null && !dto.message().isBlank()) {
                topic.setMessage(dto.message());
            }

            if (
                    dto.course() != null &&
                    dto.course().name() != null &&
                    !dto.course().name().isBlank()
            ) {
                var course = courseRepository.findByName(dto.course().name());
                course.ifPresent(topic::setCourse);
            }

            topicRepository.save(topic);
        }

        return topic;
    }

    @Transactional
    public boolean delete(Long id) {

        if (isUserAuthorized(id)) {
            topicRepository.deleteById(id);
            return true;
        }

        return false;
    }

    public Topic detail(Long id) {
        return topicRepository.findById(id).orElseThrow();
    }

    private boolean isUserAuthorized(Long id) {
        var topic = topicRepository.findById(id).orElseThrow();
        var authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return topicRepository.existsByIdAndUserId(topic.getId(), authenticatedUser.getId());
    }
}
