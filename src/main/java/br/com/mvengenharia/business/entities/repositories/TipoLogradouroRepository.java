package br.com.mvengenharia.business.entities.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.mvengenharia.business.entities.TipoLogradouro;

@Transactional
public interface TipoLogradouroRepository extends CrudRepository<TipoLogradouro, Long> {
	
	
	
}
