package io.github.odeivissonsantos.services;

import io.github.odeivissonsantos.exceptions.ObjectNotFoundException;
import io.github.odeivissonsantos.models.ServicoPrestado;
import io.github.odeivissonsantos.repositorys.ServicoPrestadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Deivisson Santos
 * @version: 2.0
 * @Email: deivissonsantos@hotmail.com
 * @Contato: (71) 99188-8419 (WhatsApp)
 */

@Service
public class ServicoPrestadoService {

    private final ServicoPrestadoRepository servicoPrestadoRepository;

    public ServicoPrestadoService(ServicoPrestadoRepository servicoPrestadoRepository) {
        this.servicoPrestadoRepository = servicoPrestadoRepository;
    }

    /*
     * @return: Retorna uma lista de Servicos cadastrados na base de dados.
     */
    public List<ServicoPrestado> listarTodosServicos() {
        return servicoPrestadoRepository.findAll();
    }

    /*
     * @param: id
     * @return: Busca um Servico na base de dados.
     */
    public ServicoPrestado buscarServicoPorId(Long id) {
        return this.verificaSeExisteServico(id);
    }

    /*
     * @return: Retorna a criação de um Servico na base de dados.
     */
    public ServicoPrestado criarServico(ServicoPrestado obj) {
        obj.setId(null);
        obj = servicoPrestadoRepository.save(obj);
        return obj;
    }

    /*
     * @param: id e entidade
     * @return: Verifica se existe um Servico na base de dados e atualiza suas informações
     */
    public ServicoPrestado atualizarServico(Long id, ServicoPrestado obj) {
        ServicoPrestado newObj = verificaSeExisteServico(id);
        atualizaData(newObj, obj);
        return servicoPrestadoRepository.save(newObj);
    }

    //Verifica se existe um Serviço na base de dados e deleta.
    public void deletarServico(Long id) {
        verificaSeExisteServico(id);
        servicoPrestadoRepository.deleteById(id);
    }

    /*
     * método com a responsabilidade em passar os novos dados de um
       Servico para um já existente na base de dados.
     */
    private void atualizaData(ServicoPrestado newObj, ServicoPrestado obj) {
        newObj.setNome(obj.getNome());
        newObj.setPreco(obj.getPreco());
        newObj.setAgendamento(obj.getAgendamento());
    }

    /*
     * @param: id
     * @return: verifica existe um Serviço na base de dados, caso contrario é lançado uma excessão
     */
    private ServicoPrestado verificaSeExisteServico(Long id) throws ObjectNotFoundException {
        return servicoPrestadoRepository.findById(id)
            .orElseThrow(() -> new ObjectNotFoundException("Servico com id: "+ id +" não encontrado, Tipo: " + ServicoPrestado.class.getName()));
    }
}
