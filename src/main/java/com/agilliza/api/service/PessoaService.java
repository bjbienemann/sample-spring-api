package com.agilliza.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.agilliza.api.model.entity.Pessoa;
import com.agilliza.api.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa save(Long id, Pessoa pessoa) {
		Pessoa pessoaSalva = findById(id);
		BeanUtils.copyProperties(pessoa, pessoaSalva, "id");

		return pessoaRepository.save(pessoaSalva);
	}

	public void salvarAtivo(Long id, Boolean ativo) {
		Pessoa pessoaSalva = findById(id);
		pessoaSalva.setAtivo(ativo);

		pessoaRepository.save(pessoaSalva);
	}

	private Pessoa findById(Long id) {
		return pessoaRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
	}
}
