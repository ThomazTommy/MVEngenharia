package br.com.mvengenharia.business.entities.repositories;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

import br.com.mvengenharia.business.entities.Log;

@Repository
public interface LogRepository extends DataTablesRepository<Log, Long>{	
}
