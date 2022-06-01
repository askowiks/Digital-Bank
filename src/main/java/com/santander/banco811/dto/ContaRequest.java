package com.santander.banco811.dto;
import com.santander.banco811.model.TipoConta;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContaRequest {
    private Integer id;
    private Integer numero;
    private Integer agencia;
    private TipoConta tipoConta;
}
