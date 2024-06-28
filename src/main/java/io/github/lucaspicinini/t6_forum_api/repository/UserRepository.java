package io.github.lucaspicinini.t6_forum_api.repository;

import io.github.lucaspicinini.t6_forum_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // UserDetails findByEmail(String email);
}