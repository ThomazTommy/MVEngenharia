package br.com.mvengenharia.business.entities;

import java.io.Serializable;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.Date;


/**
 * The persistent class for the InsercaoSistema database table.
 * 
 */
@Entity
@NamedQuery(name="AprovacaoSistema.findAll", query="SELECT i FROM AprovacaoSistema i")
public class AprovacaoSistema implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idAprovacaoSistema;

	private boolean aprovacaoSemRessalvas;

	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHoraAprovacaoSistemaCliente;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHoraAprovacao;		
	
	private boolean ultimo;
	
	//bi-directional many-to-one association to Inspecao
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="idInspecao")
	private Inspecao inspecao;

	//bi-directional many-to-one association to Funcionario
	@ManyToOne
	@JoinColumn(name="cpfAprovador")
	private Funcionario funcionarioAprovador;

	public AprovacaoSistema() {
	}

	
	
	public boolean isUltimo() {
		return ultimo;
	}



	public void setUltimo(boolean ultimo) {
		this.ultimo = ultimo;
	}



	public long getIdAprovacaoSistema() {
		return idAprovacaoSistema;
	}

	public void setIdAprovacaoSistema(long idAprovacaoSistema) {
		this.idAprovacaoSistema = idAprovacaoSistema;
	}

	public boolean getAprovacaoSemRessalvas() {
		return aprovacaoSemRessalvas;
	}

	public void setAprovacaoSemRessalvas(boolean aprovacaoSemRessalvas) {
		this.aprovacaoSemRessalvas = aprovacaoSemRessalvas;
	}

	public Date getDataHoraAprovacaoSistemaCliente() {
		return dataHoraAprovacaoSistemaCliente;
	}

	public void setDataHoraAprovacaoSistemaCliente(Date dataHoraAprovacaoSistemaCliente) {
		this.dataHoraAprovacaoSistemaCliente = dataHoraAprovacaoSistemaCliente;
	}

	public Date getDataHoraAprovacao() {
		return dataHoraAprovacao;
	}

	public void setDataHoraAprovacao(Date dataHoraAprovacao) {
		this.dataHoraAprovacao = dataHoraAprovacao;
	}

	public Inspecao getInspecao() {
		return inspecao;
	}

	public void setInspecao(Inspecao inspecao) {
		this.inspecao = inspecao;
	}

	public Funcionario getFuncionarioAprovador() {
		return funcionarioAprovador;
	}

	public void setFuncionarioAprovador(Funcionario funcionarioAprovador) {
		this.funcionarioAprovador = funcionarioAprovador;
	}

}