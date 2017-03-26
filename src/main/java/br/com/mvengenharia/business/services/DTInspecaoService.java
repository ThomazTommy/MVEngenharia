package br.com.mvengenharia.business.services;

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
				Predicate a = builder.equal(designacao.on(builder.equal(designacao.get("ultima"), true))
						.get("funcionarioDesignado").get("cpf"), cpf);
				Predicate b = builder.equal(root.<Status>get("fase").<Long>get("idFase"), 4);
				Predicate c = builder.equal(root.<Status>get("fase").<Long>get("idFase"), 6);
				Predicate d = builder.or(b,c);
				return builder.and(a,d);
			}
		};
		return this.dtInspecaoRepository.findAll(input, specification);
	}

	public DataTablesOutput<Inspecao> findAllByFase(DataTablesInput input, Long idFase1, Long idFase2) {
		Specification<Inspecao> specification = new Specification<Inspecao>() {
			public Predicate toPredicate(Root<Inspecao> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				Predicate a = builder.equal(root.<Status>get("fase").<Long>get("idFase"), idFase1);
				Predicate b = builder.equal(root.<Status>get("fase").<Long>get("idFase"), idFase2);
				return builder.or(a, b);
			}
		};
		return this.dtInspecaoRepository.findAll(input, specification);
	}
	
	public DataTablesOutput<Inspecao> findAllByFase(DataTablesInput input, Long idFase1, Long idFase2,
			Long idFase3) {
		Specification<Inspecao> specification = new Specification<Inspecao>() {
			public Predicate toPredicate(Root<Inspecao> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				Predicate a = builder.equal(root.<Status>get("fase").<Long>get("idFase"), idFase1);
				Predicate b = builder.equal(root.<Status>get("fase").<Long>get("idFase"), idFase2);
				Predicate c = builder.equal(root.<Status>get("fase").<Long>get("idFase"), idFase3);
				return builder.or(a, b, c);
			}
		};
		return this.dtInspecaoRepository.findAll(input, specification);
	}

	public DataTablesOutput<Inspecao> findAllByFase(DataTablesInput input, Long idFase1, Long idFase2,
			Long idFase3,Long idFase4) {
		Specification<Inspecao> specification = new Specification<Inspecao>() {
			public Predicate toPredicate(Root<Inspecao> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				Predicate a = builder.equal(root.<Status>get("fase").<Long>get("idFase"), idFase1);
				Predicate b = builder.equal(root.<Status>get("fase").<Long>get("idFase"), idFase2);
				Predicate c = builder.equal(root.<Status>get("fase").<Long>get("idFase"), idFase3);
				Predicate d = builder.equal(root.<Status>get("fase").<Long>get("idFase"), idFase4);
				return builder.or(a, b, c, d);
			}
		};
		return this.dtInspecaoRepository.findAll(input, specification);
	}

	
	public DataTablesOutput<Inspecao> findAllByFase(DataTablesInput input, Long idFase) {
		Specification<Inspecao> specification = new Specification<Inspecao>() {
			public Predicate toPredicate(Root<Inspecao> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.<Status>get("fase").<Long>get("idFase"), idFase);
			}
		};
		return this.dtInspecaoRepository.findAll(input, specification);
	}

	
	

}
