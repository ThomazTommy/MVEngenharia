package br.com.mvengenharia.business.entities.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.mvengenharia.business.entities.Cidade;

@Transactional
public interface CidadeRepository extends CrudRepository<Cidade, Long> {
	
	
	
}
