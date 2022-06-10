package com.neoris.ejercicio.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.neoris.ejercicio.dto.response.UsuarioResponseDTO;
import com.neoris.ejercicio.modelo.entities.Usuario;

public interface IntUsuarioServices {
	
	//Devuelvo un usuario a partir de la id que me proporcionan
	ResponseEntity<UsuarioResponseDTO> findById(int id);
	//Devuelvo una lista de todos los usuarios
	ResponseEntity<List<UsuarioResponseDTO>> findAll();
	//Devuelvo un usuario a partir del DNI que me proporcionan
	ResponseEntity<UsuarioResponseDTO> findByDni(String dni);
	//Devuelvo los usuario cuyo nombre coincide con el que me proporcionan
	ResponseEntity<List<UsuarioResponseDTO>> findByName(String nombre);
	//Grabo un usuario en la BBDD
	ResponseEntity<UsuarioResponseDTO> saveUsuario(Usuario usuario);
	//Modifica el usuario propocionado en la BBDD
	ResponseEntity<UsuarioResponseDTO> modifyUsuario(Usuario usuario);
	//Elimina de la BBDD el usuario cuya idUsuario proporcionamos
	ResponseEntity<UsuarioResponseDTO> deleteUsuario(int id);
}
