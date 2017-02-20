package br.com.mvengenharia.business.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mvengenharia.business.entities.TipoLogradouro;
import br.com.mvengenharia.business.entities.repositories.TipoLogradouroRepository;

@Service
@Transactional
public class TipoLogradouroService {
    
    @Autowired
    private TipoLogradouroRepository tipoLogradouroRepository; 
    
    
    public TipoLogradouroService() {
        super();
    }  
        
    public Iterable<TipoLogradouro> findAll() {
        return this.tipoLogradouroRepository.findAll();
    }    
      
    public void addOrUpdate(final TipoLogradouro tipoLogradouro) {
        this.tipoLogradouroRepository.save(tipoLogradouro);
    }
    
    public void remove(final Long id){
    	this.tipoLogradouroRepository.delete(id);
    }
    
}
