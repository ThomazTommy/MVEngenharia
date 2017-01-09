package br.com.mvengenharia.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mvengenharia.business.entities.Cobertura;
import br.com.mvengenharia.business.entities.repositories.CoberturaRepository;

@Service
public class CoberturaService {
    
    @Autowired
    private CoberturaRepository coberturaRepository; 
    
    
    public CoberturaService() {
        super();
    }  
        
    public Iterable<Cobertura> findAll() {
        return this.coberturaRepository.findAll();
    }    
      
    public void addOrUpdate(final Cobertura cobertura) {
        this.coberturaRepository.save(cobertura);
    }
    
    public void remove(final Long id){
    	this.coberturaRepository.delete(id);
    }
    
}
