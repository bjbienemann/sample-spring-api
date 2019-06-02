package com.agilliza.api.repository.pessoa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.agilliza.api.model.entity.Pessoa;
import com.agilliza.api.repository.filter.PessoaFilter;

public interface PessoaRepositoryQuery {
	
	Page<Pessoa> filter(PessoaFilter filter, Pageable pageable);
}
