package io.github.odeivissonsantos.dtos;

import io.github.odeivissonsantos.models.Produto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: Deivisson Santos
 * @version: 2.0
 * @Email: deivissonsantos@hotmail.com
 * @Contato: (71) 99188-8419 (WhatsApp)
 */
public class ProdutoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private String marca;
    private BigDecimal preco;
    private String codigoEan;

    public ProdutoDTO(Produto obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.marca = obj.getMarca();
        this.preco = obj.getPreco();
        this.codigoEan = obj.getCodigoEan();
    }
}
