package com.kristonmoney.api.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.kristonmoney.api.model.Lancamento;
import com.kristonmoney.api.model.Pessoa;
import com.kristonmoney.api.repository.LancamentoRepository;
import com.kristonmoney.api.repository.PessoaRepository;
import com.kristonmoney.api.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	public Lancamento alterarLancamento(Long codigo, Lancamento lancamento) {
		Lancamento lancamentoEncontrado = buscarLancamentoPeloCodigo(codigo);
		BeanUtils.copyProperties(lancamento, lancamentoEncontrado, "codigo");
		return lancamentoRepository.save(lancamentoEncontrado);
	}

	private Lancamento buscarLancamentoPeloCodigo(Long codigo) {
		Lancamento lancamentoEncontrado = lancamentoRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		return lancamentoEncontrado;
	}

	public Lancamento cadastrarLancamento(@Valid Lancamento lancamento) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo());
		if (pessoa.isEmpty() || !pessoa.get().getAtivo()) {
			throw new PessoaInexistenteOuInativaException();
		}
		return lancamentoRepository.save(lancamento);
	}
	
	
}
