package br.com.mvengenharia.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mvengenharia.business.entities.NaoConformidade;
import br.com.mvengenharia.business.entities.repositories.NaoConformidadeRepository;

@Service
public class NaoConformidadeService {
    
    @Autowired
    private NaoConformidadeRepository naoConformidadeRepository; 
    
    
    public NaoConformidadeService() {
        super();
    }  
        
    public Iterable<NaoConformidade> findAll() {
        return this.naoConformidadeRepository.findAll();
    }    
      
    public void addOrUpdate(final NaoConformidade naoConformidade) {
        this.naoConformidadeRepository.save(naoConformidade);
    }
    
    public void remove(final Long id){
    	this.naoConformidadeRepository.delete(id);
    }
    
}
