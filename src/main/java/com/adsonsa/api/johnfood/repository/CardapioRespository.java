package com.adsonsa.api.johnfood.repository;

import com.adsonsa.api.johnfood.dto.CardapioDto;
import com.adsonsa.api.johnfood.entity.Cardapio;
import com.adsonsa.api.johnfood.repository.projection.CardapioProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardapioRespository extends PagingAndSortingRepository<Cardapio, Integer>, JpaSpecificationExecutor<Cardapio> {

    // retorna um cardapioDTO
    @Query("SELECT new com.adsonsa.api.johnfood.dto.CardapioDto(c.nome, c.descricao, c.valor, c.categoria.nome) " +
            "FROM Cardapio c WHERE c.nome LIKE %:nome% AND c.disponivel = true")
    List<CardapioDto> findAllByNome(final String nome,final Pageable pageable);

    @Query(value = "SELECT" +
            " c.nome as nome," +
            " c.descricao as descricao," +
            " c.valor as valor," +
            " cat.nome as nomeCategoria " +
            " FROM cardapio c" +
            " INNER JOIN categorias cat on c.categoria_id = cat.id" +
            " WHERE c.categoria_id = ?1 AND c.disponivel = true",
            nativeQuery = true,
            countQuery = "SELECT count(*) FROM cardapio")
    List<CardapioProjection> findByCategoria(final Integer categoria, final Pageable pageable);

}
