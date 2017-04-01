package br.com.mvengenharia.business.entities.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.mvengenharia.business.entities.Escritorio;

@Repository
public interface EscritorioRepository extends CrudRepository<Escritorio, Long> {
	
	@Query("SELECT e FROM Escritorio e WHERE e.situacao = true")
	public Iterable<Escritorio> findAllAtivo();
	
}
