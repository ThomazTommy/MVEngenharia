package br.com.mvengenharia.business.services;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.mvengenharia.business.entities.Inspecao;
import br.com.mvengenharia.business.entities.Log;
import br.com.mvengenharia.business.entities.repositories.InspecaoRepository;

@Service
@Transactional
public class InspecaoService {
    
    @Autowired
    private InspecaoRepository inspecaoRepository; 
    
    @Autowired
    private LogService logService; 
    
    public InspecaoService() {
        super();
    }  
        
    public Iterable<Inspecao> findAll() {
        return this.inspecaoRepository.findAll();
    }    
      
    public void addOrUpdate(Inspecao inspecao) {    	
        inspecao = this.inspecaoRepository.save(inspecao);
        this.logService.addOrUpdate(new Log(inspecao.toString(), 
    			SecurityContextHolder.getContext().getAuthentication().getName(), 
    			inspecao.getIdInspecao(), 
    			new Date()));
    }
    
    public void remove(final Long id){
    	this.inspecaoRepository.delete(id);
    	this.logService.addOrUpdate(new Log("Removida inspecao de id nº " + id, 
    			SecurityContextHolder.getContext().getAuthentication().getName(), 
    			id, 
    			new Date()));
    }
    
    public Inspecao findOne(Long id){
    	return this.inspecaoRepository.findOne(id);
    }
    
}
