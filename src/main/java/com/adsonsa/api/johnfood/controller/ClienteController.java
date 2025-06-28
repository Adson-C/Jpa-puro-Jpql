package com.adsonsa.api.johnfood.controller;

import com.adsonsa.api.johnfood.entity.Cliente;
import com.adsonsa.api.johnfood.entity.ClienteId;
import com.adsonsa.api.johnfood.repository.ClienteRepository;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/cliente")
@RestController
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ObjectMapper objectMapper;

    // listar todos
    @GetMapping
    public ResponseEntity<List<Cliente>> consultarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.findAll());
    }
    // busca por email e cpf
    @GetMapping("/{email}/{cpf}")
    public ResponseEntity<Cliente> consultarPorEmailECpf(@PathVariable(value = "email") final String email,@PathVariable(value = "cpf") final String cpf) {
        ClienteId clienteId = new ClienteId(email, cpf);
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        return cliente.map(value -> ResponseEntity.status(HttpStatus.OK).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
    // Update
    @PatchMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable(value = "id") final String id,@RequestBody final Cliente cliente) throws JsonMappingException {

        Optional<Cliente> clienteOptional = this.clienteRepository.findByEmailOrCpf(id);

        if (clienteOptional.isPresent()) {
            objectMapper.updateValue(clienteOptional.get(), cliente);
        return ResponseEntity.status(HttpStatus.OK).body(this.clienteRepository.save(clienteOptional.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    }
}
