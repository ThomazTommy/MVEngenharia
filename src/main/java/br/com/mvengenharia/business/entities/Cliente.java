package br.com.mvengenharia.business.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


@Entity
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idCliente;

	private String descCliente;

	private int prazoCliente;
	
private boolean situacao;
	
	public boolean getSituacao() {
		return situacao;
	}

	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}

	//bi-directional many-to-many association to Ramo
	@ManyToMany(cascade={CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST})
	@JoinTable(
		name="Cliente_Ramos"
		, joinColumns={
			@JoinColumn(name="idCliente")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idRamos")
			}
		)
	private List<Ramo> ramos;

	//bi-directional many-to-many association to TipoInspecao
	@ManyToMany(cascade={CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST})
	@JoinTable(
		name="Cliente_TipoInspecao"
		, joinColumns={
			@JoinColumn(name="idCliente")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idTipoInspecao")
			}
		)
	private List<TipoInspecao> tipoInspecaos;

	
	public Cliente() {
	}

	public long getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}

	public String getDescCliente() {
		return this.descCliente;
	}

	public void setDescCliente(String descCliente) {
		this.descCliente = descCliente;
	}

	public int getPrazoCliente() {
		return this.prazoCliente;
	}

	public void setPrazoCliente(int prazoCliente) {
		this.prazoCliente = prazoCliente;
	}

	public List<Ramo> getRamos() {
		return this.ramos;
	}

	public void setRamos(List<Ramo> ramos) {
		this.ramos = ramos;
	}

	public List<TipoInspecao> getTipoInspecaos() {
		return this.tipoInspecaos;
	}

	public void setTipoInspecaos(List<TipoInspecao> tipoInspecaos) {
		this.tipoInspecaos = tipoInspecaos;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("descCliente: ").append(this.getDescCliente());
		
		return sb.toString();
	}
	
}