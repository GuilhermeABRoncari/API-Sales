package io.github.guilhermeabroncari.domain.repository;

import io.github.guilhermeabroncari.domain.entity.ItemRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRequestRepository extends JpaRepository<ItemRequest, Long> {
}
