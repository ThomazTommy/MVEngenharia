package br.com.mvengenharia.business.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;



@Entity
@NamedQuery(name="Fase.findAll", query="SELECT s FROM Fase s")
public class Fase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idFase;

	private String descFase;
	
	@ManyToOne
	@JoinColumn(name="idStatus")
	private Status status;
	
	private boolean situacao;	
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public boolean getSituacao() {
		return situacao;
	}

	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}

	public Fase() {
	}

	public long getIdFase() {
		return this.idFase;
	}

	public void setIdFase(long idFase) {
		this.idFase = idFase;
	}

	public String getDescFase() {
		return this.descFase;
	}

	public void setDescFase(String descFase) {
		this.descFase = descFase;
	}

}