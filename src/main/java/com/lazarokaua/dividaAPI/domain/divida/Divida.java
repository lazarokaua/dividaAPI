package com.lazarokaua.dividaAPI.domain.divida;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Data
@NoArgsConstructor 
@AllArgsConstructor
public class Divida {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpfDevedor;
    private BigDecimal valorPego;
    private BigDecimal valorComJuros;
    private BigDecimal valorComDesconto;

}
