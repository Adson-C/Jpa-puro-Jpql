package com.adsonsa.api.johnfood.controller;

import com.adsonsa.api.johnfood.entity.Cliente;
import com.adsonsa.api.johnfood.entity.ClienteId;
import com.adsonsa.api.johnfood.entity.Endereco;
import com.adsonsa.api.johnfood.repository.EnderecoRepository;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/endereco")
@RestController
public class EnderecoController {

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    // listar todos
    @GetMapping
    public ResponseEntity<List<Endereco>> consultarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(enderecoRepository.findAll());
    }

    @GetMapping("/cep/{cep}")
    public ResponseEntity<List<Endereco>> consultarTodosPorCep(@PathVariable(value = "cep") final String cep) {
        return ResponseEntity.status(HttpStatus.OK).body(enderecoRepository.findByCep(cep));
    }


    // busca por email e cpf
    @GetMapping("/{id}")
    public ResponseEntity<Endereco> consultarPorEmailECpf(@PathVariable(value = "id") final Integer id) {
        return enderecoRepository.findById(id)
                .map(value -> ResponseEntity.status(HttpStatus.OK).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
    // Update
//    @PatchMapping("/{id}")
//    public ResponseEntity<Cliente> update(@PathVariable(value = "id") final String id,@RequestBody final Cliente cliente) throws JsonMappingException {
//
//    }

}
