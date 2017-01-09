package br.com.mvengenharia.business.entities.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.mvengenharia.business.entities.Permissoes;

@Transactional
public interface PermissoesRepository extends CrudRepository<Permissoes, Long> {
}
