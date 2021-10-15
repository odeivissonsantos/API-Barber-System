package io.github.odeivissonsantos.repositorys;

import io.github.odeivissonsantos.models.ContaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: Deivisson Santos
 * @version: 2.0
 * @Email: deivissonsantos@hotmail.com
 * @Contato: (71) 99188-8419 (WhatsApp)
 */

@Repository
public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Long> {
}
