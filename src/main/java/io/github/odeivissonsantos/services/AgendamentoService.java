package io.github.odeivissonsantos.services;

import io.github.odeivissonsantos.exceptions.ObjectNotFoundException;
import io.github.odeivissonsantos.models.Agendamento;
import io.github.odeivissonsantos.models.Barbeiro;
import io.github.odeivissonsantos.models.Cliente;
import io.github.odeivissonsantos.models.ServicoPrestado;
import io.github.odeivissonsantos.repositorys.AgendamentoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final ClienteService clienteService;
    private final BarbeiroService barbeiroService;
    private final ServicoPrestadoService servicoPrestadoService;

    public AgendamentoService(AgendamentoRepository agendamentoRepository,
                              ClienteService clienteService,
                              BarbeiroService barbeiroService,
                              ServicoPrestadoService servicoPrestadoService) {
        this.agendamentoRepository = agendamentoRepository;
        this.clienteService = clienteService;
        this.barbeiroService = barbeiroService;
        this.servicoPrestadoService = servicoPrestadoService;
    }

    /*
     * @return: Retorna uma lista de agendamentos cadastrados na base de dados.
     */
    public List<Agendamento> listarTodosAgendamentos() {
        return agendamentoRepository.findAll();
    }

    /*
     * @param: id
     * @return: Busca um Agendamento na base de dados.
     */
    public Agendamento buscarAgendamentoPorId(Long id) {
        return this.verificaSeExisteAgendamento(id);
    }

    /*
     * @return: Retorna a criação de um Agendamento na base de dados.
     */
    public Agendamento criarBarbeiro(Agendamento obj) {
        Cliente cliente = clienteService.buscarClientePorId(obj.getCliente().getId());
        Barbeiro barbeiro = barbeiroService.buscarBarbeiroPorId(obj.getBarbeiro().getId());
        ServicoPrestado servicoPrestado = servicoPrestadoService.buscarServicoPorId(obj.getServicoPrestado().getId());

        Agendamento agendamento = new Agendamento();
        if(obj.getId() != null) {
            agendamento.setId(obj.getId());
        }

        obj.setCliente(cliente);
        obj.setBarbeiro(barbeiro);
        obj.setServicoPrestado(servicoPrestado);

        obj = agendamentoRepository.save(obj);
        return obj;
    }

    /*
     * @param: id e entidade
     * @return: Verifica se existe um chamado na base de dados e atualiza suas informações
     */
    public Agendamento atualizarBarbeiro(Long id, Agendamento obj) {
        Agendamento newObj = verificaSeExisteAgendamento(id);
        atualizaData(newObj, obj);
        return agendamentoRepository.save(newObj);
    }

    //Verifica se existe um Agendamento na base de dados e deleta.
    public void deletarAgendamento(Long id) {
        verificaSeExisteAgendamento(id);
        agendamentoRepository.deleteById(id);
    }

    /*
     * método com a responsabilidade em passar os novos dados de um
       Agendamento para um já existente na base de dados.
     */
    private void atualizaData(Agendamento newObj, Agendamento obj) {
        newObj.setCliente(obj.getCliente());
        newObj.setBarbeiro(obj.getBarbeiro());
        newObj.setServicoPrestado(obj.getServicoPrestado());
        newObj.setData(obj.getData());
    }

    /*
     * @param: id
     * @return: verifica existe agendamento na base de dados, caso contrario é lançado uma excessão
     */
    private Agendamento verificaSeExisteAgendamento(Long id) throws ObjectNotFoundException {
        return agendamentoRepository.findById(id)
            .orElseThrow(() -> new ObjectNotFoundException("Agendamento com id: "+ id +" não encontrado, Tipo: " + Agendamento.class.getName()));
    }
}
