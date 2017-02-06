package br.com.mvengenharia.business.entities.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import br.com.mvengenharia.business.entities.Inspecao;

@Transactional
public interface DTInspecaoRepository extends DataTablesRepository<Inspecao, Long> {
		
}
