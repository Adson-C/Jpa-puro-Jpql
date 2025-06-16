package com.adsonsa.api.johnfood.repository;

import com.adsonsa.api.johnfood.entity.Cliente;
import com.adsonsa.api.johnfood.entity.ClienteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, ClienteId> {

    // listar todos
//    public List<Cliente> findAll();
}
