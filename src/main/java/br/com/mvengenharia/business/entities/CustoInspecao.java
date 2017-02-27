package br.com.mvengenharia.business.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;


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

	private BigDecimal valorDeslocamento;
	
	private boolean custoInformado;

	//bi-directional many-to-one association to Inspecao
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="idInspecao")
	private Inspecao inspecao;

	public CustoInspecao() {
	}

	
	
	public boolean getCustoInformado() {
		return custoInformado;
	}



	public void setCustoInformado(boolean custoInformado) {
		this.custoInformado = custoInformado;
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

	public BigDecimal getValorDeslocamento() {
		return this.valorDeslocamento;
	}

	public void setValorDeslocamento(BigDecimal valorDeslocamento) {
		this.valorDeslocamento = valorDeslocamento;
	}

	public Inspecao getInspecao() {
		return this.inspecao;
	}

	public void setInspecao(Inspecao inspecao) {
		this.inspecao = inspecao;
	}

}