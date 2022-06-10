package com.neoris.ejercicio.modelo.dao;

import java.util.List;

import com.neoris.ejercicio.modelo.entities.Usuario;

public interface IntUsuarioDao {

	//devuelve todos los usuarios de la BBDD
	List<Usuario> findAll();
	//devuelve el usuario que corresponde al id proporcionado
	Usuario findById(int id);
	//devuelve el usuario que corresponde al dni proporcionado
	Usuario findByDni (String dni);
	//devuelve los usuarios que corresponden al nombre proporcionado
	List<Usuario> findByName(String nombre);
	//graba el usuario en la BBDD si no existe
	boolean saveUsuario (Usuario usuario);
	//eliminar el usuario de la BBDD si existe
	boolean deleteUsuario (int id);
	//modifica el usuario que propocionamos si existe en la BBDD
	boolean modifyUsuario(Usuario usuario);	
	
}


