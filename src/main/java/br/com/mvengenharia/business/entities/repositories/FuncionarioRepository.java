package br.com.mvengenharia.business.entities.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.mvengenharia.business.entities.Funcionario;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, String> {
	
	public Funcionario findByCpf(String cpf);
	
	public void deleteByCpf(String cpf);
	
	public Funcionario findByNomeFuncionario(String nomeFuncionario);

	@Query("select f from Funcionario f where f.situacao = true")
	public Iterable<Funcionario> findAllAtivos();
	
}
