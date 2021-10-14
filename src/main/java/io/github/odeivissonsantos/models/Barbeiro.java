package io.github.odeivissonsantos.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author: Deivisson Santos
 * @version: 2.0
 * @Email: deivissonsantos@hotmail.com
 * @Contato: (71) 99188-8419 (WhatsApp)
 */

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "tb_barbeiro")
public class Barbeiro implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome_completo")
	private String nomeCompleto;

	@Column(name = "cpf")
	private String cpf;

	@Column(name = "email")
	private String email;

	@Column(name = "telefone")
	private String telefone;

	@Column(updatable = false)
	@JsonFormat(pattern = "dd/MM/yyy")
	private LocalDate dataCadastro;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	private ContaBancaria contaBancaria;

	@OneToOne(mappedBy = "barbeiro")
	private Agendamento agendamento;

}
