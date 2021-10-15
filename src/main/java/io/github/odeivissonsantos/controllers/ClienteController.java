package io.github.odeivissonsantos.controllers;

import java.util.List;
import java.util.Optional;

import io.github.odeivissonsantos.models.Cliente;
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

import io.github.odeivissonsantos.repositorys.ClienteRepository;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/clientes")
public class ClienteController {

	public final ClienteRepository repository;

	public ClienteController(ClienteRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	public ResponseEntity<List<Cliente>> listarTodos() {
		return ResponseEntity.ok().body(repository.findAll());
	}

	@PostMapping
	public ResponseEntity<Cliente> salvar( @RequestBody Cliente cliente) {
		return ResponseEntity.created(null).body(repository.save(cliente));
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Optional<Cliente>> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok().body(repository.findById(id));
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> atualizar(@PathVariable Long id,
			 @RequestBody Cliente clienteAtualizado) {
		clienteAtualizado.setId(id);
		repository.save(clienteAtualizado);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
