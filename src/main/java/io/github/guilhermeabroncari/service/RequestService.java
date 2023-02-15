package io.github.guilhermeabroncari.service;

import io.github.guilhermeabroncari.domain.entity.Request;
import io.github.guilhermeabroncari.rest.dto.RequestDTO;

public interface RequestService {
    Request save(RequestDTO dto);
}
