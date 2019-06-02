package com.agilliza.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.agilliza.api.model.entity.Lancamento;
import com.agilliza.api.model.entity.Pessoa;
import com.agilliza.api.repository.LancamentoRepository;
import com.agilliza.api.repository.PessoaRepository;
import com.agilliza.api.repository.filter.LancamentoFilter;
import com.agilliza.api.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	public Page<Lancamento> filter(LancamentoFilter filter, Pageable pageable) {
		return lancamentoRepository.filter(filter, pageable);
	}

	public Lancamento findById(Long id) {
		Optional<Lancamento> lancamentoOptional = lancamentoRepository.findById(id);

		return lancamentoOptional.get();
	}

	public Lancamento save(Lancamento lancamento) {
		Pessoa pessoa = lancamento.getPessoa();
		Optional<Pessoa> pessoaOptional = pessoaRepository.findById(pessoa.getId());
		if (pessoaOptional.isPresent() && pessoaOptional.get().getAtivo()) {
			return lancamentoRepository.save(lancamento);			
		}

		throw new PessoaInexistenteOuInativaException();
	}

	public void delete(Long id) {
		lancamentoRepository.deleteById(id);
	}

}
