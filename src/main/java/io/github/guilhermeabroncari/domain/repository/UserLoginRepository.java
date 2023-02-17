package io.github.guilhermeabroncari.domain.repository;

import io.github.guilhermeabroncari.domain.entity.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLoginRepository extends JpaRepository<UserLogin, Long> {
    Optional<UserLogin> findByLogin(String login);
}
