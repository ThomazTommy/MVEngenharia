package br.com.mvengenharia.business.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * The persistent class for the Honorario database table.
 * 
 */
@Entity
@NamedQuery(name = "Honorario.findAll", query = "SELECT h FROM Honorario h")
public class Honorario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idHonorario;
	
	@ManyToOne
	@JoinColumn(name="cpfResponsavelInsercao")
	private Funcionario funcionarioAlterador;

	private String descCondicoes;

	private boolean flagAlteracao;

	private String motivoAlteracao;

	private BigDecimal valorHonorarioCalculado;

	// bi-directional many-to-one association to Inspecao
	@JsonBackReference
	@OneToOne
	@JoinColumn(name = "idInspecao")
	private Inspecao inspecao;

	public Honorario() {
	}

	public Funcionario getFuncionarioAlterador() {
		return funcionarioAlterador;
	}

	public void setFuncionarioAlterador(Funcionario funcionarioAlterador) {
		this.funcionarioAlterador = funcionarioAlterador;
	}

	public boolean isFlagAlteracao() {
		return flagAlteracao;
	}

	public void setFlagAlteracao(boolean flagAlteracao) {
		this.flagAlteracao = flagAlteracao;
	}

	public BigDecimal getValorHonorarioCalculado() {
		return valorHonorarioCalculado;
	}

	public void setValorHonorarioCalculado(BigDecimal valorHonorarioCalculado) {
		this.valorHonorarioCalculado = valorHonorarioCalculado;
	}

	public long getIdHonorario() {
		return this.idHonorario;
	}

	public void setIdHonorario(long idHonorario) {
		this.idHonorario = idHonorario;
	}

	public String getDescCondicoes() {
		return this.descCondicoes;
	}

	public void setDescCondicoes(String descCondicoes) {
		this.descCondicoes = descCondicoes;
	}

	public String getMotivoAlteracao() {
		return this.motivoAlteracao;
	}

	public void setMotivoAlteracao(String motivoAlteracao) {
		this.motivoAlteracao = motivoAlteracao;
	}

	public Inspecao getInspecao() {
		return this.inspecao;
	}

	public void setInspecao(Inspecao inspecao) {
		this.inspecao = inspecao;		
	}

}