package br.com.mvengenharia.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mvengenharia.business.entities.Inspecao;
import br.com.mvengenharia.business.entities.repositories.InspecaoRepository;

@Service
public class InspecaoService {
    
    @Autowired
    private InspecaoRepository inspecaoRepository; 
    
    
    public InspecaoService() {
        super();
    }  
        
    public Iterable<Inspecao> findAll() {
        return this.inspecaoRepository.findAll();
    }    
      
    public void addOrUpdate(final Inspecao inspecao) {
        this.inspecaoRepository.save(inspecao);
    }
    
    public void remove(final Long id){
    	this.inspecaoRepository.delete(id);
    }
    
    public Inspecao findOne(Long id){
    	return this.inspecaoRepository.findOne(id);
    }
    
}
