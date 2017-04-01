package br.com.mvengenharia.business.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Ramos database table.
 * 
 */
@Entity
@NamedQuery(name="Ramo.findAll", query="SELECT r FROM Ramo r")
public class Ramo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idRamo;

	private String descRamo;
	
private boolean situacao;
	
	public boolean getSituacao() {
		return situacao;
	}

	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}

	public Ramo() {
	}

	public long getIdRamo() {
		return this.idRamo;
	}

	public void setIdRamo(long idRamo) {
		this.idRamo = idRamo;
	}

	public String getDescRamo() {
		return this.descRamo;
	}

	public void setDescRamo(String descRamos) {
		this.descRamo = descRamos;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("descRamo: ").append(this.getDescRamo() == null ? "" :this.getDescRamo().toString());
		return sb.toString();
	}

}