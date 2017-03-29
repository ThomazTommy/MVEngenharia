package br.com.mvengenharia.business.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import br.com.mvengenharia.business.entities.Log;
import br.com.mvengenharia.business.entities.repositories.LogRepository;

@Service
@Transactional
public class LogService {
    
    @Autowired
    private LogRepository logRepository; 
    
    
    public LogService() {
        super();
    }  
        
    public Iterable<Log> findAll() {
        return this.logRepository.findAll();
    }    
      
    public DataTablesOutput<Log> findAll(DataTablesInput input) {
		return this.logRepository.findAll(input);
	}
    
    public void addOrUpdate(final Log log) {
        this.logRepository.save(log);
    }
    
    public void remove(final Long id){
    	this.logRepository.delete(id);
    }

	public Log findOne(Long id) {		
		return this.logRepository.findOne(id);
	}
    
}
