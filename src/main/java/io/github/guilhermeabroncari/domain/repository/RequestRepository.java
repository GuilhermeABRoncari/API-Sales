package io.github.guilhermeabroncari.domain.repository;

import io.github.guilhermeabroncari.domain.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {
}
