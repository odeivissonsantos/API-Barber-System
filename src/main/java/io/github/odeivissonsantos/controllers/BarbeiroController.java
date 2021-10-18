package io.github.odeivissonsantos.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import io.github.odeivissonsantos.dtos.BarbeiroDTO;
import io.github.odeivissonsantos.dtos.ClienteDTO;
import io.github.odeivissonsantos.models.Barbeiro;
import io.github.odeivissonsantos.models.Cliente;
import io.github.odeivissonsantos.services.BarbeiroService;
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

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/v2/barbeiros")
public class BarbeiroController {
	
	public final BarbeiroService barbeiroService;

	public BarbeiroController(BarbeiroService barbeiroService) {
		this.barbeiroService = barbeiroService;
	}


	@Operation(description = "Lista todos os barbeiros cadastrados na base de dados")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Lista com todos barbeiros")
	})
	@GetMapping
	public ResponseEntity<List<Barbeiro>> listarTodosBarbeiros() {
		List<Barbeiro> list = barbeiroService.listarTodosBarbeiros();
		List<BarbeiroDTO> listDTO = list.stream().map(obj -> new BarbeiroDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(barbeiroService.listarTodosBarbeiros());
	}

	@Operation(description = "Retorna um barbeiro por ID")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Lista os detalhes de um barbeiro por ID")
	})
	@GetMapping("/{id}")
	public ResponseEntity<Barbeiro> buscarBarbeiroPorId(@PathVariable Long id) {
		Barbeiro obj = barbeiroService.buscarBarbeiroPorId(id);
		return ResponseEntity.ok().body(obj);
	}

	@Operation(description = "Insere um novo barbeiro na base de dados")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Cria um novo barbeiro")
	})
	@PostMapping
	public ResponseEntity<Barbeiro> criarBarbeiro(@Valid @RequestBody Barbeiro obj) {
		Barbeiro newObj= barbeiroService.criarBarbeiro(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(newObj);
	}

	@Operation(description = "Atualiza um barbeiro")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Atualiza um barbeiro cadastrado base de dados")
	})
	@PutMapping("/{id}")
	public ResponseEntity<Barbeiro> atualizarBarbeiro(@PathVariable Long id,
													@Valid @RequestBody Barbeiro obj) {
		Barbeiro newObj = barbeiroService.atualizarBarbeiro(id, obj);
		return ResponseEntity.ok().body(newObj);
	}

	@Operation(description = "Exclui um barbeiro")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "204", description = "Exclui um barbeiro passando uum id")
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarBarbeiro(@PathVariable Long id) {
		barbeiroService.deletarBarbeiro(id);
		return ResponseEntity.noContent().build();
	}

}
