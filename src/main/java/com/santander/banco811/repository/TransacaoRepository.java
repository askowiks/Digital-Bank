package com.santander.banco811.repository;

import com.santander.banco811.model.TipoTransacao;
import com.santander.banco811.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {

    @Query("select t from Transacao t " +
            "where t.tipoTransacao = :tipoTransacao")
    List<Transacao> findByTipoTransacao(@Param("tipoTransacao")TipoTransacao tipoTransacao);

}
