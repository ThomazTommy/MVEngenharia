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
	private Date dataInicioRevisao;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFimRevisao;
	
	private boolean ultimo; 
	
	private String observacao;

	//bi-directional many-to-one association to Inspecao
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="idInspecao")
	private Inspecao inspecao;

	//bi-directional many-to-one association to Funcionario
	@ManyToOne
	@JoinColumn(name="cpfRevisor")
	private Funcionario funcionarioRevisor;

	//bi-directional many-to-many association to NaoConformidade
	@ManyToMany
	private List<NaoConformidade> naoConformidades;

	public Revisao() {
	}

	
	
	
	public String getObservacao() {
		return observacao;
	}




	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}




	public Date getDataFimRevisao() {
		return dataFimRevisao;
	}


	public void setDataFimRevisao(Date dataFimRevisao) {
		this.dataFimRevisao = dataFimRevisao;
	}


	public boolean getUltimo() {
		return ultimo;
	}


	public void setUltimo(boolean ultimo) {
		this.ultimo = ultimo;
	}


	public long getIdRevisao() {
		return this.idRevisao;
	}

	public void setIdRevisao(long idRevisao) {
		this.idRevisao = idRevisao;
	}

	public Date getDataInicioRevisao() {
		return this.dataInicioRevisao;
	}

	public void setDataInicioRevisao(Date dataInicioRevisao) {
		this.dataInicioRevisao = dataInicioRevisao;
	}

	public Inspecao getInspecao() {
		return this.inspecao;
	}

	public void setInspecao(Inspecao inspecao) {
		this.inspecao = inspecao;
	}

	public Funcionario getFuncionarioRevisor() {
		return this.funcionarioRevisor;
	}

	public void setFuncionarioRevisor(Funcionario funcionarioRevisor) {
		this.funcionarioRevisor = funcionarioRevisor;
	}

	public List<NaoConformidade> getNaoConformidades() {
		return this.naoConformidades;
	}

	public void setNaoConformidades(List<NaoConformidade> naoConformidades) {
		this.naoConformidades = naoConformidades;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("dataFimRevisao: ").append(this.getDataFimRevisao() == null ? "" :this.getDataFimRevisao().toString()).append("|")
		.append("dataInicioRevisao: ").append(this.getDataInicioRevisao() == null ? "" :this.getDataInicioRevisao().toString()).append("|")
		.append("dataFuncionarioRevisor: ").append(this.getFuncionarioRevisor() == null ? "" :this.getFuncionarioRevisor().toString()).append("|")
		.append("observacao: ").append(this.getObservacao() == null ? "" :this.getObservacao().toString()).append("|")
		
		.append("naoConformidades: ");
		for(NaoConformidade nc : this.getNaoConformidades())
		{
			sb.append(nc.getDescNaoConformidade() == null ? "" : nc.getDescNaoConformidade()).append("|");
		}
		sb.append("ultimo: ").append(this.getUltimo());
		return sb.toString();
	}

}