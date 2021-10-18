package io.github.odeivissonsantos.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import io.github.odeivissonsantos.dtos.BarbeiroDTO;
import io.github.odeivissonsantos.dtos.ServicoPrestadoDTO;
import io.github.odeivissonsantos.models.Barbeiro;
import io.github.odeivissonsantos.services.ServicoPrestadoService;
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

import io.github.odeivissonsantos.models.ServicoPrestado;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/v2/servicos-prestados")
public class ServicoPrestadoController {

	public final ServicoPrestadoService servicoPrestadoService;

	public ServicoPrestadoController(ServicoPrestadoService servicoPrestadoService) {
		this.servicoPrestadoService = servicoPrestadoService;
	}

	@Operation(description = "Lista todos os Servicos cadastrados na base de dados")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Lista com todos servicos")
	})
	@GetMapping
	public ResponseEntity<List<ServicoPrestado>> listarTodosServicos() {
		List<ServicoPrestado> list = servicoPrestadoService.listarTodosServicos();
		List<ServicoPrestadoDTO> listDTO = list.stream().map(obj -> new ServicoPrestadoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(servicoPrestadoService.listarTodosServicos());
	}

	@Operation(description = "Retorna um servico por ID")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Lista os detalhes de um servico por ID")
	})
	@GetMapping("/{id}")
	public ResponseEntity<ServicoPrestado> buscarServicoPorId(@PathVariable Long id) {
		ServicoPrestado obj = servicoPrestadoService.buscarServicoPorId(id);
		return ResponseEntity.ok().body(obj);
	}

	@Operation(description = "Insere um novo servico na base de dados")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Cria um novo servico")
	})
	@PostMapping
	public ResponseEntity<ServicoPrestado> criarServico(@Valid @RequestBody ServicoPrestado obj) {
		ServicoPrestado newObj= servicoPrestadoService.criarServico(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(newObj);
	}

	@Operation(description = "Atualiza um servico")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Atualiza um servico cadastrado base de dados")
	})
	@PutMapping("/{id}")
	public ResponseEntity<ServicoPrestado> atualizarServico(@PathVariable Long id,
													  @Valid @RequestBody ServicoPrestado obj) {
		ServicoPrestado newObj = servicoPrestadoService.atualizarServico(id, obj);
		return ResponseEntity.ok().body(newObj);
	}

	@Operation(description = "Exclui um servico")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "204", description = "Exclui um servico passando uum id")
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarServico(@PathVariable Long id) {
		servicoPrestadoService.deletarServico(id);
		return ResponseEntity.noContent().build();
	}

}
