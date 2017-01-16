package br.com.mvengenharia.business.entities.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.mvengenharia.business.entities.Ramo;

@Transactional
public interface RamoRepository extends CrudRepository<Ramo, Long> {

		
}
