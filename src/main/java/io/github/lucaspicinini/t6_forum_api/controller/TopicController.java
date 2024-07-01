package io.github.lucaspicinini.t6_forum_api.controller;

import io.github.lucaspicinini.t6_forum_api.dto.TopicInputDto;
import io.github.lucaspicinini.t6_forum_api.dto.TopicDetailsDto;
import io.github.lucaspicinini.t6_forum_api.service.TopicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topics")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @PostMapping
    public ResponseEntity<TopicDetailsDto> post(@RequestBody @Valid TopicInputDto dto, UriComponentsBuilder uriBuilder) {
        var topic = topicService.create(dto);
        var uri = uriBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();

        return ResponseEntity.created(uri).body(new TopicDetailsDto(topic));
    }

    @GetMapping
    public ResponseEntity<Page<TopicDetailsDto>> list(Pageable pageable) {
        var page = topicService.list(pageable).map(TopicDetailsDto::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid TopicInputDto dto) {
        if (topicService.update(id, dto) != null) {
            var topic = topicService.update(id, dto);

            return ResponseEntity.ok(new TopicDetailsDto(topic));
        }

        return ResponseEntity.status(401).body("Acesso não autorizado.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (topicService.delete(id)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(401).body("Acesso não autorizado.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDetailsDto> getById(@PathVariable Long id) {
        var topic = topicService.detail(id);

        return ResponseEntity.ok(new TopicDetailsDto(topic));
    }
}
