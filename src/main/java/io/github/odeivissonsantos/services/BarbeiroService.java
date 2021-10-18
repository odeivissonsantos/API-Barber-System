package io.github.odeivissonsantos.services;

import io.github.odeivissonsantos.exceptions.ObjectNotFoundException;
import io.github.odeivissonsantos.models.Barbeiro;
import io.github.odeivissonsantos.models.Cliente;
import io.github.odeivissonsantos.repositorys.BarbeiroRepository;
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
public class BarbeiroService {

    private final BarbeiroRepository barbeiroRepository;

    public BarbeiroService(BarbeiroRepository barbeiroRepository) {
        this.barbeiroRepository = barbeiroRepository;
    }

    /*
     * @return: Retorna uma lista de barbeiros cadastrados na base de dados.
     */
    public List<Barbeiro> listarTodosBarbeiros() {
        return barbeiroRepository.findAll();
    }

    /*
     * @param: id
     * @return: Busca um Baribeiro na base de dados.
     */
    public Barbeiro buscarBarbeiroPorId(Long id) {
        return this.verificaSeExisteBarbeiro(id);
    }

    /*
     * @return: Retorna a criação de um Barbeiro na base de dados.
     */
    public Barbeiro criarBarbeiro(Barbeiro obj) {
        obj.setId(null);
        obj.setDataCadastro(LocalDate.now());
        obj = barbeiroRepository.save(obj);
        return obj;
    }

    /*
     * @param: id e entidade
     * @return: Verifica se existe um Barbeiro na base de dados e atualiza suas informações
     */
    public Barbeiro atualizarBarbeiro(Long id, Barbeiro obj) {
        Barbeiro newObj = verificaSeExisteBarbeiro(id);
        atualizaData(newObj, obj);
        return barbeiroRepository.save(newObj);
    }

    //Verifica se existe um Barbeiro na base de dados e deleta.
    public void deletarBarbeiro(Long id) {
        verificaSeExisteBarbeiro(id);
        barbeiroRepository.deleteById(id);
    }

    /*
     * método com a responsabilidade em passar os novos dados de um
       Barbeiro para um já existente na base de dados.
     */
    private void atualizaData(Barbeiro newObj, Barbeiro obj) {
        newObj.setNomeCompleto(obj.getNomeCompleto());
        newObj.setCpf(obj.getCpf());
        newObj.setTelefone(obj.getTelefone());
        newObj.setEmail(obj.getEmail());
        newObj.setContaBancaria(obj.getContaBancaria());
    }

    /*
     * @param: id
     * @return: verifica existe Barbeiro na base de dados, caso contrario é lançado uma excessão
     */
    private Barbeiro verificaSeExisteBarbeiro(Long id) throws ObjectNotFoundException {
        return barbeiroRepository.findById(id)
            .orElseThrow(() -> new ObjectNotFoundException("Barbeiro com id: "+ id +" não encontrado, Tipo: " + Barbeiro.class.getName()));
    }


}
