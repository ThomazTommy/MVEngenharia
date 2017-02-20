package br.com.mvengenharia.business.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mvengenharia.business.entities.TipoInspecao;
import br.com.mvengenharia.business.entities.repositories.TipoInspecaoRepository;

@Service
@Transactional
public class TipoInspecaoService {
    
    @Autowired
    private TipoInspecaoRepository tipoInspecaoRepository; 
    
    
    public TipoInspecaoService() {
        super();
    }  
        
    public Iterable<TipoInspecao> findAll() {
        return this.tipoInspecaoRepository.findAll();
    }    
      
    public void addOrUpdate(final TipoInspecao tipoInspecao) {
        this.tipoInspecaoRepository.save(tipoInspecao);
    }
    
    public void remove(final Long id){
    	this.tipoInspecaoRepository.delete(id);
    }

	
}
