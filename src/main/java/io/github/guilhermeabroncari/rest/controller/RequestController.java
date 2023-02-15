package io.github.guilhermeabroncari.rest.controller;

import io.github.guilhermeabroncari.domain.entity.Request;
import io.github.guilhermeabroncari.rest.dto.RequestDTO;
import io.github.guilhermeabroncari.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("api/requests")
public class RequestController {
    @Autowired
    private RequestService service;

    @PostMapping
    @ResponseStatus(CREATED)
    public Long saveRequest(@RequestBody RequestDTO dto) {
        Request request = service.save(dto);
        return request.getId();
    }
}
