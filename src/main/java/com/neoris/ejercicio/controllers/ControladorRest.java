package com.neoris.ejercicio.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neoris.ejercicio.dto.response.UsuarioResponseDTO;
import com.neoris.ejercicio.modelo.entities.Usuario;
import com.neoris.ejercicio.services.IntUsuarioServices;

@RestController
@RequestMapping("/rest/usuario")
public class ControladorRest {

	
	@Autowired
	private IntUsuarioServices usuarioService;
	
	@GetMapping("/findAll")
	public ResponseEntity<List<UsuarioResponseDTO>> processFindAll(){
		//return new ResponseEntity<List<Usuario>>(usuarioDao.findAll(), HttpStatus.OK);
		return usuarioService.findAll();
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<UsuarioResponseDTO> processFindById(@PathVariable ("id") int id){
		/*Usuario usuario = usuarioDao.findById(id);
		rabbitMQSender.sendIdUsuario(usuario);
		return usuario != null ?
			new ResponseEntity<Usuario>(usuario, HttpStatus.OK):
			new ResponseEntity<Usuario>(usuario, HttpStatus.NOT_FOUND);
		*/
		return usuarioService.findById(id);
		
	}
	
	@GetMapping("/findByDni/{dni}")
	public ResponseEntity<UsuarioResponseDTO> processFindByDni(@PathVariable ("dni") String dni){
		/*
		Usuario usuario = usuarioDao.findByDni(dni);
		return usuario != null ?
			new ResponseEntity<Usuario>(usuario, HttpStatus.OK):
			new ResponseEntity<Usuario>(usuario, HttpStatus.NOT_FOUND);
		*/
		return usuarioService.findByDni(dni);
	}
	
	@GetMapping("/findByName/{nombre}")
	public ResponseEntity<List<UsuarioResponseDTO>> processfindByName(@PathVariable ("nombre") String nombre){
		/*
		List<Usuario> usuarios = usuarioDao.findByName(nombre);
		return usuarios != null && !usuarios.isEmpty()?
			new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK):
			new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.NOT_FOUND);
		*/
		return usuarioService.findByName(nombre);
	}
	
	@PostMapping("/saveUsuario")
	public ResponseEntity<UsuarioResponseDTO> processSaveUsuario(@Valid @RequestBody Usuario usuario){
		/*
		return usuarioDao.saveUsuario(usuario) ?
			new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED):
			new ResponseEntity<Usuario>(usuario, HttpStatus.BAD_REQUEST);
		*/
		return usuarioService.saveUsuario(usuario);
	}
	
	@PutMapping("/modifyUsuario")
	public ResponseEntity<UsuarioResponseDTO> processModifyUsuario(@RequestBody Usuario usuario){
		/*
		return usuarioDao.modifyUsuario(usuario) ?
			new ResponseEntity<Usuario>(usuario, HttpStatus.OK):
			new ResponseEntity<Usuario>(usuario, HttpStatus.NOT_MODIFIED);
		*/
		return usuarioService.modifyUsuario(usuario);
	}
	
	@DeleteMapping("/deleteUsuario/{id}")
	public ResponseEntity<UsuarioResponseDTO> processDeleteUsuario(@PathVariable ("id") int id){
		/*
		Usuario usuario = usuarioDao.findById(id);
		return usuarioDao.deleteUsuario(id) ?
			new ResponseEntity<Usuario>(usuario, HttpStatus.OK):
			new ResponseEntity<Usuario>(usuario, HttpStatus.NOT_FOUND);
		*/
		return usuarioService.deleteUsuario(id);
	}
}