package io.github.guilhermeabroncari.rest.controller;

import io.github.guilhermeabroncari.domain.entity.ItemRequest;
import io.github.guilhermeabroncari.domain.entity.Request;
import io.github.guilhermeabroncari.domain.enums.RequestStatus;
import io.github.guilhermeabroncari.domain.repository.RequestRepository;
import io.github.guilhermeabroncari.rest.dto.AttRequestStatusDTO;
import io.github.guilhermeabroncari.rest.dto.ItemRequestDTOInfo;
import io.github.guilhermeabroncari.rest.dto.RequestDTO;
import io.github.guilhermeabroncari.rest.dto.RequestDTOInfo;
import io.github.guilhermeabroncari.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("api/requests")
public class RequestController {
    @Autowired
    private RequestService service;
    @Autowired
    private RequestRepository repository;

    @PostMapping
    @ResponseStatus(CREATED)
    public Long saveRequest(@RequestBody RequestDTO dto) {
        Request request = service.save(dto);
        return request.getId();
    }

    @GetMapping("{id}")
    public RequestDTOInfo findRequestById(@PathVariable Long id) {
        return service.getCompositeRequest(id).map(request -> convertRequest(request)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Request not found."));
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRequestStatus(@PathVariable Long id, @RequestBody AttRequestStatusDTO dto) {
        service.statusUpdate(id, RequestStatus.valueOf(dto.getNewStatus()));
    }

    private RequestDTOInfo convertRequest(Request request) {
        return RequestDTOInfo.builder()
                .code(request.getId())
                .requestDate(request.getRequestDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(request.getClient().getCpf())
                .clientName(request.getClient().getName())
                .totalPrice(request.getTotalPrice())
                .status(request.getStatus().name())
                .items(convertRequest(request.getItemRequestList()))
                .build();
    }

    private List<ItemRequestDTOInfo> convertRequest(List<ItemRequest> itemRequests) {
        if (CollectionUtils.isEmpty(itemRequests)) {
            return Collections.emptyList();
        }
        return itemRequests.stream().map(itemRequest -> ItemRequestDTOInfo.builder()
                        .productDescription(itemRequest.getProduct().getDescription())
                        .unitaryValue(itemRequest.getProduct().getPrice())
                        .amount(itemRequest.getAmount())
                        .build())
                .collect(Collectors.toList());
    }

}
