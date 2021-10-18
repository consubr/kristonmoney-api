package com.kristonmoney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.kristonmoney.api.model.Categoria;
import com.kristonmoney.api.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria alterarCategoria(Long codigo, Categoria categoria) {
		Categoria categoriaEncontrada = buscarCategoriaPeloCodigo(codigo);
		BeanUtils.copyProperties(categoria, categoriaEncontrada, "codigo");
		return categoriaRepository.save(categoriaEncontrada);
	}

	private Categoria buscarCategoriaPeloCodigo(Long codigo) {
		Categoria categoriaEncontrada = categoriaRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		return categoriaEncontrada;
	}
	
	
}
