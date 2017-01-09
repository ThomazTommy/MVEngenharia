package br.com.mvengenharia.business.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the InsercaoSistema database table.
 * 
 */
@Entity
@NamedQuery(name="InsercaoSistema.findAll", query="SELECT i FROM InsercaoSistema i")
public class InsercaoSistema implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idInsercaoSistema;

	private byte aprovacaoSemRessalvas;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHoraAprovacao;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHoraInsercao;

	//bi-directional many-to-one association to Inspecao
	@ManyToOne
	@JoinColumn(name="idInspecao")
	private Inspecao inspecao;

	//bi-directional many-to-one association to Funcionario
	@ManyToOne
	@JoinColumn(name="cpfSupervisor")
	private Funcionario funcionarioSupervisor;

	//bi-directional many-to-one association to Funcionario
	@ManyToOne
	@JoinColumn(name="cpfResponsavelInsercao")
	private Funcionario funcionarioResponsavelInsercao;

	public InsercaoSistema() {
	}

	public long getIdInsercaoSistema() {
		return this.idInsercaoSistema;
	}

	public void setIdInsercaoSistema(long idInsercaoSistema) {
		this.idInsercaoSistema = idInsercaoSistema;
	}

	public byte getAprovacaoSemRessalvas() {
		return this.aprovacaoSemRessalvas;
	}

	public void setAprovacaoSemRessalvas(byte aprovacaoSemRessalvas) {
		this.aprovacaoSemRessalvas = aprovacaoSemRessalvas;
	}

	public Date getDataHoraAprovacao() {
		return this.dataHoraAprovacao;
	}

	public void setDataHoraAprovacao(Date dataHoraAprovacao) {
		this.dataHoraAprovacao = dataHoraAprovacao;
	}

	public Date getDataHoraInsercao() {
		return this.dataHoraInsercao;
	}

	public void setDataHoraInsercao(Date dataHoraInsercao) {
		this.dataHoraInsercao = dataHoraInsercao;
	}

	public Inspecao getInspecao() {
		return this.inspecao;
	}

	public void setInspecao(Inspecao inspecao) {
		this.inspecao = inspecao;
	}

	public Funcionario getFuncionarioSupervisor() {
		return this.funcionarioSupervisor;
	}

	public void setFuncionarioSupervisor(Funcionario funcionarioSupervisor) {
		this.funcionarioSupervisor = funcionarioSupervisor;
	}

	public Funcionario getFuncionarioResponsavelInsercao() {
		return this.funcionarioResponsavelInsercao;
	}

	public void setFuncionarioResponsavelInsercao(Funcionario funcionarioResponsavelInsercao) {
		this.funcionarioResponsavelInsercao = funcionarioResponsavelInsercao;
	}

}