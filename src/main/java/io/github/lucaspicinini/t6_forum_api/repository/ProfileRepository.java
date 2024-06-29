package io.github.lucaspicinini.t6_forum_api.repository;

import io.github.lucaspicinini.t6_forum_api.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findByName(String user);
}