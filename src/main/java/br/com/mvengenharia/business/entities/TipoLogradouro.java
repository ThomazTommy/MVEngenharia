package br.com.mvengenharia.business.entities;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * The persistent class for the TipoLogradouro database table.
 * 
 */
@Entity
@NamedQuery(name="TipoLogradouro.findAll", query="SELECT t FROM TipoLogradouro t")
public class TipoLogradouro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idTipoLogradouro;

	private String descTipoLogradouro;
	
	private String abreviatura;
	
	
	
public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

private boolean situacao;
	
	public boolean getSituacao() {
		return situacao;
	}

	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}

	//bi-directional many-to-one association to Endereco
	//@OneToMany(mappedBy="tipoLogradouro")
	//private List<Endereco> enderecos;

	public TipoLogradouro() {
	}

	public long getIdTipoLogradouro() {
		return this.idTipoLogradouro;
	}

	public void setIdTipoLogradouro(long idTipoLogradouro) {
		this.idTipoLogradouro = idTipoLogradouro;
	}

	public String getDescTipoLogradouro() {
		return this.descTipoLogradouro;
	}

	public void setDescTipoLogradouro(String descTipoLogradouro) {
		this.descTipoLogradouro = descTipoLogradouro;
	}

	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
/*
		StringBuilder sb = new StringBuilder();
		sb.append("descTipoLogradouro: ").append(this.getDescTipoLogradouro() == null ? "" :this.getDescTipoLogradouro().toString());		
		return sb.toString();
		*/
	}

}