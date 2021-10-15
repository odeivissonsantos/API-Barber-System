package io.github.odeivissonsantos.services;

import io.github.odeivissonsantos.exceptions.ObjectNotFoundException;
import io.github.odeivissonsantos.models.Cliente;
import io.github.odeivissonsantos.repositorys.ClienteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * @author: Deivisson Santos
 * @version: 2.0
 * @Email: deivissonsantos@hotmail.com
 * @Contato: (71) 99188-8419 (WhatsApp)
 */

@Service
public class ClienteService {

    public final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    /*
     * @return: Retorna uma lista de clientes cadastrados na base de dados.
     */
    public List<Cliente> listarTodosClientes() {
        return clienteRepository.findAll();
    }

    /*
     * @param: id
     * @return: Busca um Cliente na base de dados.
     */
    public Cliente buscarClientePorId(Long id) throws ObjectNotFoundException {
        return this.verificaSeExisteCliente(id);
    }

    /*
     * @return: Retorna a criação de um cliente na base de dados.
     */
    public Cliente criarCliente(Cliente obj) {
        obj.setId(null);
        obj.setDataCadastro(LocalDate.now());
        obj = clienteRepository.save(obj);
        return obj;
    }

    /*
     * @param: id e entidade
     * @return: Verifica se existe um cliente na base de dados e atualiza suas informações
     */
    public Cliente atualizarCliente(Long id, Cliente obj) {
        Cliente newObj = verificaSeExisteCliente(id);
        atualizaData(newObj, obj);
        return clienteRepository.save(newObj);
    }

    //Verifica se existe um cliente na base de dados e deleta.
    public void deletarCliente(Long id) {
        verificaSeExisteCliente(id);
        clienteRepository.deleteById(id);
    }

    /*
     * método com a responsabilidade em passar os novos dados de um
       cliente para um já existente na base de dados.
     */
    private void atualizaData(Cliente newObj, Cliente obj) {
        newObj.setNomeCompleto(obj.getNomeCompleto());
        newObj.setCpf(obj.getCpf());
        newObj.setTelefone(obj.getTelefone());
        newObj.setEmail(obj.getEmail());
    }

    /*
     * @param: id
     * @return: verifica existe Cliente na base de dados, caso contrario é lançado uma excessão
     */
    private Cliente verificaSeExisteCliente(Long id) throws ObjectNotFoundException {
        return clienteRepository.findById(id)
            .orElseThrow(() -> new ObjectNotFoundException("Cliente com id: "+ id +" não encontrado, Tipo: " + Cliente.class.getName()));
    }
}
