package com.kristonmoney.api.resource;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kristonmoney.api.event.RecursoCriadoEvent;
import com.kristonmoney.api.model.Pessoa;
import com.kristonmoney.api.repository.PessoaRepository;
import com.kristonmoney.api.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESSOA_PESQUISAR') and hasAuthority('SCOPE_read')")
	public Page<Pessoa> pesquisarPessoas(@RequestParam(required = false, defaultValue = "") String nome, Pageable pageable) {
		return pessoaRepository.findByNomeContaining(nome, pageable);
	}

	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESSOA_PESQUISAR') and hasAuthority('SCOPE_read')")
	public ResponseEntity<Pessoa> buscarPessoaPeloCodigo(@PathVariable Long codigo){
		Optional<Pessoa> pessoaEncontrada = pessoaRepository.findById(codigo);
		return pessoaEncontrada.isPresent() ? 
				ResponseEntity.ok(pessoaEncontrada.get()) : 
				ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_PESSOA_CADASTRAR') and hasAuthority('SCOPE_write')")
	public ResponseEntity<Pessoa> cadastrarPessoa(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response){
		Pessoa pessoaCadastrada = pessoaRepository.save(pessoa);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaCadastrada.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaCadastrada);
	}

	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESSOA_ALTERAR') and hasAuthority('SCOPE_write')")
	public ResponseEntity<Pessoa> alterarPessoa(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa){
		Pessoa pessoaAtualizada = pessoaService.alterarPessoa(codigo, pessoa);
		return ResponseEntity.ok(pessoaAtualizada);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_PESSOA_EXCLUIR') and hasAuthority('SCOPE_write')")
	public void excluirPessoa (@PathVariable Long codigo) {
		pessoaRepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_PESSOA_ALTERAR') and hasAuthority('SCOPE_write')")
	public void atualizarSituacaoPessoa(@PathVariable Long codigo, @RequestBody Boolean situacao) {
		pessoaService.atualizarSituacaoPessoa(codigo, situacao);
	}
	
	
}
