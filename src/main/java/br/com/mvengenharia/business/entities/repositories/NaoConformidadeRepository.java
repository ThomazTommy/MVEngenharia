package br.com.mvengenharia.business.entities.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.mvengenharia.business.entities.NaoConformidade;

@Repository
public interface NaoConformidadeRepository extends CrudRepository<NaoConformidade, Long> {
	
	
	
}
