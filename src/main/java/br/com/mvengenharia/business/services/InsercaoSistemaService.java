package br.com.mvengenharia.business.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mvengenharia.business.entities.InsercaoSistema;
import br.com.mvengenharia.business.entities.repositories.InsercaoSistemaRepository;

@Service
@Transactional
public class InsercaoSistemaService {
    
    @Autowired
    private InsercaoSistemaRepository insercaoSistemaRepository; 
    
    
    public InsercaoSistemaService() {
        super();
    }  
        
    public Iterable<InsercaoSistema> findAll() {
        return this.insercaoSistemaRepository.findAll();
    }    
      
    public void addOrUpdate(final InsercaoSistema insercaoSistema) {
        this.insercaoSistemaRepository.save(insercaoSistema);
    }
    
    public void remove(final Long id){
    	this.insercaoSistemaRepository.delete(id);
    }
    
}
