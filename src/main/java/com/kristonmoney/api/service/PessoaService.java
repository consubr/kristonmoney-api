package com.kristonmoney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.kristonmoney.api.model.Pessoa;
import com.kristonmoney.api.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	public Pessoa alterarPessoa(Long codigo, Pessoa pessoa) {
		Pessoa pessoaEncontrada = buscarPessoaPeloCodigo(codigo);
		BeanUtils.copyProperties(pessoa, pessoaEncontrada, "codigo");
		return pessoaRepository.save(pessoaEncontrada);
	}


	public void atualizarSituacaoPessoa(Long codigo, Boolean situacao) {
		Pessoa pessoaEncontrada = buscarPessoaPeloCodigo(codigo);
		pessoaEncontrada.setAtivo(situacao);
		pessoaRepository.save(pessoaEncontrada);
	}
	
	
	public Pessoa buscarPessoaPeloCodigo(Long codigo) {
		Pessoa pessoaEncontrada = pessoaRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		return pessoaEncontrada;
	}
	
}
