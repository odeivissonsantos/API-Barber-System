package io.github.odeivissonsantos.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: Deivisson Santos
 * @version: 2.0
 * @Email: deivissonsantos@hotmail.com
 * @Contato: (71) 99188-8419 (WhatsApp)
 */

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "tb_servico_prestado")
public class ServicoPrestado implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "O campo NOME é obrigatório!")
	@Column(name = "nome")
	private BigDecimal nome;

	@NotNull(message = "O campo PREÇO é obrigatório!")
	@Column(name = "preco")
	private BigDecimal preco;
	
	@OneToOne(mappedBy = "servicoPrestado")
	private Agendamento agendamento;

}
