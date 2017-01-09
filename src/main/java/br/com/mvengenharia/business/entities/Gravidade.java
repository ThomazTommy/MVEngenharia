package br.com.mvengenharia.business.entities;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@NamedQuery(name="Gravidade.findAll", query="SELECT g FROM Gravidade g")
public class Gravidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idGravidade;

	private String descGravidade;

	private int peso;
	
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
	
}