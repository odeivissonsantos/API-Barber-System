package io.github.odeivissonsantos.controllers;

import java.net.URI;
import java.util.List;

import io.github.odeivissonsantos.services.AgendamentoService;
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

import io.github.odeivissonsantos.models.Agendamento;
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
@RequestMapping(value = "/api/v2/agendamentos")
public class AgendamentoController {

	private final AgendamentoService agendamentoService;

	public AgendamentoController(AgendamentoService agendamentoService) {
		this.agendamentoService = agendamentoService;
	}

	@Operation(description = "Lista todos os agendamentos cadastrados na base de dados")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Lista com todos agendamentos")
	})
	@GetMapping
	public ResponseEntity<List<Agendamento>> listarTodosAgendamentos(){
		return ResponseEntity.ok().body(agendamentoService.listarTodosAgendamentos());
	}

	@Operation(description = "Retorna um agendamento por ID")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Lista os detalhes de um agendamento por ID")
	})
	@GetMapping("/{id}")
	public ResponseEntity<Agendamento> buscarAgendamentoPorId(@PathVariable Long id) {
		Agendamento obj = agendamentoService.buscarAgendamentoPorId(id);
		return ResponseEntity.ok().body(obj);
	}

	@Operation(description = "Insere um novo agendamento na base de dados")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Cria um novo agendamento")
	})
	@PostMapping
	public ResponseEntity<Agendamento> criarAgendamento(@RequestBody Agendamento obj) {
		Agendamento newObj = agendamentoService.criarAgendamento(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(newObj);
	}

	@Operation(description = "Atualiza um agendamento")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Atualiza um agendamento cadastrado base de dados")
	})
	@PutMapping("/{id}")
	public ResponseEntity<Agendamento> atualizarAgendamento(@PathVariable Long id,
															@Valid @RequestBody Agendamento obj) {
		Agendamento newObj = agendamentoService.atualizarAgendamento(id, obj);
		return ResponseEntity.ok().body(newObj);
	}

	@Operation(description = "Exclui um agendamento")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "204", description = "Exclui um agendamento passando uum id")
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarAgendamento(@PathVariable Long id) {
		agendamentoService.deletarAgendamento(id);
		return ResponseEntity.noContent().build();
	}


}
