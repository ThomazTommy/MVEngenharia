package br.com.mvengenharia.business.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Escritorio database table.
 * 
 */
@Entity
@NamedQuery(name="Escritorio.findAll", query="SELECT e FROM Escritorio e")
public class Escritorio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idEscritorio;

	private String descEscritorio;
	
private boolean situacao;
	
	public boolean getSituacao() {
		return situacao;
	}

	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}

	//bi-directional one-to-one association to Endereco
	@OneToOne(cascade=CascadeType.ALL)
	private Endereco endereco;

	public Escritorio() {
	}

	public long getIdEscritorio() {
		return this.idEscritorio;
	}

	public void setIdEscritorio(long idEscritorio) {
		this.idEscritorio = idEscritorio;
	}

	public String getDescEscritorio() {
		return this.descEscritorio;
	}

	public void setDescEscritorio(String descEscritorio) {
		this.descEscritorio = descEscritorio;
	}

	public Endereco getEndereco() {
		return this.endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
}