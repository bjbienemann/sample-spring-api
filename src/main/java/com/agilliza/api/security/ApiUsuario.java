package com.agilliza.api.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.agilliza.api.model.entity.Usuario;

public class ApiUsuario extends User {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	
	public ApiUsuario(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getEmail(), usuario.getSenha(), authorities);
		this.usuario = usuario;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

}
