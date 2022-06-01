package com.santander.banco811.model;

import com.santander.banco811.dto.ContaRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "conta")
@Entity
@Getter @Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "agencia")
    private Integer agencia;

    @Column(name = "data_criacao")
    @CreatedDate
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    @LastModifiedDate
    private LocalDateTime dataAtualizacao;

    @Column(name = "saldo")
    private BigDecimal saldo;

    @Column(name = "tipo_conta")
    @Enumerated(EnumType.STRING)
    private TipoConta tipoConta;

    //Relacionamento: Lado N
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    //Relacionamento: Lado 1
    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
    private List<Transacao> transacoes;

    public Conta(ContaRequest contaRequest) {
        if (contaRequest != null) {
            id = contaRequest.getId();
            numero = contaRequest.getNumero();
            agencia = contaRequest.getAgencia();
            dataCriacao = LocalDateTime.now();
            saldo = new BigDecimal("0.00");
            tipoConta = contaRequest.getTipoConta();
        }
    }
}
