package com.agilliza.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agilliza.api.model.entity.Pessoa;
import com.agilliza.api.repository.pessoa.PessoaRepositoryQuery;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>, PessoaRepositoryQuery {

}
