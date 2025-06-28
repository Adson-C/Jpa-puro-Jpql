package com.adsonsa.api.johnfood.controller;

import com.adsonsa.api.johnfood.dto.CardapioDto;
import com.adsonsa.api.johnfood.entity.Cardapio;
import com.adsonsa.api.johnfood.repository.CardapioRespository;
import com.adsonsa.api.johnfood.repository.especification.CardaponSpec;
import com.adsonsa.api.johnfood.repository.projection.CardapioProjection;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequestMapping("/cardapio")
@RestController
public class CardapioController {


    @Autowired
    private CardapioRespository cardapioRespository;

    @Autowired
    private ObjectMapper objectMapper;

    // listar todos
    @GetMapping
    public ResponseEntity<Page<Cardapio>> consultarTodos(@RequestParam("page") final Integer page, @RequestParam("size") final Integer size,
                                                         @RequestParam(value = "sort", required = false) Sort.Direction sort,
                                                         @RequestParam(value = "property",required = false)String property) {
        Pageable pageable = Objects.isNull(sort) ?
                PageRequest.of(page, size, Sort.by(sort, property))
                : PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(cardapioRespository.findAll(pageable));
    }
    // Native Query Por Like Name
    @GetMapping("/nome/{nome}/disponivel")
    public ResponseEntity<List<Cardapio>> consultarPorNome(@PathVariable(value = "nome") final String nome,
                                                              @RequestParam("page") final Integer page, @RequestParam("size") final Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK).body((List<Cardapio>) cardapioRespository.findAll(Specification.where(
                CardaponSpec.nome(nome))
                .and(CardaponSpec.disponivel(true)), pageable).getContent());
    }

    // Native Query
    @GetMapping("/categoria/{categoria_id}/disponivel")
    public ResponseEntity<List<Cardapio>> consultarTodosComQuery(@PathVariable(value = "categoria_id") final Integer categoria_id,
                                                                           @RequestParam("page") final Integer page,
                                                                           @RequestParam("size") final Integer size)
    {Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(cardapioRespository.findAll(Specification.where(
                CardaponSpec.categoria(categoria_id)
                .and(CardaponSpec.disponivel(true))
        ),pageable).getContent());
    }


    // busca por email e cpf
    @GetMapping("/{id}/")
    public ResponseEntity<Cardapio> consultarPorEmailECpf(@PathVariable(value = "id") final Integer id) {
        return cardapioRespository.findById(id)
                .map(value -> ResponseEntity.status(HttpStatus.OK).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    //  criar
    @PostMapping
    public ResponseEntity<Cardapio> create(@RequestBody final Cardapio cardapio) throws JsonMappingException {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.cardapioRespository.save(cardapio));
    }

    // Update
    @PatchMapping("/{id}")
    public ResponseEntity<Cardapio> update(@PathVariable(value = "id") final Integer id,@RequestBody final Cardapio cardapio) throws JsonMappingException {

        Optional<Cardapio> CardapioOptional = this.cardapioRespository.findById(id);

        if (CardapioOptional.isPresent()) {
            objectMapper.updateValue(CardapioOptional.get(), cardapio);
            return ResponseEntity.status(HttpStatus.OK).body(this.cardapioRespository.save(CardapioOptional.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    // busca por email e cpf
    @DeleteMapping("/{id}/")
    public ResponseEntity<?> excluirPorId(@PathVariable(value = "id") final Integer id) {
         Optional<Cardapio> CardapioOptional = this.cardapioRespository.findById(id);
        if (CardapioOptional.isPresent()) {
            cardapioRespository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Elemento não encontrado");
    }
}
