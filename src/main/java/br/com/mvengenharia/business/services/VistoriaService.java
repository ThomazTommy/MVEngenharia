package br.com.mvengenharia.business.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mvengenharia.business.entities.Vistoria;
import br.com.mvengenharia.business.entities.repositories.VistoriaRepository;

@Service
@Transactional
public class VistoriaService {
    
    @Autowired
    private VistoriaRepository vistoriaRepository; 
    
    
    public VistoriaService() {
        super();
    }  
        
    public Iterable<Vistoria> findAll() {
        return this.vistoriaRepository.findAll();
    }    
      
    public void addOrUpdate(final Vistoria vistoria) {
        this.vistoriaRepository.save(vistoria);
    }
    
    public void remove(final Long id){
    	this.vistoriaRepository.delete(id);
    }

    
}
