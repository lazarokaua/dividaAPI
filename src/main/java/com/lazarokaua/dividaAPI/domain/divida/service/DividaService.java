package com.lazarokaua.dividaAPI.domain.divida.service;


import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import com.lazarokaua.dividaAPI.domain.divida.Divida;
import com.lazarokaua.dividaAPI.domain.divida.DividaDTO;
import com.lazarokaua.dividaAPI.domain.divida.DividaRepository;
import com.lazarokaua.dividaAPI.domain.divida.DividaResponse;
import com.lazarokaua.dividaAPI.domain.divida.exception.DividaNotFoundException;
import com.lazarokaua.dividaAPI.util.Converter;


@Service 
public class DividaService {

    private final Converter convert;
    private final DividaRepository dividaRepository;

    public DividaService(Converter converter, DividaRepository dividaRepository) {
        this.convert = converter;
        this.dividaRepository = dividaRepository;
    }

    public DividaResponse criar(DividaDTO dividaDTO) {

        Divida entity = convert.converter(dividaDTO);
        Divida dividaSalvo = dividaRepository.save(entity);

        return new DividaResponse(dividaSalvo.getId(), dividaSalvo.getCpfDevedor(), dividaSalvo.getValorPego());
    }


    public DividaDTO buscarPorId(Long id) {

        Divida divida = dividaRepository.findById(id)
            .orElseThrow(() -> new DividaNotFoundException("Divida não encontrada."));
        return convert.converter(divida);
    }

    public Page<DividaDTO> listarTodasAsDividas(Pageable pageable) {

        Page<Divida> dividas = dividaRepository.findAll(pageable);


        return dividas.map(convert::converter);
    }

    public Page<DividaDTO> encontrarPorCpf(String cpf, Pageable pageable) {

        String cpfLimpo = cpf.replaceAll("[^0-9]", "");

        Page<Divida> divida = dividaRepository.findByCpfDevedor(cpfLimpo, pageable);


        return divida.map(convert::converter);
    }

    public DividaResponse atualizarDivida(Long id, DividaDTO dividaDTO) {

        Divida dividaExistente = dividaRepository.findById(id)
        .orElseThrow(() -> new DividaNotFoundException("Divida não existe!"));

        String cpfLimpo = dividaDTO.cpfDevedor().replaceAll("[^0-9]", "");

        dividaExistente.setCpfDevedor(cpfLimpo);
        dividaExistente.setValorComDesconto(dividaDTO.valorComDesconto());
        dividaExistente.setValorComJuros(dividaDTO.valorComJuros());
        dividaExistente.setValorPego(dividaDTO.valorPego());
        

        Divida dividaSalva = dividaRepository.save(dividaExistente);

        return new DividaResponse(dividaSalva.getId(), dividaSalva.getCpfDevedor(), dividaSalva.getValorPego());
    }

    public void deletarDivida(Long id) {

        if (!dividaRepository.existsById(id)) {
            throw new DividaNotFoundException("Essa divida " + id + " não existe para deletar");
        }

        dividaRepository.deleteById(id);
    }

}
