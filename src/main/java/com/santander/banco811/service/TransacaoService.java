package com.santander.banco811.service;

import com.santander.banco811.dto.TransacaoRequest;
import com.santander.banco811.model.Conta;
import com.santander.banco811.model.TipoTransacao;
import com.santander.banco811.model.Transacao;
import com.santander.banco811.repository.ContaRepository;
import com.santander.banco811.repository.TransacaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransacaoService {

    @Autowired
    TransacaoRepository transacaoRepository;

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    ModelMapper modelMapper;


    public Page<TransacaoRequest> listar(Pageable paginacao) {
        return transacaoRepository
                .findAll(paginacao)
                .map( item -> modelMapper.map(item, TransacaoRequest.class));
    }

    public List<TransacaoRequest> listarPorTipo(TipoTransacao tipoTransacao) {
        List<Transacao> byTipoTransacao = transacaoRepository.findByTipoTransacao(tipoTransacao);
        System.out.println("\n\n List size: " + byTipoTransacao.size());
        System.out.println("\n\n " + byTipoTransacao + "\n");
        return byTipoTransacao.stream()
                .map(item -> modelMapper.map(item, TransacaoRequest.class))
                .collect(Collectors.toList());
//        return modelMapper.map(transacaoRepository.findByTipoTransacao(tipoTransacao), new TypeToken<List<Transacao>>() {}.getType());
//        return transacaoRepository.findByTipoTransacao(tipoTransacao);
    }

    public TransacaoRequest criar(TransacaoRequest novaTransacao){
        Conta conta = contaRepository.findById(novaTransacao.getIdConta()).get();
        Transacao transacao = modelMapper.map(novaTransacao, Transacao.class);
        transacao.setConta(conta);
        Transacao savedTransacao = transacaoRepository.save(transacao);
        return modelMapper.map(savedTransacao, TransacaoRequest.class);
    }

}
