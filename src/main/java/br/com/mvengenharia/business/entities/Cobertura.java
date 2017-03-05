package br.com.mvengenharia.business.entities;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@NamedQuery(name="Cobertura.findAll", query="SELECT c FROM Cobertura c")
public class Cobertura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idCobertura;
	
	private String codigoCobertura;

	private String descCobertura;
	
	private boolean situacao;
	
	@ManyToOne
	@JoinColumn(name="idGrupoCobertura")
	private GrupoCobertura grupoCobertura;
	
	public String getCodigoCobertura() {
		return codigoCobertura;
	}

	public void setCodigoCobertura(String codigoCobertura) {
		this.codigoCobertura = codigoCobertura;
	}

	public GrupoCobertura getGrupoCobertura() {
		return grupoCobertura;
	}

	public void setGrupoCobertura(GrupoCobertura grupoCobertura) {
		this.grupoCobertura = grupoCobertura;
	}

	public boolean getSituacao() {
		return situacao;
	}

	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}

	public Cobertura() {
	}

	public long getIdCobertura() {
		return this.idCobertura;
	}

	public void setIdCobertura(long idCobertura) {
		this.idCobertura = idCobertura;
	}

	public String getDescCobertura() {
		return this.descCobertura;
	}

	public void setDescCobertura(String descCobertura) {
		this.descCobertura = descCobertura;
	}
	


}