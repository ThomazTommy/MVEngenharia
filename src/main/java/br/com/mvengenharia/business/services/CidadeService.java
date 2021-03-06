package br.com.mvengenharia.business.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mvengenharia.business.entities.Cidade;
import br.com.mvengenharia.business.entities.repositories.CidadeRepository;

@Service
@Transactional
public class CidadeService {
    
    @Autowired
    private CidadeRepository cidadeRepository; 
    
    
    public CidadeService() {
        super();
    }  
        
    public Iterable<Cidade> findAll() {
        return this.cidadeRepository.findAll();
    }
       
    public Iterable<Cidade> findByIdEstado(Long idEstado) {
        return this.cidadeRepository.findByIdEstado(idEstado);
    }
      
    public void addOrUpdate(final Cidade cidade) {
        this.cidadeRepository.save(cidade);
    }
    
    public void remove(final Long id){
    	this.cidadeRepository.delete(id);
    }
    
}
