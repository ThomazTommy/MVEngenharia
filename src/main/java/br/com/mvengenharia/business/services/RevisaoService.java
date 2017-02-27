package br.com.mvengenharia.business.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mvengenharia.business.entities.Revisao;
import br.com.mvengenharia.business.entities.repositories.RevisaoRepository;

@Service
@Transactional
public class RevisaoService {
    
    @Autowired
    private RevisaoRepository revisaoRepository; 
    
    
    public RevisaoService() {
        super();
    }  
        
    public Iterable<Revisao> findAll() {
        return this.revisaoRepository.findAll();
    }    
      
    public void addOrUpdate(final Revisao revisao) {
        this.revisaoRepository.save(revisao);
    }
    
    public void remove(final Long id){
    	this.revisaoRepository.delete(id);
    }
    
}
