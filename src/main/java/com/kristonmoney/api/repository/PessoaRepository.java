package com.kristonmoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kristonmoney.api.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
