package com.lazarokaua;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.lazarokaua.dividaAPI.domain.divida.Divida;
import com.lazarokaua.dividaAPI.domain.divida.DividaDTO;
import com.lazarokaua.dividaAPI.domain.divida.DividaRepository;
import com.lazarokaua.dividaAPI.domain.divida.DividaResponse;
import com.lazarokaua.dividaAPI.domain.divida.service.DividaService;
import com.lazarokaua.dividaAPI.util.Converter;

@ExtendWith(MockitoExtension.class)
public class DividaServiceTest {

    @InjectMocks
    private DividaService dividaService;

    @Mock 
    private Converter converter;

    @Mock 
    private DividaRepository dividaRepository;


    @Test
    @DisplayName("Deve criar uma divida com sucesso")
    void deveCriarUmaDividaComSucesso() {
        DividaDTO dto = new DividaDTO("123.456.789-09", new BigDecimal("100"), new BigDecimal("150"), new BigDecimal("100"));
        Divida divida = new Divida();
        divida.setCpfDevedor("12345678909");
        divida.setValorPego(new BigDecimal("100"));


        when(converter.converter(dto)).thenReturn(divida);
        when(dividaRepository.save(divida)).thenReturn(divida);

        DividaResponse response = dividaService.criar(dto);

        assertNotNull(response);
        assertEquals("12345678909", response.cpfDevedor());
        verify(dividaRepository, times(1)).save(divida);

    }

    @Test
    @DisplayName("Deve retornar uma divida ao buscar pelo id")
    void deveBuscarPorIdCorretamente() {
        Long idLong = 1L;
        Divida divida = new Divida();
        divida.setId(idLong);
        divida.setCpfDevedor("12345678909");

        DividaDTO dto = new DividaDTO("12345678909", new BigDecimal("100"), new BigDecimal("150"), new BigDecimal("100"));

        

        when(dividaRepository.findById(idLong)).thenReturn(Optional.of(divida));
        when(converter.converter(divida)).thenReturn(dto);


        DividaDTO response = dividaService.buscarPorId(idLong);

        assertNotNull(response);
        assertEquals("12345678909", response.cpfDevedor());
        verify(dividaRepository, times(1)).findById(idLong);



    }
}
