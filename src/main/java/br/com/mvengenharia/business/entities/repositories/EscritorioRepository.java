package br.com.mvengenharia.business.entities.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.mvengenharia.business.entities.Escritorio;

@Transactional
public interface EscritorioRepository extends CrudRepository<Escritorio, Long> {
	
	
	
}
