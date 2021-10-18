package com.kristonmoney.api.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kristonmoney.api.event.RecursoCriadoEvent;
import com.kristonmoney.api.model.Categoria;
import com.kristonmoney.api.repository.CategoriaRepository;
import com.kristonmoney.api.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	
	//Lista todas as categorias
	@GetMapping 
	@PreAuthorize("hasAuthority('ROLE_CATEGORIA_PESQUISAR') and hasAuthority('SCOPE_read')")
	public List<Categoria> listarCategorias() {
		return categoriaRepository.findAll();
	}
	
	//Busca a categoria por id
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CATEGORIA_PESQUISAR') and hasAuthority('SCOPE_read')")
	public ResponseEntity<Categoria> buscarCategoriaPeloCodigo(@PathVariable Long codigo) {
		//Cria uma variavel de categoria cujo valor é opcional, ou seja, pode encontrar ou não um valor
		Optional<Categoria> categoriaEncontrada = categoriaRepository.findById(codigo);
		//Se a variável possuir valor, retorna um Status 200 Ok, senão, retorna um Status 404 Not Found
		return categoriaEncontrada.isPresent() ? ResponseEntity.ok(categoriaEncontrada.get()) : ResponseEntity.notFound().build();
	}

	//Cadastra uma nova categoria
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CATEGORIA_CADASTRAR') and hasAuthority('SCOPE_write')")
	public ResponseEntity<Categoria> cadastrarCategoria(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {
		//Cria a variável categoriaSalva para receber o objeto categoria cadastrada
		Categoria categoriaCadastrada = categoriaRepository.save(categoria);
		//Chama um evento para poder inserir um Header Location na resposta
		publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaCadastrada.getCodigo()));
		//No retorno é definido o Status "201 Created",
		//definindo o HEADER "Location" para a nova URI criada acima
		//e passado a categoria salva para o corpo da resposta HTTP
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaCadastrada);
	}

	//Alterar categoria
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CATEGORIA_ALTERAR') and hasAuthority('SCOPE_write')")
	public ResponseEntity<Categoria> alterarCategoria (@PathVariable Long codigo, @Valid @RequestBody Categoria categoria){
		Categoria categoriaAtualizada = categoriaService.alterarCategoria(codigo, categoria);
		return ResponseEntity.ok(categoriaAtualizada);
	}
	
	//Excluir categoria
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_CATEGORIA_EXCLUIR') and hasAuthority('SCOPE_write')")
	public void excluirCategoria(@PathVariable Long codigo) {
		categoriaRepository.deleteById(codigo);
	}
	
	
}
