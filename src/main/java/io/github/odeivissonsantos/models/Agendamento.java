package io.github.odeivissonsantos.models;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author: Deivisson Santos
 * @version: 2.0
 * @Email: deivissonsantos@hotmail.com
 * @Contato: (71) 99188-8419 (WhatsApp)
 */

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "tb_agendamento")
public class Agendamento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private Barbeiro barbeiro;
	
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private Cliente cliente;
	
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private ServicoPrestado servicoPrestado;
	
	@Column
	@JsonFormat(pattern = "dd/MM/yyy")
	private LocalDate data;

	/*
	@Transient
	private Long barbeiroId;
	@Transient
	private Long clienteId;
	@Transient
	private Long servicoPrestadoId;
	*/

}
