package com.santander.banco811.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.santander.banco811.model.TipoTransacao;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransacaoRequest {

    private Integer id;
    private TipoTransacao tipoTransacao;
    private BigDecimal valor;
    private LocalDateTime dataCriacao;
    private Integer idConta;

}
