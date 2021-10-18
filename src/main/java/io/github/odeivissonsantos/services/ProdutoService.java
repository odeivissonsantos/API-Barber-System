package io.github.odeivissonsantos.services;

import io.github.odeivissonsantos.exceptions.ObjectNotFoundException;
import io.github.odeivissonsantos.models.Produto;
import io.github.odeivissonsantos.repositorys.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    /*
     * @return: Retorna uma lista de produtos cadastrados na base de dados.
     */
    public List<Produto> listarTodosProdutos() {
        return produtoRepository.findAll();
    }

    /*
     * @param: id
     * @return: Busca um Produto na base de dados.
     */
    public Produto buscarProdutoPorId(Long id) {
        return verificaSeExisteProduto(id);
    }

    /*
     * @return: Retorna a criação de um Produto na base de dados.
     */
    public Produto criarProduto(Produto obj) {
        obj.setId(null);
        obj = produtoRepository.save(obj);
        return obj;
    }

    /*
     * @param: id e entidade
     * @return: Verifica se existe um produto na base de dados e atualiza suas informações
     */
    public Produto atualizarProduto(Long id, Produto obj) {
        Produto newObj = verificaSeExisteProduto(id);
        atualizaData(newObj, obj);
        return produtoRepository.save(newObj);
    }

    //Verifica se existe um Produto na base de dados e deleta.
    public void deletarProduto(Long id) {
        verificaSeExisteProduto(id);
        produtoRepository.deleteById(id);
    }

    /*
     * método com a responsabilidade de passar os novos dados de um
       produto para um produto já existente na base de dados.
     */
    private void atualizaData(Produto newObj, Produto obj) {
        newObj.setNome(obj.getNome());
        newObj.setCodigoEan(obj.getCodigoEan());
        newObj.setPreco(obj.getPreco());
    }

    /*
     * @param: id
     * @return: verifica existe Produto na base de dados, caso contrario é lançado uma excessão
     */
    private Produto verificaSeExisteProduto(Long id) throws ObjectNotFoundException {
        return produtoRepository.findById(id)
            .orElseThrow(() -> new ObjectNotFoundException("Produto com "+ id +" não encontrado, Tipo: " + Produto.class.getName()));
    }

}
