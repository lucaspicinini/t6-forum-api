package io.github.lucaspicinini.t6_forum_api.controller;

import io.github.lucaspicinini.t6_forum_api.dto.RegisterUserDto;
import io.github.lucaspicinini.t6_forum_api.dto.UserDetailsDto;
import io.github.lucaspicinini.t6_forum_api.service.RegisterUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/register")
public class RegisterUserController {
    @Autowired
    private RegisterUserService registerUserService;

    @PostMapping
    public ResponseEntity<UserDetailsDto> create(
            @RequestBody @Valid RegisterUserDto dto,
            UriComponentsBuilder uriBuilder
    ) {
        var user = registerUserService.register(dto);
        var uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(new UserDetailsDto(user));
    }
}
