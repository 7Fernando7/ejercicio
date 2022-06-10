package com.neoris.ejercicio.modelo.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;


@Entity
@Data
@Table(name="usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@NotNull
	private String nombre;
	@NotNull
	private String apellidos;
	@Column(name = "e_mail")
	@NotNull
	@Email
	private String email;
	@NotNull
	@Pattern(regexp = "[0-9]{7,8}[A-Za-z]")
	private String dni;
	private String observaciones;
	
	public Usuario() {
		super();
	}
		
}
