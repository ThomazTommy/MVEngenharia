package br.com.mvengenharia.business.entities.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.mvengenharia.business.entities.Honorario;

@Repository
public interface HonorarioRepository extends CrudRepository<Honorario, Long> {
	
	
	
}
