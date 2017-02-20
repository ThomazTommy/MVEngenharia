package br.com.mvengenharia.business.entities.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.mvengenharia.business.entities.Gravidade;

@Repository
public interface GravidadeRepository extends CrudRepository<Gravidade, Long> {
	
	
	
}
