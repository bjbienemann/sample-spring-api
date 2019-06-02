package com.agilliza.api.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.agilliza.api.model.entity.Lancamento;
import com.agilliza.api.repository.filter.LancamentoFilter;
import com.agilliza.api.repository.projection.LancamentoProjection;

public interface LancamentoRepositoryQuery {
	
	Page<Lancamento> filter(LancamentoFilter filter, Pageable pageable);
	
	Page<LancamentoProjection> resumir(LancamentoFilter filter, Pageable pageable);
}
