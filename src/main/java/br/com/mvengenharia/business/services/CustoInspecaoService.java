package br.com.mvengenharia.business.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mvengenharia.business.entities.CustoInspecao;
import br.com.mvengenharia.business.entities.repositories.CustoInspecaoRepository;

@Service
@Transactional
public class CustoInspecaoService {
    
    @Autowired
    private CustoInspecaoRepository custoInspecaoRepository; 
    
    
    public CustoInspecaoService() {
        super();
    }  
        
    public Iterable<CustoInspecao> findAll() {
        return this.custoInspecaoRepository.findAll();
    }    
      
    public void addOrUpdate(final CustoInspecao custoInspecao) {
        this.custoInspecaoRepository.save(custoInspecao);
    }
    
    public void remove(final Long id){
    	this.custoInspecaoRepository.delete(id);
    }
    
}
