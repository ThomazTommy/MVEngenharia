package br.com.mvengenharia.business.entities.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.mvengenharia.business.entities.Feriado;

@Repository
public interface FeriadoRepository extends CrudRepository<Feriado, Long> {
	
	public Iterable<Feriado> findByAnoFeriado(int ano);	
	
}
