package io.github.odeivissonsantos.dtos;

import io.github.odeivissonsantos.models.Cliente;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * @author: Deivisson Santos
 * @version: 2.0
 * @Email: deivissonsantos@hotmail.com
 * @Contato: (71) 99188-8419 (WhatsApp)
 */

@Data
@RequiredArgsConstructor
public class ClienteDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nomeCompleto;
    private String cpf;
    private String telefone;
    private String email;

    public ClienteDTO(Cliente obj) {
        this.id = obj.getId();
        this.nomeCompleto = obj.getNomeCompleto();
        this.cpf = obj.getCpf();
        this.telefone = obj.getTelefone();
        this.email = obj.getEmail();
    }
}
