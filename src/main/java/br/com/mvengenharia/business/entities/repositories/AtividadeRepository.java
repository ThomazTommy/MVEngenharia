package br.com.mvengenharia.business.entities.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.mvengenharia.business.entities.Atividade;

@Transactional
public interface AtividadeRepository extends CrudRepository<Atividade, Long> {
	
	
	
}
