package io.github.odeivissonsantos.models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity(name = "barbeiro")
public class BarbeiroModel extends PessoaModel {
	
	
	@OneToOne(mappedBy = "barbeiro")
	private Agendamento agendamento;
	
	public BarbeiroModel() {
		super();
	}



}
