package io.github.odeivissonsantos.models;

import javax.persistence.*;

import io.github.odeivissonsantos.enums.TipoConta;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: Deivisson Santos
 * @version: 2.0
 * @Email: deivissonsantos@hotmail.com
 * @Contato: (71) 99188-8419 (WhatsApp)
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_conta_bancaria")
public class ContaBancaria implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private TipoConta tipoConta;
	
	@Column(name = "agencia")
	private String agencia;
	
	@Column(name = "numero")
	private String numero;
	
	@Column(name = "chave_pix")
	private String chavePix;
	
	@OneToOne(mappedBy = "contaBancaria")
	private Barbeiro barbeiro;
	
	
	

}
