package com.agilliza.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agilliza.api.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public Optional<Usuario> findByEmail(String email);
}
