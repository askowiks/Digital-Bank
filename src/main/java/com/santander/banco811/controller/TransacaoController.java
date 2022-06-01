package com.santander.banco811.controller;

import com.santander.banco811.dto.TransacaoRequest;
import com.santander.banco811.model.TipoTransacao;
import com.santander.banco811.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    TransacaoService transacaoService;

    @GetMapping
    public Page<TransacaoRequest> listar(@PageableDefault(size=10)Pageable paginacao){
        return transacaoService.listar(paginacao);
    }

    @GetMapping("/{tipoTransacao}")
    public List<TransacaoRequest> listarPorTipo(@PathVariable("tipoTransacao") TipoTransacao tipoTransacao) {
        return transacaoService.listarPorTipo(tipoTransacao);
    }

    @PostMapping
    public TransacaoRequest criar(@RequestBody TransacaoRequest novaTransacao){
        return transacaoService.criar(novaTransacao);
    }

}
