package io.github.odeivissonsantos.models;


import javax.persistence.Entity;
import javax.persistence.OneToOne;


@Entity(name = "cliente")
public class ClienteModel extends PessoaModel {

	
	@OneToOne(mappedBy = "cliente")
	private Agendamento agendamento;
	
	public ClienteModel() {
		super();
	}

}
