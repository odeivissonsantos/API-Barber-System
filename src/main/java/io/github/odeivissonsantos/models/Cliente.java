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
@Table(name = "cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String nomeCompleto;

	@Column
	private String cpf;

	@Column
	private String email;

	@Column
	private String telefone;

	@Column(updatable = false)
	@JsonFormat(pattern = "dd/MM/yyy")
	private LocalDate dataCadastro;

	@OneToOne(mappedBy = "cliente")
	private Agendamento agendamento;

}
