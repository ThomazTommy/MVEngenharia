package br.com.mvengenharia.business.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mvengenharia.business.entities.Relatorio;
import br.com.mvengenharia.business.entities.repositories.RelatorioRepository;

@Service
@Transactional
public class RelatorioService {
    
    @Autowired
    private RelatorioRepository relatorioRepository; 
    
    
    public RelatorioService() {
        super();
    }  
        
    public Iterable<Relatorio> findAll() {
        return this.relatorioRepository.findAll();
    }    
      
    public void addOrUpdate(final Relatorio relatorio) {
        this.relatorioRepository.save(relatorio);
    }
    
    public void remove(final Long id){
    	this.relatorioRepository.delete(id);
    }
    
}
