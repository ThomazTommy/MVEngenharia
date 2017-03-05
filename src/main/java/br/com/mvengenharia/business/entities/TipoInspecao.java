package br.com.mvengenharia.business.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQuery(name="TipoInspecao.findAll", query="SELECT t FROM TipoInspecao t")
public class TipoInspecao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idTipoInspecao;

	private String descTipoInspecao;
	
private boolean situacao;
	
	public boolean getSituacao() {
		return situacao;
	}

	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}

	public TipoInspecao() {
	}

	public long getIdTipoInspecao() {
		return this.idTipoInspecao;
	}

	public void setIdTipoInspecao(long idTipoInspecao) {
		this.idTipoInspecao = idTipoInspecao;
	}

	public String getDescTipoInspecao() {
		return this.descTipoInspecao;
	}

	public void setDescTipoInspecao(String descTipoInspecao) {
		this.descTipoInspecao = descTipoInspecao;
	}

}