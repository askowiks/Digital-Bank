package com.santander.banco811.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "transacao")
@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tipo_transacao")
    @Enumerated(EnumType.STRING)
    private TipoTransacao tipoTransacao;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "data_criacao")
    @CreatedDate
    private LocalDateTime dataCriacao;

    //Relacionamento: Lado N
    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;

}
