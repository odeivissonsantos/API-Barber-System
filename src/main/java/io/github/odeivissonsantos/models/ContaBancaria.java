package io.github.odeivissonsantos.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

	@NotNull(message = "O campo NOME DO BANCO é obrigatório!")
	@Column(name = "nome_banco")
	private String nomeBanco;

	@Enumerated(EnumType.STRING)
	private TipoConta tipoConta;

	@NotNull(message = "O campo AGÊNCIA é obrigatório!")
	@Column(name = "agencia")
	private String agencia;

	@NotNull(message = "O campo NÚMERO é obrigatório!")
	@Column(name = "numero")
	private String numero;

	@Column(name = "chave_pix")
	private String chavePix;
	
	@OneToOne(mappedBy = "contaBancaria")
	private Barbeiro barbeiro;

}
