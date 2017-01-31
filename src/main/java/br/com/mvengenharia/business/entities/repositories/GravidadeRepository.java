package br.com.mvengenharia.business.entities.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.mvengenharia.business.entities.Gravidade;

@Transactional
public interface GravidadeRepository extends CrudRepository<Gravidade, Long> {
	
	
	
}