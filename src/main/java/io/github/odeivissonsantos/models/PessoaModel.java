package io.github.odeivissonsantos.models;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,  property = "id")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "pessoa")
public abstract class PessoaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String nome;

	@Column
	private String cpf;

	@Column
	private String email;

	@Column
	private String telefone;

	@Column(updatable = false)
	@JsonFormat(pattern = "dd/MM/yyy")
	private LocalDate dataCadastro;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	private ContaBancaria contaBancaria;

	@PrePersist
	public void prePersist() {
		this.setDataCadastro(LocalDate.now());
	}

}
