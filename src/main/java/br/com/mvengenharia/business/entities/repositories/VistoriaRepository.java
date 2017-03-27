package br.com.mvengenharia.business.entities.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.mvengenharia.business.entities.Vistoria;

@Repository
public interface VistoriaRepository extends CrudRepository<Vistoria, Long> {	
}
