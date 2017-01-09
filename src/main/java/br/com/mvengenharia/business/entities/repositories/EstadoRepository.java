package br.com.mvengenharia.business.entities.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.mvengenharia.business.entities.Estado;

@Transactional
public interface EstadoRepository extends CrudRepository<Estado, Long> {
	
}
