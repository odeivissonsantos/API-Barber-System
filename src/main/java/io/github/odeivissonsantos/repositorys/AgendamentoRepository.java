package io.github.odeivissonsantos.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.odeivissonsantos.models.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long>  {

}
