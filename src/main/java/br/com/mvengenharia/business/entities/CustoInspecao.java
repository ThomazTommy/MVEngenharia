package br.com.mvengenharia.business.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CustoInspecao database table.
 * 
 */
@Entity
@NamedQuery(name="CustoInspecao.findAll", query="SELECT c FROM CustoInspecao c")
public class CustoInspecao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idCustoInspecao;

	private double custoAprovado;

	private double kmEfetivo;

	private double valorDeslocamento;

	//bi-directional many-to-one association to Inspecao
	@ManyToOne
	@JoinColumn(name="idInspecao")
	private Inspecao inspecao;

	public CustoInspecao() {
	}

	public long getIdCustoInspecao() {
		return this.idCustoInspecao;
	}

	public void setIdCustoInspecao(long idCustoInspecao) {
		this.idCustoInspecao = idCustoInspecao;
	}

	public double getCustoAprovado() {
		return this.custoAprovado;
	}

	public void setCustoAprovado(double custoAprovado) {
		this.custoAprovado = custoAprovado;
	}

	public double getKmEfetivo() {
		return this.kmEfetivo;
	}

	public void setKmEfetivo(double kmEfetivo) {
		this.kmEfetivo = kmEfetivo;
	}

	public double getValorDeslocamento() {
		return this.valorDeslocamento;
	}

	public void setValorDeslocamento(double valorDeslocamento) {
		this.valorDeslocamento = valorDeslocamento;
	}

	public Inspecao getInspecao() {
		return this.inspecao;
	}

	public void setInspecao(Inspecao inspecao) {
		this.inspecao = inspecao;
	}

}