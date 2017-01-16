package br.com.mvengenharia.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mvengenharia.business.entities.Ramo;
import br.com.mvengenharia.business.entities.repositories.RamoRepository;

@Service
public class RamoService {
    
    @Autowired
    private RamoRepository ramoRepository; 
    
    
    public RamoService() {
        super();
    }  
        
    public Iterable<Ramo> findAll() {
        return this.ramoRepository.findAll();
    }    
      
    public void addOrUpdate(final Ramo ramo) {
        this.ramoRepository.save(ramo);
    }
    
    public void remove(final Long id){
    	this.ramoRepository.delete(id);
    }


}
