package io.github.odeivissonsantos.controllers;

import java.util.List;
import java.util.Optional;

import io.github.odeivissonsantos.models.Barbeiro;
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

import io.github.odeivissonsantos.repositorys.BarbeiroRepository;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/barbeiros")
public class BarbeiroController {
	
	public final BarbeiroRepository repository;
	
	public BarbeiroController(BarbeiroRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	public ResponseEntity<List<Barbeiro>> listarTodos(){
		return ResponseEntity.ok().body(repository.findAll());
	}
	
	@PostMapping
	public ResponseEntity<Barbeiro> salvar(@RequestBody Barbeiro barbeiro) {
		return ResponseEntity.created(null).body(repository.save(barbeiro));
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Optional<Barbeiro>> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok().body(repository.findById(id));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody Barbeiro barbeiroAtualizado) {
		barbeiroAtualizado.setId(id);
		repository.save(barbeiroAtualizado);
		return ResponseEntity.noContent().build();	
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id){
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}


}
