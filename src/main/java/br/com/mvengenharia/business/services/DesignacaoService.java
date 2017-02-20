package br.com.mvengenharia.business.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mvengenharia.business.entities.Designacao;
import br.com.mvengenharia.business.entities.repositories.DesignacaoRepository;

@Service
@Transactional
public class DesignacaoService {
    
    @Autowired
    private DesignacaoRepository designacaoRepository; 
    
    
    public DesignacaoService() {
        super();
    }  
        
    public Iterable<Designacao> findAll() {
        return this.designacaoRepository.findAll();
    }    
      
    public Designacao findOne(Long id){
    	return this.designacaoRepository.findOne(id);
    }
    
    public void addOrUpdate(final Designacao designacao) {
    	this.designacaoRepository.save(designacao);
    }
    
    public void remove(final Long id){
    	this.designacaoRepository.delete(id);
    }
    
}
