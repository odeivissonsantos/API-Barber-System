package io.github.odeivissonsantos.controllers;

import java.util.List;
import java.util.Optional;

import io.github.odeivissonsantos.models.Cliente;
import io.github.odeivissonsantos.services.ClienteService;
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

/**
 * @author: Deivisson Santos
 * @version: 2.0
 * @Email: deivissonsantos@hotmail.com
 * @Contato: (71) 99188-8419 (WhatsApp)
 */

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/clientes")
public class ClienteController {

	public final ClienteService clienteService;

	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}


	@GetMapping
	public ResponseEntity<List<Cliente>> listarTodos() {
		return ResponseEntity.ok().body(clienteService.findAll());
	}

	@PostMapping
	public ResponseEntity<Cliente> salvar( @RequestBody Cliente cliente) {
		return ResponseEntity.created(null).body(clienteService.save(cliente));
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Optional<Cliente>> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok().body(clienteService.findById(id));
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> atualizar(@PathVariable Long id,
			 @RequestBody Cliente clienteAtualizado) {
		clienteAtualizado.setId(id);
		clienteService.save(clienteAtualizado);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		clienteService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
