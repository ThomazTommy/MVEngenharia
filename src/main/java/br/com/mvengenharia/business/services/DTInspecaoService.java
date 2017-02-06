package br.com.mvengenharia.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import br.com.mvengenharia.business.entities.Inspecao;
import br.com.mvengenharia.business.entities.repositories.DTInspecaoRepository;

@Service
public class DTInspecaoService {
    
    @Autowired
    private DTInspecaoRepository dtInspecaoRepository; 
    
    
    public DTInspecaoService() {
        super();
    }  
        
    public DataTablesOutput<Inspecao> findAll(DataTablesInput input) {
        return this.dtInspecaoRepository.findAll(input);
    }        
   
    
}
