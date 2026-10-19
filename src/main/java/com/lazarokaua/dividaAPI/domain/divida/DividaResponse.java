package com.lazarokaua.dividaAPI.domain.divida;

import java.math.BigDecimal;

public record DividaResponse(
    Long id, 
    String cpfDevedor,
    BigDecimal valorPego 
) {}