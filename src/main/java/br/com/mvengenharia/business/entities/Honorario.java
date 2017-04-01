package br.com.mvengenharia.business.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

	@NotNull(message = "Memória de cálculo deve ser preenchida")
	@Size(min = 1, message = "Memória de cálculo deve ser preenchida")
	private String descCondicoes;

	private boolean flagAlteracao;

	@NotNull(message = "Motivo da alteração deve ser preenchido")
	@Size(min = 1, message = "Motivo da alteração deve ser preenchido")
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
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("decCondicoes: ").append(this.getDescCondicoes() == null ? "" :this.getDescCondicoes().toString()).append("|")
		.append("funcionarioAlterador: ").append(this.getFuncionarioAlterador() == null ? "" :this.getFuncionarioAlterador().toString()).append("|")
		.append("valorHonorarioCalculado: ").append(this.getValorHonorarioCalculado() == null ? "" : this.getValorHonorarioCalculado().toString()).append("|")
		.append("motivoAlteracao: ").append(this.getMotivoAlteracao() == null ? "" : this.getMotivoAlteracao().toString());			
		return sb.toString();
	}

}