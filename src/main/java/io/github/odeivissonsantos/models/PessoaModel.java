package io.github.odeivissonsantos.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "pessoa")
public abstract class PessoaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull(message = "Campo nome é obrigatório")
	private String nome;

	@CPF
	@NotNull(message = "Campo CPF é obrigatório")
	@Size(min = 1, max = 11)
	private String cpf;

	@Email
	@NotNull(message = "Campo email é obrigatório")
	private String email;

	@NotNull(message = "Campo telefone é obrigatório")
	private String telefone;

	@Column(updatable = false)
	@JsonFormat(pattern = "dd/MM/yyy")
	private LocalDate dataCadastro;


	@PrePersist
	public void prePersist() {
		this.setDataCadastro(LocalDate.now());
	}

}
