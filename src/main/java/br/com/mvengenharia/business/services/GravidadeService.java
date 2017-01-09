package br.com.mvengenharia.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mvengenharia.business.entities.Gravidade;
import br.com.mvengenharia.business.entities.repositories.GravidadeRepository;

@Service
public class GravidadeService {
    
    @Autowired
    private GravidadeRepository gravidadeRepository; 
    
    
    public GravidadeService() {
        super();
    }  
        
    public Iterable<Gravidade> findAll() {
        return this.gravidadeRepository.findAll();
    }    
      
    public void addOrUpdate(final Gravidade gravidade) {
        this.gravidadeRepository.save(gravidade);
    }
    
    public void remove(final Long id){
    	this.gravidadeRepository.delete(id);
    }
    
}
