package br.com.mvengenharia.business.entities.repositories;

import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.mvengenharia.business.entities.Inspecao;

@Repository
public interface DTInspecaoRepository extends DataTablesRepository<Inspecao, Long> {
	
	@Query(value = "SELECT DISTINCT INSPECAO.* FROM INSPECAO INNER JOIN DESIGNACAO ON INSPECAO.ID_INSPECAO = DESIGNACAO.ID_INSPECAO WHERE DESIGNACAO.CPF_DESIGNADO = ?", nativeQuery = true)
	public List<Inspecao> findByFuncionarioDesignado(String cpf);
	
}
