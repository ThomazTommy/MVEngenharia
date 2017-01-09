package br.com.mvengenharia.business.entities.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.mvengenharia.business.entities.Funcionario;

@Transactional
public interface FuncionarioRepository extends CrudRepository<Funcionario, Long> {
	
	public Funcionario findByCpf(String cpf);
	
	public Funcionario findByNomeFuncionario(String nomeFuncionario);
	
}
