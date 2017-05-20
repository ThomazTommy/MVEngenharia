package br.com.mvengenharia.business.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mvengenharia.business.entities.Feriado;
import br.com.mvengenharia.business.entities.repositories.FeriadoRepository;

@Service
@Transactional
public class FeriadoService {
    
    @Autowired
    private FeriadoRepository feriadoRepository; 
    
    
    public FeriadoService() {
        super();
    }  
        
    public Iterable<Feriado> findAll() {
        return this.feriadoRepository.findAll();
    }   
    
    public Iterable<Feriado> findByAnoFeriado(int ano) {
        return this.feriadoRepository.findByAnoFeriado(ano);
    } 
      
    public void addOrUpdate(final Feriado feriado) {
        this.feriadoRepository.save(feriado);
    }
    
    public void remove(final Long id){
    	this.feriadoRepository.delete(id);
    }

	public Feriado findOne(Long id) {		
		return this.feriadoRepository.findOne(id);
	}
    
}
