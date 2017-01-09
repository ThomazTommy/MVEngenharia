package br.com.mvengenharia.business.entities;

import java.io.Serializable;
import javax.persistence.*;


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

	//public List<Endereco> getEnderecos() {
	//	return this.enderecos;
	//}

	//public void setEnderecos(List<Endereco> enderecos) {
	//	this.enderecos = enderecos;
	//}

	//public Endereco addEndereco(Endereco endereco) {
	//	getEnderecos().add(endereco);
	//	endereco.setTipoLogradouro(this);

	//	return endereco;
	//}

	//public Endereco removeEndereco(Endereco endereco) {
	//	getEnderecos().remove(endereco);
	//	endereco.setTipoLogradouro(null);

	//	return endereco;
	//}

}