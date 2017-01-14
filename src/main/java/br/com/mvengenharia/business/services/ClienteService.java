package br.com.mvengenharia.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mvengenharia.business.entities.Cliente;
import br.com.mvengenharia.business.entities.repositories.ClienteRepository;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository; 
    
    
    public ClienteService() {
        super();
    }  
        
    public Iterable<Cliente> findAll() {
        return this.clienteRepository.findAll();
    }    
      
    public void addOrUpdate(final Cliente cliente) {
        this.clienteRepository.save(cliente);
    }
    
    public Cliente findOne(long id)
    {
    	return this.clienteRepository.findOne(id);
    }
    
    public void remove(final Long id){
    	this.clienteRepository.delete(id);
    }
    
}
