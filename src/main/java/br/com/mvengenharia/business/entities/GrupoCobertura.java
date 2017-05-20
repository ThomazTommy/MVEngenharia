package br.com.mvengenharia.business.entities;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@NamedQuery(name = "GrupoCobertura.findAll", query = "SELECT c FROM GrupoCobertura c")
public class GrupoCobertura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idGrupoCobertura;

	private String descGrupoCobertura;

	private boolean situacao;

	public boolean getSituacao() {
		return situacao;
	}

	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}

	public GrupoCobertura() {
	}

	public long getIdGrupoCobertura() {
		return this.idGrupoCobertura;
	}

	public void setIdGrupoCobertura(long idGrupoCobertura) {
		this.idGrupoCobertura = idGrupoCobertura;
	}

	public String getDescGrupoCobertura() {
		return this.descGrupoCobertura;
	}

	public void setDescGrupoCobertura(String descGrupoCobertura) {
		this.descGrupoCobertura = descGrupoCobertura;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);

	}

}