package com.kristonmoney.api.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kristonmoney.api.event.RecursoCriadoEvent;

@Component
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent> {

	@Override
	public void onApplicationEvent(RecursoCriadoEvent recursoCriadoEvent) {
		HttpServletResponse response = recursoCriadoEvent.getResponse();
		Long codigo = recursoCriadoEvent.getCodigo();
		
		adicionarHeaderLocation(response, codigo);
	}

	//Recupera a requisição atual (/categorias),
	//concatena o código da categoria criado (/categoria/codigo)
	//e adiciona a requisição completa na URI
	private void adicionarHeaderLocation(HttpServletResponse response, Long codigo) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
				.path("/{codigo}")
				.buildAndExpand(codigo)
				.toUri();
		response.setHeader("Location", uri.toASCIIString());
	}

}
