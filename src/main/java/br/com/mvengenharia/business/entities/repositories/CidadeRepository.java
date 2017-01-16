package br.com.mvengenharia.business.entities.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.mvengenharia.business.entities.Cidade;

@Transactional
public interface CidadeRepository extends CrudRepository<Cidade, Long> {
	
	@Query("select c from Cidade c JOIN c.estado e where e.idEstado = ?")
	public List<Cidade> findByIdEstado(Long idEstado);
	
}
