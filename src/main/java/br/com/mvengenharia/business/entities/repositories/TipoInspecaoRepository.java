package br.com.mvengenharia.business.entities.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.mvengenharia.business.entities.TipoInspecao;

@Repository
public interface TipoInspecaoRepository extends CrudRepository<TipoInspecao, Long> {
	
}
