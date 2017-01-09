package br.com.mvengenharia.business.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Honorario database table.
 * 
 */
@Entity
@NamedQuery(name="Honorario.findAll", query="SELECT h FROM Honorario h")
public class Honorario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idHonorario;

	private String cpfAlterador;

	private String descCondicoes;

	private byte flagAlteracao;

	private String motivoAlteracao;

	private double valorHonorarioCalculado;

	//bi-directional many-to-one association to Inspecao
	@OneToOne
	@JoinColumn(name="idInspecao")
	private Inspecao inspecao;

	public Honorario() {
	}

	public long getIdHonorario() {
		return this.idHonorario;
	}

	public void setIdHonorario(long idHonorario) {
		this.idHonorario = idHonorario;
	}

	public String getCpfAlterador() {
		return this.cpfAlterador;
	}

	public void setCpfAlterador(String cpfAlterador) {
		this.cpfAlterador = cpfAlterador;
	}

	public String getDescCondicoes() {
		return this.descCondicoes;
	}

	public void setDescCondicoes(String descCondicoes) {
		this.descCondicoes = descCondicoes;
	}

	public byte getFlagAlteracao() {
		return this.flagAlteracao;
	}

	public void setFlagAlteracao(byte flagAlteracao) {
		this.flagAlteracao = flagAlteracao;
	}

	public String getMotivoAlteracao() {
		return this.motivoAlteracao;
	}

	public void setMotivoAlteracao(String motivoAlteracao) {
		this.motivoAlteracao = motivoAlteracao;
	}

	public double getValorHonorarioCalculado() {
		return this.valorHonorarioCalculado;
	}

	public void setValorHonorarioCalculado(double valorHonorarioCalculado) {
		this.valorHonorarioCalculado = valorHonorarioCalculado;
	}

	public Inspecao getInspecao() {
		return this.inspecao;
	}

	public void setInspecao(Inspecao inspecao) {
		this.inspecao = inspecao;
		inspecao.setHonorario(this);
	}

}