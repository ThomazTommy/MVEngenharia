package br.com.mvengenharia.business.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mvengenharia.business.entities.Status;
import br.com.mvengenharia.business.entities.repositories.StatusRepository;

@Service
@Transactional
public class StatusService {
    
    @Autowired
    private StatusRepository statusRepository; 
    
    
    public StatusService() {
        super();
    }  
        
    public Iterable<Status> findAll() {
        return this.statusRepository.findAll();
    }    
      
    public void addOrUpdate(final Status status) {
        this.statusRepository.save(status);
    }
    
    public void remove(final Long id){
    	this.statusRepository.delete(id);
    }
    
}
