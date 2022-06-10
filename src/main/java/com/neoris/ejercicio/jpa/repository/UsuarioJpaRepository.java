package com.neoris.ejercicio.jpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neoris.ejercicio.modelo.entities.Usuario;

public interface UsuarioJpaRepository extends JpaRepository<Usuario, Integer>{
	public Usuario findBydni(String dni);
	public List<Usuario> findBynombre(String nombre);
}
