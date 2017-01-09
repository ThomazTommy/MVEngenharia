package br.com.mvengenharia.business.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the Segurado database table.
 * 
 */
@Entity
@NamedQuery(name="Segurado.findAll", query="SELECT s FROM Segurado s")
public class Segurado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idSegurado;

	@Column(name="cpf_cnpj")
	private BigDecimal cpfCnpj;

	private String nomeSegurado;

	//bi-directional many-to-one association to Inspecao
	@OneToMany(mappedBy="segurado")
	private List<Inspecao> inspecaos;

	public Segurado() {
	}

	public int getIdSegurado() {
		return this.idSegurado;
	}

	public void setIdSegurado(int idSegurado) {
		this.idSegurado = idSegurado;
	}

	public BigDecimal getCpfCnpj() {
		return this.cpfCnpj;
	}

	public void setCpfCnpj(BigDecimal cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getNomeSegurado() {
		return this.nomeSegurado;
	}

	public void setNomeSegurado(String nomeSegurado) {
		this.nomeSegurado = nomeSegurado;
	}

	public List<Inspecao> getInspecaos() {
		return this.inspecaos;
	}

	public void setInspecaos(List<Inspecao> inspecaos) {
		this.inspecaos = inspecaos;
	}

	public Inspecao addInspecao(Inspecao inspecao) {
		getInspecaos().add(inspecao);
		inspecao.setSegurado(this);

		return inspecao;
	}

	public Inspecao removeInspecao(Inspecao inspecao) {
		getInspecaos().remove(inspecao);
		inspecao.setSegurado(null);

		return inspecao;
	}

}