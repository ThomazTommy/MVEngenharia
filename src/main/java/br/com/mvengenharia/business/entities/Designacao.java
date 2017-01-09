package br.com.mvengenharia.business.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the Designacao database table.
 * 
 */
@Entity
@NamedQuery(name="Designacao.findAll", query="SELECT d FROM Designacao d")
public class Designacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idDesignacao;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataDesignacao;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataDivulgacao;

	//bi-directional many-to-one association to Inspecao
	@ManyToOne
	@JoinColumn(name="idInspecao")
	private Inspecao inspecao;

	//bi-directional many-to-one association to Funcionario
	@ManyToOne
	@JoinColumn(name="cpfDesignado")
	private Funcionario funcionario;

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

	public Date getDataDivulgacao() {
		return this.dataDivulgacao;
	}

	public void setDataDivulgacao(Date dataDivulgacao) {
		this.dataDivulgacao = dataDivulgacao;
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

}