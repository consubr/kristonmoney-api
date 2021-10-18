package com.kristonmoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kristonmoney.api.model.Lancamento;
import com.kristonmoney.api.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {

}
