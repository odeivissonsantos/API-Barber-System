package io.github.odeivissonsantos.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity(name = "cliente")
public class ClienteModel extends PessoaModel {

	public ClienteModel() {
		super();
	}

	@OneToMany
	private List<ServicoPrestadoModel> servicoPrestado;
}
