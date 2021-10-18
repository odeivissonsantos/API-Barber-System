package io.github.odeivissonsantos.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
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

	@NotNull(message = "O campo NOME COMPLETO é obrigatório!")
	@Column(name = "nome_completo")
	private String nomeCompleto;

	@NotNull(message = "O campo CPF é obrigatório!")
	@CPF(message = "Digite um CPF válido!")
	@Column(name = "cpf", unique = true)
	private String cpf;

	@NotNull(message = "O campo EMAIL é obrigatório!")
	@Email(message = "Digite um EMAIL válido!")
	@Column(name = "email", unique = true)
	private String email;

	@NotNull(message = "O campo TELEFONE é obrigatório!")
	@Column(name = "telefone")
	private String telefone;

	@Column(updatable = false)
	@JsonFormat(pattern = "dd/MM/yyy")
	private LocalDate dataCadastro;

	@OneToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
	private ContaBancaria contaBancaria;

}
