package br.com.mvengenharia.business.entities.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.mvengenharia.business.entities.Escritorio;

@Repository
public interface EscritorioRepository extends CrudRepository<Escritorio, Long> {
	
	
	
}
