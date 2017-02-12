package br.com.mvengenharia.business.entities.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.mvengenharia.business.entities.Agendamento;

@Transactional
public interface AgendamentoRepository extends CrudRepository<Agendamento, Long> { 
	
}
