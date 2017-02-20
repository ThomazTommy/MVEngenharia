package br.com.mvengenharia.business.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mvengenharia.business.entities.Agendamento;
import br.com.mvengenharia.business.entities.repositories.AgendamentoRepository;

@Service
@Transactional
public class AgendamentoService {
    
    @Autowired
    private AgendamentoRepository agendamentoRepository; 
    
    
    public AgendamentoService() {
        super();
    }  
        
    public Iterable<Agendamento> findAll() {
        return this.agendamentoRepository.findAll();
    }    
      
    public Agendamento findOne(Long id){
    	return this.agendamentoRepository.findOne(id);
    }
    
    public void addOrUpdate(final Agendamento agendamento) {
    	this.agendamentoRepository.save(agendamento);
    }
    
    public void remove(final Long id){
    	this.agendamentoRepository.delete(id);
    }
    
}
