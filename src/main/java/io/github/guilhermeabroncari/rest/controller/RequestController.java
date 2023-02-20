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
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("api/requests")
@Api("API Requests")
public class RequestController {
    @Autowired
    private RequestService service;
    @Autowired
    private RequestRepository repository;

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiOperation("Creates an order(request) using clients and products saved in the database.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Created a new request successfully."),
            @ApiResponse(code = 400, message = "Validation error.")
    })
    public Long saveRequest(@RequestBody @Valid RequestDTO dto) {
        Request request = service.save(dto);
        return request.getId();
    }

    @GetMapping("{id}")
    @ApiOperation("Find a request by ID.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Request found."),
            @ApiResponse(code = 404, message = "Request not found by ID.")
    })
    public RequestDTOInfo findRequestById(@PathVariable @ApiParam("Request ID.") Long id) {
        return service.getCompositeRequest(id).map(request -> convertRequest(request)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Request not found."));
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Update a Request parameters by ID.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Not return any content but make the updates of request status in database."),
            @ApiResponse(code = 404, message = "Request not found in database.")
    })
    public void updateRequestStatus(@PathVariable @ApiParam("Request ID.") Long id, @RequestBody AttRequestStatusDTO dto) {
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
