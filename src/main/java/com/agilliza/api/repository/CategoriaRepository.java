package com.agilliza.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agilliza.api.model.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
