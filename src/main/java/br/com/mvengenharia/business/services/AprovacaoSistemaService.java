package br.com.mvengenharia.business.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mvengenharia.business.entities.AprovacaoSistema;
import br.com.mvengenharia.business.entities.repositories.AprovacaoSistemaRepository;

@Service
@Transactional
public class AprovacaoSistemaService {
    
    @Autowired
    private AprovacaoSistemaRepository aprovacaoSistemaRepository; 
    
    
    public AprovacaoSistemaService() {
        super();
    }  
        
    public Iterable<AprovacaoSistema> findAll() {
        return this.aprovacaoSistemaRepository.findAll();
    }    
      
    public void addOrUpdate(final AprovacaoSistema aprovacaoSistema) {
        this.aprovacaoSistemaRepository.save(aprovacaoSistema);
    }
    
    public void remove(final Long id){
    	this.aprovacaoSistemaRepository.delete(id);
    }
    
}
