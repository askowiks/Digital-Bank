package com.santander.banco811.dto;

import com.santander.banco811.model.Conta;
import com.santander.banco811.model.TipoConta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContaResponse {
    private Integer id;
    private Integer numero;
    private Integer agencia;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    private TipoConta tipoConta;

    public ContaResponse(Conta conta) {
        this.id = conta.getId();
        this.numero = conta.getNumero();
        this.agencia = conta.getAgencia();
        this.dataCriacao = conta.getDataCriacao();
        this.dataAtualizacao = conta.getDataAtualizacao();
        this.tipoConta = conta.getTipoConta();
    }
    public static List<ContaResponse> toResponse (List<Conta> contas){
        return contas.stream().map(ContaResponse::new).collect(Collectors.toList());
    }
}
