package io.github.odeivissonsantos.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
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
@Table(name = "tb_produto")
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private BigDecimal preco;
}
