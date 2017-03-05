package br.com.mvengenharia.business.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import br.com.mvengenharia.business.entities.Inspecao;
import br.com.mvengenharia.business.entities.repositories.DTInspecaoRepository;

@Service
@Transactional
public class DTInspecaoService {
    
    @Autowired
    private DTInspecaoRepository dtInspecaoRepository; 
    
    
    public DTInspecaoService() {
        super();
    }  
        
    public DataTablesOutput<Inspecao> findAll(DataTablesInput input) {
        return this.dtInspecaoRepository.findAll(input);
    } 
  
    public List<Inspecao> findByFuncionarioDesignado(String cpf){
    	return this.dtInspecaoRepository.findByFuncionarioDesignado(cpf);
    }
    
    public List<Inspecao> findByStatus(Long status){
    	return this.dtInspecaoRepository.findByStatus(status);
    }
  
   
    
}
