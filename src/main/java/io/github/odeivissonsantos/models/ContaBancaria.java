package io.github.odeivissonsantos.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,  property = "id")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ContaBancaria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String tipo;
	
	@Column
	private String agencia;
	
	@Column
	private String numero;
	
	@Column
	private String pix;
	
	@OneToOne(mappedBy = "contaBancaria")
	private PessoaModel pessoa;
	
	
	

}
