package br.com.mvengenharia.business.entities.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.mvengenharia.business.entities.TipoLogradouro;

@Repository
public interface TipoLogradouroRepository extends CrudRepository<TipoLogradouro, Long> {
	
	@Query("select c from TipoLogradouro c where c.situacao = true")
	 public Iterable<TipoLogradouro> findAllAtivo();
	
}
