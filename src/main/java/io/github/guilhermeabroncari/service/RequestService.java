package io.github.guilhermeabroncari.service;

import io.github.guilhermeabroncari.domain.entity.Request;
import io.github.guilhermeabroncari.rest.dto.RequestDTO;

import java.util.Optional;

public interface RequestService {
    Request save(RequestDTO dto);
    Optional<Request> getCompositeRequest(Long id);
}
