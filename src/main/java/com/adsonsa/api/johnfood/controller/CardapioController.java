package com.adsonsa.api.johnfood.controller;

import com.adsonsa.api.johnfood.dto.CardapioDto;
import com.adsonsa.api.johnfood.entity.Cardapio;
import com.adsonsa.api.johnfood.repository.CardapioRespository;
import com.adsonsa.api.johnfood.repository.projection.CardapioProjection;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<List<Cardapio>> consultarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(cardapioRespository.findAll());
    }
    // Native Query Por Like Name
    @GetMapping("/nome/{nome}/disponivel")
    public ResponseEntity<List<CardapioDto>> consultarPorNome(@PathVariable(value = "nome") final String nome) {
        return ResponseEntity.status(HttpStatus.OK).body(cardapioRespository.findAllByNome(nome));
    }

    // Native Query
    @GetMapping("/categoria/{categoria_id}/disponivel")
    public ResponseEntity<List<CardapioProjection>> consultarTodosComQuery(@PathVariable(value = "categoria_id") final Integer categoria_id) {
        return ResponseEntity.status(HttpStatus.OK).body(cardapioRespository.findByCategoria(categoria_id));
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
