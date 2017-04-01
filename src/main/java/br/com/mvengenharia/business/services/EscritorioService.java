package br.com.mvengenharia.business.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mvengenharia.business.entities.Escritorio;
import br.com.mvengenharia.business.entities.repositories.EscritorioRepository;

@Service
@Transactional
public class EscritorioService {
    
    @Autowired
    private EscritorioRepository escritorioRepository; 
    
    
    public EscritorioService() {
        super();
    }  
        
    public Iterable<Escritorio> findAll() {
        return this.escritorioRepository.findAllAtivo();
    }    
      
    public void addOrUpdate(final Escritorio escritorio) {
        this.escritorioRepository.save(escritorio);
    }
    
    public void remove(final Long id){
    	this.escritorioRepository.delete(id);
    }

	public Escritorio findOne(Long id) {
		
		return this.escritorioRepository.findOne(id);
	}
    
}
