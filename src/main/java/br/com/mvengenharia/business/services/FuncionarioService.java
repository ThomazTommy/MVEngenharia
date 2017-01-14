package br.com.mvengenharia.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mvengenharia.business.entities.Funcionario;
import br.com.mvengenharia.business.entities.repositories.FuncionarioRepository;

@Service
public class FuncionarioService {
    
    @Autowired
    private FuncionarioRepository funcionarioRepository; 
    
    
    public FuncionarioService() {
        super();
    }  
        
    public Iterable<Funcionario> findAll() {
        return this.funcionarioRepository.findAll();
    }
    
    public Funcionario findByCpf(String cpf){
    	return this.funcionarioRepository.findByCpf(cpf);
    }
    
    public Funcionario findOne(String cpf){
    	return this.funcionarioRepository.findOne(cpf);
    }
    
    public Funcionario findByNomeFuncionario(String name){
    	return this.funcionarioRepository.findByNomeFuncionario(name);
    }
    
    public void addOrUpdate(final Funcionario funcionario) {
        this.funcionarioRepository.save(funcionario);
    }
    
    public void remove(final String cpf){
    	this.funcionarioRepository.deleteByCpf(cpf);
    }
    
}
