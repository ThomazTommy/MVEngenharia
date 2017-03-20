package br.com.mvengenharia.business.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
	
	@NotNull(message = "Data não pode ser nula")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHoraInsercaoSistemaCliente;

	@NotNull(message = "Data não pode ser nula")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHoraInsercao;
	
	boolean ultimo;
	
	//bi-directional many-to-one association to Inspecao
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="idInspecao")
	private Inspecao inspecao;

	//bi-directional many-to-one association to Funcionario
	@ManyToOne
	@JoinColumn(name="cpfResponsavelInsercao")
	private Funcionario funcionarioResponsavelInsercao;

	public InsercaoSistema() {
	}

	
	
	public boolean isUltimo() {
		return ultimo;
	}



	public void setUltimo(boolean ultimo) {
		this.ultimo = ultimo;
	}



	public long getIdInsercaoSistema() {
		return idInsercaoSistema;
	}

	public void setIdInsercaoSistema(long idInsercaoSistema) {
		this.idInsercaoSistema = idInsercaoSistema;
	}

	public Date getDataHoraInsercaoSistemaCliente() {
		return dataHoraInsercaoSistemaCliente;
	}

	public void setDataHoraInsercaoSistemaCliente(Date dataHoraInsercaoSistemaCliente) {
		this.dataHoraInsercaoSistemaCliente = dataHoraInsercaoSistemaCliente;
	}

	public Date getDataHoraInsercao() {
		return dataHoraInsercao;
	}

	public void setDataHoraInsercao(Date dataHoraInsercao) {
		this.dataHoraInsercao = dataHoraInsercao;
	}

	public Inspecao getInspecao() {
		return inspecao;
	}

	public void setInspecao(Inspecao inspecao) {
		this.inspecao = inspecao;
	}

	public Funcionario getFuncionarioResponsavelInsercao() {
		return funcionarioResponsavelInsercao;
	}

	public void setFuncionarioResponsavelInsercao(Funcionario funcionarioResponsavelInsercao) {
		this.funcionarioResponsavelInsercao = funcionarioResponsavelInsercao;
	}

}