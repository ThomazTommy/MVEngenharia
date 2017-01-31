package br.com.mvengenharia.business.entities.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.mvengenharia.business.entities.Inspecao;

@Transactional
public interface InspecaoRepository extends CrudRepository<Inspecao, Long> {
		
}
