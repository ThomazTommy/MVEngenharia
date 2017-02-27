package br.com.mvengenharia.business.entities.repositories;

import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.mvengenharia.business.entities.Inspecao;

@Repository
public interface DTInspecaoRepository extends DataTablesRepository<Inspecao, Long> {
	
	@Query(value = "select distinct inspecao.* from inspecao inner join designacao on inspecao.id_inspecao = designacao.id_inspecao where designacao.cpf_designado = ?", nativeQuery = true)
	public List<Inspecao> findByFuncionarioDesignado(String cpf);
	
	@Query(value = "select distinct inspecao.* from inspecao where inspecao.id_status = ?", nativeQuery = true)
	public List<Inspecao> findByStatus(long status);
	
	
}
