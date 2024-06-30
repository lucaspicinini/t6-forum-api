package io.github.lucaspicinini.t6_forum_api.controller;

import io.github.lucaspicinini.t6_forum_api.dto.AnswerCreateDto;
import io.github.lucaspicinini.t6_forum_api.dto.AnswerDetailsDto;
import io.github.lucaspicinini.t6_forum_api.service.AnswerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/answers")
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    @PostMapping
    public ResponseEntity<AnswerDetailsDto> create(@RequestBody @Valid AnswerCreateDto dto, UriComponentsBuilder uriBuilder) {
        var answer = answerService.create(dto);
        var uri = uriBuilder.path("/answers/{id}").buildAndExpand(answer.getId()).toUri();

        return ResponseEntity.created(uri).body(new AnswerDetailsDto(answer));
    }

    @GetMapping
    public ResponseEntity<Page<AnswerDetailsDto>> list(Pageable pageable) {
        var page = answerService.list(pageable).map(AnswerDetailsDto::new);

        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (answerService.delete(id)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(401).body("Acesso não autorizado.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnswerDetailsDto> getById(@PathVariable Long id) {
        var answer = answerService.detail(id);

        return ResponseEntity.ok(new AnswerDetailsDto(answer));
    }
}
