package io.github.guilhermeabroncari.domain.repository;

import io.github.guilhermeabroncari.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
