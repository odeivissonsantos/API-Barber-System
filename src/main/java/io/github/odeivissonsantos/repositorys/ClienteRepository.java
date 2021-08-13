package io.github.odeivissonsantos.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.odeivissonsantos.models.ClienteModel;

public interface ClienteRepository extends JpaRepository<ClienteModel, Integer> {

}
