package com.kristonmoney.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table (name = "lancamento")
public class Lancamento {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotNull(message = "{mensagem-campo-obrigatorio}")
	@Size(max=200, message = "{mensagem-caracteres-max}")
	private String descricao;
	
	@Column (name = "data_vencimento")
	@NotNull(message = "{mensagem-campo-obrigatorio}")
	private LocalDate dataVencimento;
	
	@Column (name = "data_pagamento")
	private LocalDate dataPagamento;
	
	@NotNull(message = "{mensagem-campo-obrigatorio}")
	private BigDecimal valor;
	
	@Size(max=500, message = "{mensagem-caracteres-max}")
	private String observacao;
	
	@NotNull(message = "{mensagem-campo-obrigatorio}")
	@Enumerated(EnumType.STRING)
	private TipoLancamento tipo;
	
	
	@ManyToOne
	@JoinColumn (name = "codigo_categoria")
	@NotNull(message = "{mensagem-campo-obrigatorio}")
	private Categoria categoria;
	
	
	@ManyToOne
	@JoinColumn (name = "codigo_pessoa")
	@NotNull(message = "{mensagem-campo-obrigatorio}")
	private Pessoa pessoa;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public TipoLancamento getTipo() {
		return tipo;
	}

	public void setTipo(TipoLancamento tipo) {
		this.tipo = tipo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lancamento other = (Lancamento) obj;
		return Objects.equals(codigo, other.codigo);
	}
	
	
}
