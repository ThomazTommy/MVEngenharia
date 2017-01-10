package br.com.mvengenharia.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mvengenharia.business.entities.Escritorio;
import br.com.mvengenharia.business.entities.repositories.EscritorioRepository;

@Service
public class EscritorioService {
    
    @Autowired
    private EscritorioRepository escritorioRepository; 
    
    
    public EscritorioService() {
        super();
    }  
        
    public Iterable<Escritorio> findAll() {
        return this.escritorioRepository.findAll();
    }    
      
    public void addOrUpdate(final Escritorio escritorio) {
        this.escritorioRepository.save(escritorio);
    }
    
    public void remove(final Long id){
    	this.escritorioRepository.delete(id);
    }
    
}
