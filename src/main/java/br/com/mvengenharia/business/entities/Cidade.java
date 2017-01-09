package br.com.mvengenharia.business.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Cidade database table.
 * 
 */
@Entity
@NamedQuery(name="Cidade.findAll", query="SELECT c FROM Cidade c")
public class Cidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idCidade;

	private String nomeCidade;

	//bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="idEstado")
	private Estado estado;

	//bi-directional many-to-one association to Endereco
	//@OneToMany(mappedBy="cidade")
	//private List<Endereco> enderecos;

	public Cidade() {
	}

	public long getIdCidade() {
		return this.idCidade;
	}

	public void setIdCidade(long idCidade) {
		this.idCidade = idCidade;
	}

	public String getNomeCidade() {
		return this.nomeCidade;
	}

	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	//public List<Endereco> getEnderecos() {
	//	return this.enderecos;
	//}

	//public void setEnderecos(List<Endereco> enderecos) {
	//	this.enderecos = enderecos;
	//}

	//public Endereco addEndereco(Endereco endereco) {
	//	getEnderecos().add(endereco);
	//	endereco.setCidade(this);

	//	return endereco;
	//}

	//public Endereco removeEndereco(Endereco endereco) {
	//	getEnderecos().remove(endereco);
	//	endereco.setCidade(null);

	//	return endereco;
	//}

}