package io.github.guilhermeabroncari.rest.controller;

import io.github.guilhermeabroncari.domain.entity.Client;
import io.github.guilhermeabroncari.domain.repository.ClientRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
@Api("API Clients")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    @ApiOperation("Make a list of existing customers and filter on your parameters.")
    @ApiResponse(code = 200, message = "Client found.")
    public List<Client> findClientWithParam(Client filter) {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filter, matcher);
        return clientRepository.findAll(example);
    }

    @GetMapping("/{id}")
    @ApiOperation("Find a client by ID.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Client found."),
            @ApiResponse(code = 404, message = "Client not found by ID.")
    })
    public Client getClientById(@PathVariable @ApiParam("Client ID.") Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found."));
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Saves a new client in database.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created a new client successfully."),
            @ApiResponse(code = 400, message = "Validation error.")
    })
    public Client saveClient(@RequestBody @Valid Client client) {
        return clientRepository.save(client);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Delete a client by ID.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Client deleted by ID."),
            @ApiResponse(code = 404, message = "Client not found in database.")
    })
    public void deleteClient(@PathVariable @ApiParam("Client ID.") Long id) {
        clientRepository.findById(id).map(client -> {
                    clientRepository.delete(client);
                    return client;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found."));
    }

    @PutMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Update Client parameters by ID.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Not return any content but make the updates of client in database."),
            @ApiResponse(code = 404, message = "Client not found in database.")
    })
    public void updateClient(@PathVariable @ApiParam("Client ID.") Long id, @RequestBody @Valid Client client) {
        clientRepository.findById(id).map(atualClient -> {
            client.setId(atualClient.getId());
            clientRepository.save(client);
            return client;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found."));
    }

}
