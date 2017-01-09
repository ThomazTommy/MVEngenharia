package br.com.mvengenharia.business.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Inspecao_Cobertura database table.
 * 
 */
@Entity
@NamedQuery(name="Inspecao_Cobertura.findAll", query="SELECT i FROM Inspecao_Cobertura i")
public class Inspecao_Cobertura implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idInspecaoCobertura;

	private double franquia;

	private double limiteMaxRisco;

	private double pos;

	private double vr;

	//bi-directional many-to-one association to Inspecao
	@ManyToOne
	@JoinColumn(name="idInspecao")
	private Inspecao inspecao;

	@ManyToOne
	@JoinColumn(name="idCobertura")
	private Cobertura cobertura;
	
	public Inspecao_Cobertura() {
	}

	public double getFranquia() {
		return this.franquia;
	}

	public void setFranquia(double franquia) {
		this.franquia = franquia;
	}

	public Cobertura getCobertura() {
		return this.cobertura;
	}

	public void setCobertura(Cobertura cobertura) {
		this.cobertura = cobertura;
	}

	public double getLimiteMaxRisco() {
		return this.limiteMaxRisco;
	}

	public void setLimiteMaxRisco(double limiteMaxRisco) {
		this.limiteMaxRisco = limiteMaxRisco;
	}

	public double getPos() {
		return this.pos;
	}

	public void setPos(double pos) {
		this.pos = pos;
	}

	public double getVr() {
		return this.vr;
	}

	public void setVr(double vr) {
		this.vr = vr;
	}

	public Inspecao getInspecao() {
		return this.inspecao;
	}

	public void setInspecao(Inspecao inspecao) {
		this.inspecao = inspecao;
	}

	public long getIdInspecaoCobertura() {
		return idInspecaoCobertura;
	}

	public void setIdInspecaoCobertura(long idInspecaoCobertura) {
		this.idInspecaoCobertura = idInspecaoCobertura;
	}

}