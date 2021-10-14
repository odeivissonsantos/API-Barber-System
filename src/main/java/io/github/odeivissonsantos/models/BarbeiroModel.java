package io.github.odeivissonsantos.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity(name = "barbeiro")
@Data
public class BarbeiroModel extends PessoaModel {
	
	
	@OneToOne(mappedBy = "barbeiro")
	private Agendamento agendamento;
	
	public BarbeiroModel() {
		super();
	}



}
