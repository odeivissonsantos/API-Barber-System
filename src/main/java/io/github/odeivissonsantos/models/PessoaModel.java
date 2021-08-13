package io.github.odeivissonsantos.models;

import java.time.LocalDate;
import java.util.Objects;

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


@Entity(name = "pessoa")
public abstract class PessoaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

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

	public PessoaModel() {
		
	}
	
	

	public PessoaModel(Integer id, @NotNull(message = "Campo nome é obrigatório") String nome,
			@CPF @NotNull(message = "Campo CPF é obrigatório") @Size(min = 1, max = 11) String cpf,
			@Email @NotNull(message = "Campo email é obrigatório") String email,
			@NotNull(message = "Campo telefone é obrigatório") String telefone, LocalDate dataCadastro) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.telefone = telefone;
		this.dataCadastro = dataCadastro;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, dataCadastro, email, id, nome, telefone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PessoaModel other = (PessoaModel) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(dataCadastro, other.dataCadastro)
				&& Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome) && Objects.equals(telefone, other.telefone);
	}

	@Override
	public String toString() {
		return "PessoaModel [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", email=" + email + ", telefone="
				+ telefone + ", dataCadastro=" + dataCadastro + "]";
	}

	@PrePersist
	public void prePersist() {
		this.setDataCadastro(LocalDate.now());
	}

}
