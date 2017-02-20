package br.com.mvengenharia.business.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mvengenharia.business.entities.Permissoes;
import br.com.mvengenharia.business.entities.repositories.PermissoesRepository;

@Service
@Transactional
public class PermissoesService {
    
    @Autowired
    private PermissoesRepository permissoesRepository; 
    
    
    public PermissoesService() {
        super();
    }  
        
    public Iterable<Permissoes> findAll() {
        return this.permissoesRepository.findAll();
    }    
      
    public void addOrUpdate(final Permissoes permissoes) {
        this.permissoesRepository.save(permissoes);
    }
    
    public void remove(final Long id){
    	this.permissoesRepository.delete(id);
    }
    
}
