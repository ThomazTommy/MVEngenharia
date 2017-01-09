package br.com.mvengenharia.business.entities.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.mvengenharia.business.entities.NaoConformidade;

@Transactional
public interface NaoConformidadeRepository extends CrudRepository<NaoConformidade, Long> {
	
	
	
}
