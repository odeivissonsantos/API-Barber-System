package io.github.odeivissonsantos.controllers;

import io.github.odeivissonsantos.models.Venda;
import io.github.odeivissonsantos.services.VendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * @author: Deivisson Santos
 * @version: 2.0
 * @Email: deivissonsantos@hotmail.com
 * @Contato: (71) 99188-8419 (WhatsApp)
 */

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/v2/vendas")
public class VendaController {

    private final VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @Operation(description = "Lista todos as vendas cadastrados na base de dados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista com todas vendas")
    })
    @GetMapping
    public ResponseEntity<List<Venda>> listarTodasVendas(){
        return ResponseEntity.ok().body(vendaService.listarTodasVendas());
    }

    @Operation(description = "Retorna uma Venda por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista os detalhes de uma Venda por ID")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Venda> buscarVendaPorId(@PathVariable Long id) {
        Venda obj = vendaService.buscarVendaPorId(id);
        return ResponseEntity.ok().body(obj);
    }

    @Operation(description = "Insere uma nova Venda na base de dados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cria uma nova Venda")
    })
    @PostMapping
    public ResponseEntity<Venda> criarVenda(@RequestBody Venda obj) {
        Venda newObj = vendaService.criarVenda(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(newObj);
    }

    @Operation(description = "Atualiza uma Venda")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Atualiza uma Venda cadastrada base de dados")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Venda> atualizarVenda(@PathVariable Long id,
                                                            @Valid @RequestBody Venda obj) {
        Venda newObj = vendaService.atualizarVenda(id, obj);
        return ResponseEntity.ok().body(newObj);
    }

    @Operation(description = "Exclui uma Venda")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Exclui uma Venda passando uum id")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVenda(@PathVariable Long id) {
        vendaService.deletarVenda(id);
        return ResponseEntity.noContent().build();
    }

}
