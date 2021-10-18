package io.github.odeivissonsantos.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @Column(name = "nome", unique = true)
    @NotNull(message = "Campo NOME é Obrigatório!")
    private String nome;

    @Column(name = "codigo_ean", unique = true)
    @NotNull(message = "Campo NOME é Obrigatório!")
    private String codigoEan;

    @Column(name = "preco")
    @NotNull(message = "Campo PREÇO é Obrigatório!")
    private BigDecimal preco;
}
