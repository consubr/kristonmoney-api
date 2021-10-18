package com.kristonmoney.api.resource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kristonmoney.api.event.RecursoCriadoEvent;
import com.kristonmoney.api.exceptionhandler.KristonmoneyExceptionHandler.Erro;
import com.kristonmoney.api.model.Lancamento;
import com.kristonmoney.api.repository.LancamentoRepository;
import com.kristonmoney.api.repository.filter.LancamentoFilter;
import com.kristonmoney.api.repository.projection.ResumoLancamento;
import com.kristonmoney.api.service.LancamentoService;
import com.kristonmoney.api.service.exception.PessoaInexistenteOuInativaException;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_LANCAMENTO_PESQUISAR') and hasAuthority('SCOPE_read')")
	public Page<Lancamento> pesquisarLancamentos(LancamentoFilter lancamentoFilter, Pageable pageable) {
		return lancamentoRepository.filtrar(lancamentoFilter, pageable);
	}
	
	@GetMapping(params = "resumo")
	@PreAuthorize("hasAuthority('ROLE_LANCAMENTO_PESQUISAR') and hasAuthority('SCOPE_read')")
	public Page<ResumoLancamento> resumirLancamentos(LancamentoFilter lancamentoFilter, Pageable pageable) {
		return lancamentoRepository.resumir(lancamentoFilter, pageable);
	}
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_LANCAMENTO_PESQUISAR') and hasAuthority('SCOPE_read')")
	public ResponseEntity<Lancamento> buscarLancamentoPeloCodigo(@PathVariable Long codigo){
		Optional<Lancamento> lancamentoEncontrado = lancamentoRepository.findById(codigo);
		return lancamentoEncontrado.isPresent() ? 
				ResponseEntity.ok(lancamentoEncontrado.get()) : 
				ResponseEntity.notFound().build();
	}
	
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_LANCAMENTO_CADASTRAR') and hasAuthority('SCOPE_write')")
	public ResponseEntity<Lancamento> cadastrarLancamento (@Valid @RequestBody Lancamento lancamento, HttpServletResponse response){
		Lancamento lancamentoCadastrado = lancamentoService.cadastrarLancamento(lancamento);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoCadastrado.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoCadastrado);
	}
	
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_LANCAMENTO_ALTERAR') and hasAuthority('SCOPE_write')")
	public ResponseEntity<Lancamento> alterarLancamento (@PathVariable Long codigo, @Valid @RequestBody Lancamento lancamento){
		Lancamento lancamentoAtualizado = lancamentoService.alterarLancamento(codigo, lancamento);
		return ResponseEntity.ok(lancamentoAtualizado);
	}
	
	
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_LANCAMENTO_EXCLUIR') and hasAuthority('SCOPE_write')")
	public void excluirLancamento (@PathVariable Long codigo) {
		lancamentoRepository.deleteById(codigo);
	}
	
	
	@ExceptionHandler({PessoaInexistenteOuInativaException.class})
	public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex){
		String mensagemUsuario = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();	
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}
	
	
	
}
