package com.adsonsa.api.johnfood.repository.especification;

import com.adsonsa.api.johnfood.entity.Cardapio;
import org.springframework.data.jpa.domain.Specification;

public class CardaponSpec {

    public static Specification<Cardapio> nome(String nome) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
    }
    public static Specification<Cardapio> disponivel(Boolean disponivel) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("disponivel"), disponivel);
    }
    public static Specification<Cardapio> categoria(Integer categoria) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("categoria").get("id"), categoria);
    }
}
