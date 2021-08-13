package io.github.odeivissonsantos.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

import io.github.odeivissonsantos.models.ServicoPrestadoModel;
import io.github.odeivissonsantos.repositorys.ServicoPrestadoRepository;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/servicos-prestados")
public class ServicoPrestadoController {

	public final ServicoPrestadoRepository repository;

	public ServicoPrestadoController(ServicoPrestadoRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	public ResponseEntity<List<ServicoPrestadoModel>> listarTodos() {
		return ResponseEntity.ok().body(repository.findAll());
	}

	@PostMapping
	public ResponseEntity<ServicoPrestadoModel> salvar(@Valid @RequestBody ServicoPrestadoModel servicoPrestado) {
		return ResponseEntity.created(null).body(repository.save(servicoPrestado));
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Optional<ServicoPrestadoModel>> buscarPorId(@PathVariable Integer id) {
		return ResponseEntity.ok().body(repository.findById(id));
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> atualizar(@PathVariable Integer id,
			@Valid @RequestBody ServicoPrestadoModel servicoPrestadoAtualizado) {
		servicoPrestadoAtualizado.setId(id);
		repository.save(servicoPrestadoAtualizado);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Integer id) {
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
