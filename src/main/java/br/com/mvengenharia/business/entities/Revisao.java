package br.com.mvengenharia.business.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Revisao database table.
 * 
 */
@Entity
@NamedQuery(name="Revisao.findAll", query="SELECT r FROM Revisao r")
public class Revisao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idRevisao;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataRevisao;

	//bi-directional many-to-one association to Inspecao
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="idInspecao")
	private Inspecao inspecao;

	//bi-directional many-to-one association to Funcionario
	@ManyToOne
	@JoinColumn(name="cpfRevisor")
	private Funcionario funcionario;

	//bi-directional many-to-many association to NaoConformidade
	@ManyToMany
	private List<NaoConformidade> naoConformidades;

	public Revisao() {
	}

	public long getIdRevisao() {
		return this.idRevisao;
	}

	public void setIdRevisao(long idRevisao) {
		this.idRevisao = idRevisao;
	}

	public Date getDataRevisao() {
		return this.dataRevisao;
	}

	public void setDataRevisao(Date dataRevisao) {
		this.dataRevisao = dataRevisao;
	}

	public Inspecao getInspecao() {
		return this.inspecao;
	}

	public void setInspecao(Inspecao inspecao) {
		this.inspecao = inspecao;
	}

	public Funcionario getFuncionario() {
		return this.funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<NaoConformidade> getNaoConformidades() {
		return this.naoConformidades;
	}

	public void setNaoConformidades(List<NaoConformidade> naoConformidades) {
		this.naoConformidades = naoConformidades;
	}

}