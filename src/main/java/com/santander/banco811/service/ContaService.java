package com.santander.banco811.service;

import com.santander.banco811.dto.ContaRequest;
import com.santander.banco811.dto.ContaResponse;
import com.santander.banco811.model.Conta;
import com.santander.banco811.model.Usuario;
import com.santander.banco811.repository.ContaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContaService {

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    ModelMapper modelMapper;

    public List<ContaResponse> getAll(Integer id) {
        if (id != null) {
            return Arrays.asList(modelMapper.map(getById(id), ContaResponse.class));
        } else {
            return contaRepository.findAll().stream()
                    .map(item -> modelMapper.map(item, ContaResponse.class))
                    .collect(Collectors.toList());
        }
    }

    public ContaResponse create(Usuario usuario, ContaRequest contaRequest) {
        Conta conta = new Conta(contaRequest);
        conta.setUsuario(usuario);
        contaRepository.save(conta);
        return new ContaResponse(conta);
    }

    public Conta getById(Integer id) {
        return contaRepository.findById(id).get();
    }

    public Conta update(ContaRequest contaRequest, Integer id) {
        Conta conta = getById(id);

        conta.setNumero(contaRequest.getNumero());
        conta.setAgencia(contaRequest.getAgencia());
        conta.setTipoConta(contaRequest.getTipoConta());

        return contaRepository.save(conta);
    }

    public void delete(Integer id) {
        var conta = getById(id);
        contaRepository.delete(conta);
    }
}
