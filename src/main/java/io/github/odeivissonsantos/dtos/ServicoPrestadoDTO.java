package io.github.odeivissonsantos.dtos;

import io.github.odeivissonsantos.models.ServicoPrestado;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class ServicoPrestadoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private BigDecimal preco;

    public ServicoPrestadoDTO(ServicoPrestado obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.preco = obj.getPreco();
    }
}
