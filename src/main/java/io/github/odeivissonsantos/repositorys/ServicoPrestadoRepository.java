package io.github.odeivissonsantos.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.odeivissonsantos.models.ServicoPrestado;
import org.springframework.stereotype.Repository;

/**
 * @author: Deivisson Santos
 * @version: 2.0
 * @Email: deivissonsantos@hotmail.com
 * @Contato: (71) 99188-8419 (WhatsApp)
 */

@Repository
public interface ServicoPrestadoRepository extends JpaRepository<ServicoPrestado, Long> {

}
