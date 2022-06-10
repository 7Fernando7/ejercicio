package com.neoris.ejercicio.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.neoris.ejercicio.dto.response.UsuarioResponseDTO;
import com.neoris.ejercicio.modelo.dao.IntUsuarioDao;
import com.neoris.ejercicio.modelo.entities.Usuario;
import com.neoris.ejercicio.services.IntRabbitMQSenderService;
import com.neoris.ejercicio.services.IntUsuarioServices;
@Service
public class UsuarioServicesImpl implements IntUsuarioServices{

	@Autowired
	private IntUsuarioDao usuarioDao;
	
	@Autowired
	private IntRabbitMQSenderService rabbitMQSender;
	
	@Override
	public ResponseEntity<UsuarioResponseDTO> findById(int id) {
		Usuario usuario = usuarioDao.findById(id);
		UsuarioResponseDTO usuarioResponse = null;
		if(usuario != null) {
			/*
			usuarioResponse.setNombre(usuario.getNombre());
			usuarioResponse.setApellidos(usuario.getApellidos());
			usuarioResponse.setObservaciones(usuario.getObservaciones());
			*/
			usuarioResponse = UsuarioResponseDTO.builder().nombre(usuario.getNombre())
			.apellidos(usuario.getApellidos())
			.observaciones(usuario.getObservaciones())
			.build();
			rabbitMQSender.sendUsuario(usuarioResponse);
		}
		
		
		//debería mandar al return usuarioResponse, lo hago así para que veamos la diferencia
		//entre usuario y usuarioResponse
		return usuario != null ?
			new ResponseEntity<UsuarioResponseDTO>(usuarioResponse, HttpStatus.OK):
			new ResponseEntity<UsuarioResponseDTO>(usuarioResponse, HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<List<UsuarioResponseDTO>> findAll() {
		List<Usuario> ListaUsuarios = usuarioDao.findAll();
		List<UsuarioResponseDTO> ListaUsuariosResponse = new ArrayList<UsuarioResponseDTO>();
		UsuarioResponseDTO usuarioResponse;
		if(ListaUsuarios != null && !ListaUsuarios.isEmpty()) {
			for(Usuario usuario: ListaUsuarios) {
				usuarioResponse = UsuarioResponseDTO.builder().nombre(usuario.getNombre())
						.apellidos(usuario.getApellidos())
						.observaciones(usuario.getObservaciones())
						.build();
				ListaUsuariosResponse.add(usuarioResponse);
			}
			rabbitMQSender.sendListaUsuarios(ListaUsuariosResponse);
		}
		return new ResponseEntity<List<UsuarioResponseDTO>>(ListaUsuariosResponse, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UsuarioResponseDTO> findByDni(String dni) {
		UsuarioResponseDTO usuarioResponse = null;
		Usuario usuario = usuarioDao.findByDni(dni);
		if(usuario != null) {
			usuarioResponse = UsuarioResponseDTO.builder().nombre(usuario.getNombre())
					.apellidos(usuario.getApellidos())
					.observaciones(usuario.getObservaciones())
					.build();
			rabbitMQSender.sendUsuario(usuarioResponse);
			return new ResponseEntity<UsuarioResponseDTO>(usuarioResponse, HttpStatus.OK);
		}else	
			return new ResponseEntity<UsuarioResponseDTO>(usuarioResponse, HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<List<UsuarioResponseDTO>> findByName(String nombre) {
		List<UsuarioResponseDTO> ListaUsuariosResponse = new ArrayList<UsuarioResponseDTO>();
		UsuarioResponseDTO usuarioResponse;
		List<Usuario> listaUsuarios = usuarioDao.findByName(nombre);
		if(listaUsuarios != null && !listaUsuarios.isEmpty()) {
			for(Usuario usuario: listaUsuarios) {
				usuarioResponse = UsuarioResponseDTO.builder().nombre(usuario.getNombre())
						.apellidos(usuario.getApellidos())
						.observaciones(usuario.getObservaciones())
						.build();
				ListaUsuariosResponse.add(usuarioResponse);
			}
			rabbitMQSender.sendListaUsuarios(ListaUsuariosResponse);
			return new ResponseEntity<List<UsuarioResponseDTO>>(ListaUsuariosResponse, HttpStatus.OK);
		}else
			return new ResponseEntity<List<UsuarioResponseDTO>>(ListaUsuariosResponse, HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<UsuarioResponseDTO> saveUsuario(Usuario usuario) {
		UsuarioResponseDTO usuarioResponse;
		usuarioResponse = UsuarioResponseDTO.builder().nombre(usuario.getNombre())
				.apellidos(usuario.getApellidos())
				.observaciones(usuario.getObservaciones())
				.build();
		//compruebo que no tengo un usuario con ese DNI
		if(usuarioDao.findByDni(usuario.getDni()) == null) {
			if(usuarioDao.saveUsuario(usuario)) {
				rabbitMQSender.sendUsuario(usuarioResponse);
				return new ResponseEntity<UsuarioResponseDTO>(usuarioResponse, HttpStatus.CREATED);
			}
		}
	return new ResponseEntity<UsuarioResponseDTO>(usuarioResponse, HttpStatus.BAD_REQUEST);			
	}

	@Override
	public ResponseEntity<UsuarioResponseDTO> modifyUsuario(Usuario usuario) {
		UsuarioResponseDTO usuarioResponse;
		usuarioResponse = UsuarioResponseDTO.builder().nombre(usuario.getNombre())
				.apellidos(usuario.getApellidos())
				.observaciones(usuario.getObservaciones())
				.build();
		if(usuarioDao.modifyUsuario(usuario)) {
			rabbitMQSender.sendUsuario(usuarioResponse);
			return new ResponseEntity<UsuarioResponseDTO>(usuarioResponse, HttpStatus.OK);
		}else
			return new ResponseEntity<UsuarioResponseDTO>(usuarioResponse, HttpStatus.NOT_MODIFIED);
	}

	@Override
	public ResponseEntity<UsuarioResponseDTO> deleteUsuario(int id) {
		Usuario usuario = usuarioDao.findById(id);
		UsuarioResponseDTO usuarioResponse = null;
		if(usuario != null) {
			usuarioResponse = UsuarioResponseDTO.builder().nombre(usuario.getNombre())
					.apellidos(usuario.getApellidos())
					.observaciones(usuario.getObservaciones())
					.build();
		}
		if(usuarioDao.deleteUsuario(id)) {
			rabbitMQSender.sendUsuario(usuarioResponse);
			return new ResponseEntity<UsuarioResponseDTO>(usuarioResponse, HttpStatus.OK);
		}else
			return new ResponseEntity<UsuarioResponseDTO>(usuarioResponse, HttpStatus.NOT_FOUND);
	}
}
