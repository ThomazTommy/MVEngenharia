package br.com.mvengenharia.business.entities.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.mvengenharia.business.entities.TipoLogradouro;

@Repository
public interface TipoLogradouroRepository extends CrudRepository<TipoLogradouro, Long> {
	
	
	
}
