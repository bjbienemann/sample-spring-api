package com.agilliza.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agilliza.api.model.entity.Lancamento;
import com.agilliza.api.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {

}
