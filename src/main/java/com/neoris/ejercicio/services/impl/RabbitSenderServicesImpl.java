package com.neoris.ejercicio.services.impl;

import java.util.List;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.neoris.ejercicio.dto.response.UsuarioResponseDTO;
import com.neoris.ejercicio.modelo.entities.Usuario;
import com.neoris.ejercicio.services.IntRabbitMQSenderService;

@Service
public class RabbitSenderServicesImpl implements IntRabbitMQSenderService{
	
	@Value("${ejercicio.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${ejercicio.rabbitmq.routingkey}")
	private String routingkey;	
	
	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	@Override
	public void sendUsuario(UsuarioResponseDTO usuario) {
		rabbitTemplate.convertAndSend(exchange, routingkey, usuario);
	}
	@Override 
	public void sendListaUsuarios(List<UsuarioResponseDTO> listaUsuarios) {
		rabbitTemplate.convertAndSend(exchange, routingkey, listaUsuarios);
	}

}
