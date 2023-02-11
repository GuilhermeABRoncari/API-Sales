package com.br.workdate.apiworkdate.controller;

import com.br.workdate.apiworkdate.domain.servicos.*;
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
@RequestMapping("/servicos")
public class ServicoController {
    @Autowired
    private ServicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity addServico(@RequestBody @Valid ServicoData data, UriComponentsBuilder uriBuilder) {
        var servico = new Servicos(data);
        repository.save(servico);
        var uri = uriBuilder.path("/servicos/{id}").buildAndExpand(servico.getId()).toUri();
        return ResponseEntity.created(uri).body(data);
    }

    @GetMapping
    public ResponseEntity<Page<DataServicoList>> listServicos(@PageableDefault(sort = "descricao") Pageable pageable) {
        var page = repository.findAll(pageable).map(DataServicoList::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity attServico(@RequestBody @Valid UpdateServico updateServico) {
        var servico = repository.getReferenceById(updateServico.id());
        servico.att(updateServico);
        return ResponseEntity.ok(new UpdateServico(servico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteServico(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity findServico(@PathVariable Long id){
        var servico = repository.getReferenceById(id);
        return ResponseEntity.ok(new ServicoData(servico));
    }
}
