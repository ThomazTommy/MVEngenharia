package br.com.mvengenharia.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mvengenharia.business.entities.Cidade;
import br.com.mvengenharia.business.entities.repositories.CidadeRepository;

@Service
public class CidadeService {
    
    @Autowired
    private CidadeRepository cidadeRepository; 
    
    
    public CidadeService() {
        super();
    }  
        
    public Iterable<Cidade> findAll() {
        return this.cidadeRepository.findAll();
    }    
      
    public void addOrUpdate(final Cidade cidade) {
        this.cidadeRepository.save(cidade);
    }
    
    public void remove(final Long id){
    	this.cidadeRepository.delete(id);
    }
    
}
