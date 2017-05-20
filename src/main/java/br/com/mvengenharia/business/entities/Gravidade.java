package br.com.mvengenharia.business.entities;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang3.builder.ToStringBuilder;


@Entity
@NamedQuery(name="Gravidade.findAll", query="SELECT g FROM Gravidade g")
public class Gravidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idGravidade;

	private String descGravidade;

	private int peso;
	
private boolean situacao;
	
	public boolean getSituacao() {
		return situacao;
	}

	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}
	
	public Gravidade() {
	}

	public long getIdGravidade() {
		return this.idGravidade;
	}

	public void setIdGravidade(long idGravidade) {
		this.idGravidade = idGravidade;
	}

	public String getDescGravidade() {
		return this.descGravidade;
	}

	public void setDescGravidade(String descGravidade) {
		this.descGravidade = descGravidade;
	}

	public int getPeso() {
		return this.peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
/*
		StringBuilder sb = new StringBuilder();
		sb.append("descGravidade: ").append(this.getDescGravidade() == null ? "" :this.getDescGravidade().toString()).append("|");	
		return sb.toString();
		*/
	}
	
}