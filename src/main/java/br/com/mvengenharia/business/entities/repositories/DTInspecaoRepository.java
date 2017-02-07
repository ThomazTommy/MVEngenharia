package br.com.mvengenharia.business.entities.repositories;



import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.mvengenharia.business.entities.Inspecao;

@Transactional
public interface DTInspecaoRepository extends DataTablesRepository<Inspecao, Long> {
		
}
