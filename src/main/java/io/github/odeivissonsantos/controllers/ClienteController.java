package io.github.odeivissonsantos.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import io.github.odeivissonsantos.dtos.ClienteDTO;
import io.github.odeivissonsantos.models.Cliente;
import io.github.odeivissonsantos.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

/**
 * @author: Deivisson Santos
 * @version: 2.0
 * @Email: deivissonsantos@hotmail.com
 * @Contato: (71) 99188-8419 (WhatsApp)
 */

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/v2/clientes")
public class ClienteController {

	public final ClienteService clienteService;

	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}


	@Operation(description = "Lista todos os clientes cadastrados na base de dados")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Lista com todos clientes")
	})
	@GetMapping
	public ResponseEntity<List<Cliente>> listarTodosClientes() {
		List<Cliente> list = clienteService.listarTodosClientes();
		List<ClienteDTO> listDTO = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(clienteService.listarTodosClientes());
	}

	@Operation(description = "Retorna um cliente por ID")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Lista os detalhes de um cliente por ID")
	})
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long id) {
		Cliente obj = clienteService.buscarClientePorId(id);
		return ResponseEntity.ok().body(obj);
	}

	@Operation(description = "Insere um novo cliente na base de dados")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Cria um novo cliente")
	})
	@PostMapping
	public ResponseEntity<Cliente> criarCliente(@Valid @RequestBody Cliente obj) {
		Cliente newObj= clienteService.criarCliente(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(newObj);
	}

	@Operation(description = "Atualiza um cliente")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Atualiza um cliente cadastrado base de dados")
	})
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id,
			 @Valid @RequestBody Cliente obj) {
		Cliente newObj = clienteService.atualizarCliente(id, obj);
		return ResponseEntity.ok().body(newObj);
	}

	@Operation(description = "Exclui um cliente")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "204", description = "Exclui um cliente passando uum id")
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
		clienteService.deletarCliente(id);
		return ResponseEntity.noContent().build();
	}

}
