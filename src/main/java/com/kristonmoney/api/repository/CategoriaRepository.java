package com.kristonmoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kristonmoney.api.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
