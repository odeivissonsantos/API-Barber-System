package io.github.odeivissonsantos.services;

import io.github.odeivissonsantos.exceptions.ObjectNotFoundException;
import io.github.odeivissonsantos.models.*;
import io.github.odeivissonsantos.repositorys.VendaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Deivisson Santos
 * @version: 2.0
 * @Email: deivissonsantos@hotmail.com
 * @Contato: (71) 99188-8419 (WhatsApp)
 */

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ClienteService clienteService;
    private final BarbeiroService barbeiroService;
    private final ProdutoService produtoService;

    public VendaService(VendaRepository vendaRepository,
                        ClienteService clienteService,
                        BarbeiroService barbeiroService,
                        ProdutoService produtoService) {
        this.vendaRepository = vendaRepository;
        this.clienteService = clienteService;
        this.barbeiroService = barbeiroService;
        this.produtoService = produtoService;
    }

    /*
     * @return: Retorna uma lista de Vendas cadastrados na base de dados.
     */
    public List<Venda> listarTodasVendas() {
        return vendaRepository.findAll();
    }

    /*
     * @param: id
     * @return: Busca um Venda na base de dados.
     */
    public Venda buscarVendaPorId(Long id) {
        return this.verificaSeExisteVenda(id);
    }

    /*
     * @return: Retorna a criação de um Venda na base de dados.
     */
    public Venda criarVenda(Venda obj) {
        Cliente cliente = clienteService.buscarClientePorId(obj.getCliente().getId());
        Barbeiro barbeiro = barbeiroService.buscarBarbeiroPorId(obj.getBarbeiro().getId());
        Produto produto = produtoService.buscarProdutoPorId(obj.getProduto().getId());

        Agendamento agendamento = new Agendamento();
        if(obj.getId() != null) {
            agendamento.setId(obj.getId());
        }

        obj.setCliente(cliente);
        obj.setBarbeiro(barbeiro);
        obj.setProduto(produto);

        obj = vendaRepository.save(obj);
        return obj;
    }

    /*
     * @param: id e entidade
     * @return: Verifica se existe um Venda na base de dados e atualiza suas informações
     */
    public Venda atualizarVenda(Long id, Venda obj) {
        Venda newObj = verificaSeExisteVenda(id);
        atualizaData(newObj, obj);
        return vendaRepository.save(newObj);
    }

    //Verifica se existe um Venda na base de dados e deleta.
    public void deletarVenda(Long id) {
        verificaSeExisteVenda(id);
        vendaRepository.deleteById(id);
    }

    /*
     * método com a responsabilidade em passar os novos dados de um
       Venda para um já existente na base de dados.
     */
    private void atualizaData(Venda newObj, Venda obj) {
        newObj.setCliente(obj.getCliente());
        newObj.setBarbeiro(obj.getBarbeiro());
        newObj.setProduto(obj.getProduto());
    }

    /*
     * @param: id
     * @return: verifica existe um Venda na base de dados, caso contrario é lançado uma excessão
     */
    private Venda verificaSeExisteVenda(Long id) throws ObjectNotFoundException {
        return vendaRepository.findById(id)
            .orElseThrow(() -> new ObjectNotFoundException("Venda com id: "+ id +" não encontrado, Tipo: " + Venda.class.getName()));
    }
}
