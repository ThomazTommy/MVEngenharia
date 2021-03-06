package br.com.mvengenharia.business.entities;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * The persistent class for the Atividade database table.
 * 
 */
@Entity
@NamedQuery(name="Atividade.findAll", query="SELECT a FROM Atividade a")
public class Atividade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idAtividade;

	private String descAtividade;
	
	private boolean situacao;
	
	public boolean getSituacao() {
		return situacao;
	}

	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}

	public Atividade() {
	}

	public long getIdAtividade() {
		return this.idAtividade;
	}

	public void setIdAtividade(long idAtividade) {
		this.idAtividade = idAtividade;
	}

	public String getDescAtividade() {
		return this.descAtividade;
	}

	public void setDescAtividade(String descAtividade) {
		this.descAtividade = descAtividade;
	}
	
	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
/*
		StringBuilder sb = new StringBuilder();
		sb.append("descAtividade: ").append(this.getDescAtividade() == null ? "" :this.getDescAtividade().toString());
		return sb.toString();
*/
	}

}