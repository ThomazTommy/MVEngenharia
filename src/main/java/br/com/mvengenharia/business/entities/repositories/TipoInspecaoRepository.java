package br.com.mvengenharia.business.entities.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.mvengenharia.business.entities.TipoInspecao;

@Transactional
public interface TipoInspecaoRepository extends CrudRepository<TipoInspecao, Long> {
	
	
	
}
