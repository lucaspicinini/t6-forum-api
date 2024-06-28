package io.github.lucaspicinini.t6_forum_api.repository;

import io.github.lucaspicinini.t6_forum_api.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByName(String user);
}