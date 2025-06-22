package com.adsonsa.api.johnfood.repository;

import com.adsonsa.api.johnfood.entity.Cardapio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardapioRespository extends JpaRepository<Cardapio, Integer> {

    @Query(value = "SELECT * FROM cardapio c WHERE c.categoria_id = ?1 AND c.disponivel = true", nativeQuery = true)
    List<Cardapio> findByCategoria(final Integer categoria);

}
