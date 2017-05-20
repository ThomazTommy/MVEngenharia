package br.com.mvengenharia.business.entities;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Relatorio database table.
 * 
 */
@Entity
@NamedQuery(name="Relatorio.findAll", query="SELECT r FROM Relatorio r")
public class Relatorio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idRelatorio;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInicioRelatorio;
	

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFimRelatorio;
	
	private boolean ultimo;
	
	private String observacao;

	//bi-directional many-to-one association to Inspecao
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="idInspecao")
	private Inspecao inspecao;

	//bi-directional many-to-one association to Funcionario
	@ManyToOne
	@JoinColumn(name="cpfRelator")
	private Funcionario funcionarioRelator;

	//bi-directional many-to-many association to NaoConformidade
	@ManyToMany
	private List<NaoConformidade> naoConformidades;

	public Relatorio() {
	}	
	
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public boolean getUltimo() {
		return ultimo;
	}

	public void setUltimo(boolean ultimo) {
		this.ultimo = ultimo;
	}

	public Date getDataInicioRelatorio() {
		return dataInicioRelatorio;
	}

	public void setDataInicioRelatorio(Date dataInicioRelatorio) {
		this.dataInicioRelatorio = dataInicioRelatorio;
	}

	public Date getDataFimRelatorio() {
		return dataFimRelatorio;
	}

	public void setDataFimRelatorio(Date dataFimRelatorio) {
		this.dataFimRelatorio = dataFimRelatorio;
	}

	public long getIdRelatorio() {
		return this.idRelatorio;
	}

	public void setIdRelatorio(long idRelatorio) {
		this.idRelatorio = idRelatorio;
	}

	public Inspecao getInspecao() {
		return this.inspecao;
	}

	public void setInspecao(Inspecao inspecao) {
		this.inspecao = inspecao;
	}

	public Funcionario getFuncionarioRelator() {
		return this.funcionarioRelator;
	}

	public void setFuncionarioRelator(Funcionario funcionario) {
		this.funcionarioRelator = funcionario;
	}

	public List<NaoConformidade> getNaoConformidades() {
		return this.naoConformidades;
	}

	public void setNaoConformidades(List<NaoConformidade> naoConformidades) {
		this.naoConformidades = naoConformidades;
	}

	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
/*
		StringBuilder sb = new StringBuilder();
		sb.append("dataFimRelatorio: ").append(this.getDataFimRelatorio() == null ? "" :this.getDataFimRelatorio().toString()).append("|")
		.append("dataInicioRelatorio: ").append(this.getDataInicioRelatorio() == null ? "" :this.getDataInicioRelatorio().toString()).append("|")
		.append("dataFuncionarioRelator: ").append(this.getFuncionarioRelator() == null ? "" :this.getFuncionarioRelator().toString()).append("|")
		.append("observacao: ").append(this.getObservacao() == null ? "" :this.getObservacao().toString()).append("|")
		
		.append("naoConformidades: ");
		for(NaoConformidade nc : this.getNaoConformidades())
		{
			sb.append(nc.getDescNaoConformidade() == null ? "" : nc.getDescNaoConformidade()).append("|");
		}
		sb.append("ultimo: ").append(this.getUltimo());
		return sb.toString();
		*/
	}
}