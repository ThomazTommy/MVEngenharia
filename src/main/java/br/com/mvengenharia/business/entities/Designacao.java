package br.com.mvengenharia.business.entities;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.Date;

/**
 * The persistent class for the Designacao database table.
 * 
 */
@Entity
@NamedQuery(name = "Designacao.findAll", query = "SELECT d FROM Designacao d")
public class Designacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idDesignacao;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataDesignacao;

	private boolean ultima;

	// bi-directional many-to-one association to Inspecao
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "idInspecao")
	private Inspecao inspecao;

	// bi-directional many-to-one association to Funcionario
	@ManyToOne
	@JoinColumn(name = "cpfDesignado")
	private Funcionario funcionarioDesignado;

	// bi-directional many-to-one association to Funcionario
	@ManyToOne
	@JoinColumn(name = "cpfDesignador")
	private Funcionario funcionarioDesignador;

	public boolean getUltima() {
		return ultima;
	}

	public void setUltima(boolean ultima) {
		this.ultima = ultima;
	}

	public Funcionario getFuncionarioDesignado() {
		return funcionarioDesignado;
	}

	public void setFuncionarioDesignado(Funcionario funcionarioDesignado) {
		this.funcionarioDesignado = funcionarioDesignado;
	}

	public Funcionario getFuncionarioDesignador() {
		return funcionarioDesignador;
	}

	public void setFuncionarioDesignador(Funcionario funcionarioDesignador) {
		this.funcionarioDesignador = funcionarioDesignador;
	}

	public Designacao() {
	}

	public long getIdDesignacao() {
		return this.idDesignacao;
	}

	public void setIdDesignacao(long idDesignacao) {
		this.idDesignacao = idDesignacao;
	}

	public Date getDataDesignacao() {
		return this.dataDesignacao;
	}

	public void setDataDesignacao(Date dataDesignacao) {
		this.dataDesignacao = dataDesignacao;
	}

	public Inspecao getInspecao() {
		return this.inspecao;
	}

	public void setInspecao(Inspecao inspecao) {
		this.inspecao = inspecao;
	}

	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
/*
		StringBuilder sb = new StringBuilder();
		sb.append("dataDesignacao: ").append(this.getDataDesignacao() == null ? "" :this.getDataDesignacao().toString()).append("|");
		sb.append("funcionarioDesignado: ").append(this.getFuncionarioDesignado() == null ? "" :this.getFuncionarioDesignado().toString()).append("|");
		sb.append("FuncionarioDesignador: ").append(this.getFuncionarioDesignador() == null ? "" : this.getFuncionarioDesignador().toString()).append("|");
		sb.append("ultima: ").append(this.getUltima());		
		return sb.toString();
		*/
	}
	
}