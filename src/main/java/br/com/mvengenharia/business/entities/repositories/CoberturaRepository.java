package br.com.mvengenharia.business.entities.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.mvengenharia.business.entities.Cobertura;

@Transactional
public interface CoberturaRepository extends CrudRepository<Cobertura, Long> {
	
	
	
}
