package br.com.mvengenharia.business.entities;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@NamedQuery(name="NaoConformidade.findAll", query="SELECT n FROM NaoConformidade n")
public class NaoConformidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idNaoConformidade;

	private String descNaoConformidade;
	
private boolean situacao;
	
	public boolean getSituacao() {
		return situacao;
	}

	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}

	//bi-directional many-to-one association to Gravidade
	@ManyToOne
	@JoinColumn(name="idGravidade")
	private Gravidade gravidade;

	public NaoConformidade() {
	}

	public long getIdNaoConformidade() {
		return this.idNaoConformidade;
	}

	public void setIdNaoConformidade(long idNaoConformidade) {
		this.idNaoConformidade = idNaoConformidade;
	}

	public String getDescNaoConformidade() {
		return this.descNaoConformidade;
	}

	public void setDescNaoConformidade(String descNaoConformidade) {
		this.descNaoConformidade = descNaoConformidade;
	}

	public Gravidade getGravidade() {
		return this.gravidade;
	}

	public void setGravidade(Gravidade gravidade) {
		this.gravidade = gravidade;
	}

}