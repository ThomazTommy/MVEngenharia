package br.com.mvengenharia.business.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@NamedQuery(name="Permissoes.findAll", query="SELECT s FROM Permissoes s")
public class Permissoes implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idPermissoes;
	
	private String descPermissoes;

	public long getIdPermissoes() {
		return idPermissoes;
	}

	public void setIdPermissoes(long idPermissoes) {
		this.idPermissoes = idPermissoes;
	}

	public String getDescPermissoes() {
		return descPermissoes;
	}

	public void setDescPermissoes(String descPermissoes) {
		this.descPermissoes = descPermissoes;
	}

	public Permissoes(long idPermissoes, String descPermissoes) {
		super();
		this.idPermissoes = idPermissoes;
		this.descPermissoes = descPermissoes;
	}

	public Permissoes() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this);

	}

	
}
