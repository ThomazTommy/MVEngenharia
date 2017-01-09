package br.com.mvengenharia.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mvengenharia.business.entities.Atividade;
import br.com.mvengenharia.business.entities.repositories.AtividadeRepository;

@Service
public class AtividadeService {
    
    @Autowired
    private AtividadeRepository atividadeRepository; 
    
    
    public AtividadeService() {
        super();
    }  
        
    public Iterable<Atividade> findAll() {
        return this.atividadeRepository.findAll();
    }    
      
    public void addOrUpdate(final Atividade atividade) {
        this.atividadeRepository.save(atividade);
    }
    
    public void remove(final Long id){
    	this.atividadeRepository.delete(id);
    }
    
}
