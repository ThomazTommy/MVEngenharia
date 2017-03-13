package br.com.mvengenharia.business.services;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.mvengenharia.business.entities.Designacao;
import br.com.mvengenharia.business.entities.Inspecao;
import br.com.mvengenharia.business.entities.Status;
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
    
    public DataTablesOutput<Inspecao> findAllByStatus(DataTablesInput input, Long idStatus) {
    	Specification<Inspecao> specification = new Specification<Inspecao>() {
            public Predicate toPredicate(Root<Inspecao> root, CriteriaQuery<?> query, CriteriaBuilder builder) {             	
                return builder.equal(root.<Status>get("status").<Long>get("idStatus"), idStatus); 
            }
        };
        return this.dtInspecaoRepository.findAll(input, specification);
    } 
    

    public DataTablesOutput<Inspecao> findAllByCpf(DataTablesInput input, String cpf) {
    	Specification<Inspecao> specification = new Specification<Inspecao>() {
            public Predicate toPredicate(Root<Inspecao> root, CriteriaQuery<?> query, CriteriaBuilder builder) {   
            	Join<Inspecao, Designacao> designacao = root.join("designacoes");
            	return builder.equal(designacao.on(builder.equal(designacao.get("ultima"), true)).get("funcionarioDesignado").get("cpf"),cpf);                 
            }
        };
        return this.dtInspecaoRepository.findAll(input, specification);
    } 
  
  
    public List<Inspecao> findByFuncionarioDesignado(String cpf){
    	return this.dtInspecaoRepository.findByFuncionarioDesignado(cpf);
    }
    
    public List<Inspecao> findByStatus(Long status){
    	return this.dtInspecaoRepository.findByStatus(status);
    }
  
   
    
}
