package com.adsonsa.api.johnfood.repository.projection;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface CardapioProjection {
    Integer getid();

    String getnome();

    String getdescricao();

    BigDecimal getvalor();


    String getnomeCategoria();
}
