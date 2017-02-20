package br.com.mvengenharia.business.entities.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.mvengenharia.business.entities.Agendamento;

@Repository
public interface AgendamentoRepository extends CrudRepository<Agendamento, Long> { 
	
}
