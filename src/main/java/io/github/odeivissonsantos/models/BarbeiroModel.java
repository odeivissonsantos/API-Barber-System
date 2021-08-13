package io.github.odeivissonsantos.models;

import java.util.List;

import javax.persistence.Entity;

import javax.persistence.OneToMany;

@Entity(name = "barbeiro")
public class BarbeiroModel extends PessoaModel {

	public BarbeiroModel() {
		super();
	}

	@OneToMany
	private List<ServicoPrestadoModel> servicoPrestado;

}
