package io.github.guilhermeabroncari.domain.repository;

import io.github.guilhermeabroncari.domain.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByNameContaining(String name);
    List<Client> findByNameOrId(String name, Long id);
    boolean existsByName(String name);
}
