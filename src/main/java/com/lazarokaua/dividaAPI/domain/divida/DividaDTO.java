package com.lazarokaua.dividaAPI.domain.divida;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record DividaDTO(
    @NotBlank @CPF(message = "CPF Inválido")
    String cpfDevedor,
    @PositiveOrZero(message = "O valor pego não pode ser negativo!")
    BigDecimal valorPego,
    @PositiveOrZero(message = "O valor com juros não pode ser negativo!")
    BigDecimal valorComJuros,
    @PositiveOrZero(message = "O valor com desconto não pode ser negativo!")
    BigDecimal valorComDesconto
) {}