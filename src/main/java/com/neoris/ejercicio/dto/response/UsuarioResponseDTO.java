package com.neoris.ejercicio.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioResponseDTO {

	private String nombre;
	private String apellidos;
	private String observaciones;
}
