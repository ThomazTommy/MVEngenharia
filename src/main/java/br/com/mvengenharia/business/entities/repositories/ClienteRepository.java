package br.com.mvengenharia.business.entities.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.mvengenharia.business.entities.Atividade;
import br.com.mvengenharia.business.entities.Cliente;

@Transactional
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
		
}
