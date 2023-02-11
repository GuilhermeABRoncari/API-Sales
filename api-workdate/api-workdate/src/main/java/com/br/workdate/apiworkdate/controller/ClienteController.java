package com.br.workdate.apiworkdate.controller;

import com.br.workdate.apiworkdate.domain.clientes.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity addCliente(@RequestBody @Valid DataClientes data, UriComponentsBuilder uriBuilder) {
        var cliente = new Cliente(data);
        repository.save(cliente);
        var uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(data);
    }

    @GetMapping
    public ResponseEntity<Page<DataClienteList>> listCliente(@PageableDefault(sort = {"nome"}) Pageable pageable) {
        var page = repository.findAll(pageable).map(DataClienteList::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity attCliente(@RequestBody @Valid UpdateClientes data) {
        var cliente = repository.getReferenceById(data.id());
        cliente.att(data);
        return ResponseEntity.ok(new newClienteData(cliente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteCliente(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity findCliente(@PathVariable Long id){
        var cliente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DataClientes(cliente));
    }
}
