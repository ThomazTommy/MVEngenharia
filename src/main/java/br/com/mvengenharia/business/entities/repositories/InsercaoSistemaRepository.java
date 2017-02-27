package br.com.mvengenharia.business.entities.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.mvengenharia.business.entities.InsercaoSistema;
import br.com.mvengenharia.business.entities.Status;

@Repository
public interface InsercaoSistemaRepository extends CrudRepository<InsercaoSistema, Long> {	
	
}
