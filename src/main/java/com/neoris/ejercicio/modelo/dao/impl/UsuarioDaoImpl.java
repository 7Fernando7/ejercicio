package com.neoris.ejercicio.modelo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neoris.ejercicio.jpa.repository.UsuarioJpaRepository;
import com.neoris.ejercicio.modelo.dao.IntUsuarioDao;
import com.neoris.ejercicio.modelo.entities.Usuario;

@Service
public class UsuarioDaoImpl implements IntUsuarioDao{

	@Autowired
	private UsuarioJpaRepository usuarioRepo;
	
	@Override
	public List<Usuario> findAll() {
		return usuarioRepo.findAll();
	}

	@Override
	public Usuario findById(int id) {
		return usuarioRepo.findById(id).orElse(null);
	}

	@Override
	public Usuario findByDni(String dni) {
		return usuarioRepo.findBydni(dni);
	}

	@Override
	public List<Usuario> findByName(String nombre) {
		return usuarioRepo.findBynombre(nombre);
	}

	@Override
	public boolean saveUsuario(Usuario usuario) {
		if(findById(usuario.getId()) == null) {
			usuarioRepo.save(usuario);
			return true;
		}else
			return false;
	}

	@Override
	public boolean deleteUsuario(int id) {
		if(findById(id) != null){
			usuarioRepo.delete(findById(id));
			return true;
		}else
			return false;
	}

	@Override
	public boolean modifyUsuario(Usuario usuario) {
		if(findById(usuario.getId()) != null) {
			usuarioRepo.save(usuario);
			return true;
		}else
			return false;
	}

}
