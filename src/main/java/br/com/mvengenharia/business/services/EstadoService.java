package br.com.mvengenharia.business.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mvengenharia.business.entities.Estado;
import br.com.mvengenharia.business.entities.repositories.EstadoRepository;

@Service
@Transactional
public class EstadoService {
    
    @Autowired
    private EstadoRepository estadoRepository; 
    
    
    public EstadoService() {
        super();
    }  
        
    public Iterable<Estado> findAll() {
        return this.estadoRepository.findAll();
    }    
      
    public void addOrUpdate(final Estado estado) {
        this.estadoRepository.save(estado);
    }
    
    public void remove(final Long id){
    	this.estadoRepository.delete(id);
    }
    
}
