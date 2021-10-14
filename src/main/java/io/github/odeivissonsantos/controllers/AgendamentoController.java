package io.github.odeivissonsantos.controllers;

import java.util.List;
import java.util.Optional;

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
import io.github.odeivissonsantos.models.BarbeiroModel;
import io.github.odeivissonsantos.models.ClienteModel;
import io.github.odeivissonsantos.models.ServicoPrestado;
import io.github.odeivissonsantos.repositorys.AgendamentoRepository;
import io.github.odeivissonsantos.repositorys.BarbeiroRepository;
import io.github.odeivissonsantos.repositorys.ClienteRepository;
import io.github.odeivissonsantos.repositorys.ServicoPrestadoRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/agendamentos")
public class AgendamentoController {

	
	public final AgendamentoRepository repository;
	public final BarbeiroRepository barbeiroRepository;
	public final ClienteRepository clienteRepository;
	public final ServicoPrestadoRepository servicoPrestadoRepository;
	

	@GetMapping
	public ResponseEntity<List<Agendamento>> listarTodos(){
		return ResponseEntity.ok().body(repository.findAll());
	}
	
	@PostMapping
	public ResponseEntity<Agendamento> salvar(@RequestBody Agendamento agendamento) {
		BarbeiroModel barbeiro = barbeiroRepository.findById(agendamento.getBarbeiroId()).orElse(null);
		ClienteModel cliente = clienteRepository.findById(agendamento.getClienteId()).orElse(null);
		ServicoPrestado servicoPrestado = servicoPrestadoRepository.findById(agendamento.getServicoPrestadoId()).orElse(null);

		agendamento.setBarbeiro(barbeiro);
		agendamento.setCliente(cliente);
		agendamento.setServicoPrestado(servicoPrestado);
		return ResponseEntity.created(null).body(repository.save(agendamento));
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Optional<Agendamento>> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok().body(repository.findById(id));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody Agendamento agendamentoAtualizado) {
		agendamentoAtualizado.setId(id);
		repository.save(agendamentoAtualizado);
		return ResponseEntity.noContent().build();	
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id){
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}


}
