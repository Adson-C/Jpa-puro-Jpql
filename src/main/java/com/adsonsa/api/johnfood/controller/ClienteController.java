package com.adsonsa.api.johnfood.controller;

import com.adsonsa.api.johnfood.entity.Cliente;
import com.adsonsa.api.johnfood.entity.ClienteId;
import com.adsonsa.api.johnfood.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequestMapping("/cliente")
@RestController
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    // listar todos
    @GetMapping
    private ResponseEntity<List<Cliente>> consultarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.findAll());
    }
    // busca por email e cpf
    @GetMapping("/{email}/{cpf}")
    private ResponseEntity<Cliente> consultarPorEmailECpf(@PathVariable(value = "email") final String email,@PathVariable(value = "cpf") final String cpf) {
        ClienteId clienteId = new ClienteId(email, cpf);
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        return cliente.map(value -> ResponseEntity.status(HttpStatus.OK).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
}
