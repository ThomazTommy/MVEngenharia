package br.com.mvengenharia.business.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


/**
 * The persistent class for the Cidade database table.
 * 
 */
@Entity
@NamedQuery(name="Cidade.findAll", query="SELECT c FROM Cidade c")
public class Cidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idCidade;

	@NotNull
	private String nomeCidade;
	
	private boolean situacao = true;
	
	public boolean getSituacao() {
		return situacao;
	}

	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}

	//bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="idEstado")
	private Estado estado;

	public Cidade() {
	}

	public long getIdCidade() {
		return this.idCidade;
	}

	public void setIdCidade(long idCidade) {
		this.idCidade = idCidade;
	}

	public String getNomeCidade() {
		return this.nomeCidade;
	}

	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("nomeCidade: ").append(this.getNomeCidade());
		
		
		return sb.toString();
	}
}