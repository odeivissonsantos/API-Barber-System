package io.github.odeivissonsantos.controllers;

import java.util.List;
import java.util.Optional;

import io.github.odeivissonsantos.services.ServicoPrestadoService;
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

import io.github.odeivissonsantos.models.ServicoPrestado;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/v2/servicos-prestados")
public class ServicoPrestadoController {

	public final ServicoPrestadoService servicoPrestadoService;

	public ServicoPrestadoController(ServicoPrestadoService servicoPrestadoService) {
		this.servicoPrestadoService = servicoPrestadoService;
	}

	@GetMapping
	public ResponseEntity<List<ServicoPrestado>> listarTodos() {
		return ResponseEntity.ok().body(repository.findAll());
	}

	@PostMapping
	public ResponseEntity<ServicoPrestado> salvar(@RequestBody ServicoPrestado servicoPrestado) {
		return ResponseEntity.created(null).body(repository.save(servicoPrestado));
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Optional<ServicoPrestado>> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok().body(repository.findById(id));
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> atualizar(@PathVariable Long id,
			 @RequestBody ServicoPrestado servicoPrestadoAtualizado) {
		servicoPrestadoAtualizado.setId(id);
		repository.save(servicoPrestadoAtualizado);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
