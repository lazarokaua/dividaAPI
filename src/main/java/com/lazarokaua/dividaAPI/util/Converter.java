package com.lazarokaua.dividaAPI.util;

import org.springframework.stereotype.Component;

import com.lazarokaua.dividaAPI.domain.divida.Divida;
import com.lazarokaua.dividaAPI.domain.divida.DividaDTO;

@Component 
public class Converter {

    public Divida converter(DividaDTO dividaDTO) {
        Divida divida = new Divida(
            null, dividaDTO.cpfDevedor(),
            dividaDTO.valorPego(),
            dividaDTO.valorComJuros(),
            dividaDTO.valorComDesconto()
        );



        return divida;

    }

    public DividaDTO converter(Divida divida) {
        return new DividaDTO(
            divida.getCpfDevedor(),
            divida.getValorPego(),
            divida.getValorComJuros(),
            divida.getValorComDesconto()
        );


    }

}
