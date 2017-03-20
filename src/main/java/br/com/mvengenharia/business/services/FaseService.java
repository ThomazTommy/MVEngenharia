package br.com.mvengenharia.business.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mvengenharia.business.entities.Fase;
import br.com.mvengenharia.business.entities.repositories.FaseRepository;

@Service
@Transactional
public class FaseService {
    
    @Autowired
    private FaseRepository faseRepository; 
    
    
    public FaseService() {
        super();
    }  
        
    public Iterable<Fase> findAll() {
        return this.faseRepository.findAll();
    }    
      
    public void addOrUpdate(final Fase fase) {
        this.faseRepository.save(fase);
    }
    
    public void remove(final Long id){
    	this.faseRepository.delete(id);
    }
    
}
