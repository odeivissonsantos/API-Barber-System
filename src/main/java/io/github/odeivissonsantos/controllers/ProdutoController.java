package io.github.odeivissonsantos.controllers;

import io.github.odeivissonsantos.dtos.ProdutoDTO;
import io.github.odeivissonsantos.models.Produto;
import io.github.odeivissonsantos.services.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Deivisson Santos
 * @version: 2.0
 * @Email: deivissonsantos@hotmail.com
 * @Contato: (71) 99188-8419 (WhatsApp)
 */

@RestController
@RequestMapping("/api/v2/produtos")
@CrossOrigin("*")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @Operation(description = "Lista todos os produtos cadastrados na base de dados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista com todos produtos")
    })
    @GetMapping
    public ResponseEntity<List<Produto>> listarTodosProdutos() {
        List<Produto> list = produtoService.listarTodosProdutos();
        List<ProdutoDTO> listDTO = list.stream().map(obj -> new ProdutoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(produtoService.listarTodosProdutos());
    }

    @Operation(description = "Retorna um produto por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista os detalhes de um produto por ID")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Long id) {
        Produto obj = produtoService.buscarProdutoPorId(id);
        return ResponseEntity.ok().body(obj);
    }

    @Operation(description = "Insere um novo produto na base de dados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cria um novo produto")
    })
    @PostMapping
    public ResponseEntity<Produto> criarProduto(@Valid @RequestBody Produto obj) {
        Produto newObj = produtoService.criarProduto(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(newObj);
    }

    @Operation(description = "Atualiza um produto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Atualiza um produto cadastrado base de dados")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @Valid @RequestBody Produto obj){
        Produto newObj = produtoService.atualizarProduto(id, obj);
        return ResponseEntity.ok().body(newObj);
    }

    @Operation(description = "Exclui um produto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Exclui um produto passando uum id")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id){
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }
}
