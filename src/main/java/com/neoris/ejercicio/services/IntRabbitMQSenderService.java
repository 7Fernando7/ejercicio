package com.neoris.ejercicio.services;

import java.util.List;

import com.neoris.ejercicio.dto.response.UsuarioResponseDTO;

public interface IntRabbitMQSenderService {
	//envio un usuario
	public void sendUsuario(UsuarioResponseDTO usuario);
	//envio una lista de usuarios
	public void sendListaUsuarios(List<UsuarioResponseDTO> listaUsuarios);
}
