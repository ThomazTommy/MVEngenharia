package br.com.mvengenharia.business.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.List;


/**
 * The persistent class for the Segurado database table.
 * 
 */
@Entity
@NamedQuery(name="Segurado.findAll", query="SELECT s FROM Segurado s")
public class Segurado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idSegurado;

	@NotNull(message = "CPF ou CNPJ nos formatos 999.999.999-99 ou 99.999.999/9999-99")
	@Pattern(regexp="([0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2}|[0-9]{2}\\.[0-9]{3}\\.[0-9]{3}\\/[0-9]{4}\\-[0-9]{2})", message = "CPF ou CNPJ nos formatos 999.999.999-99 ou 99.999.999/9999-99")
	@Column(name="cpf_cnpj")
	private String cpfCnpj;

	@NotNull(message = "Nome do Segurado deve ser preenchido")
	@Size(min = 1, message = "Nome do Segurado deve ser preenchido")
	private String nomeSegurado;
	
	@NotNull(message = "Telefone do Segurado deve ser preenchido")
	@Pattern(regexp = "\\([1-9]{2}\\) [2-9][0-9]{3,4}\\-[0-9]{4}", message = "O telefone deve estar no seguinte formato: (99) 99999-9999 ou (99) 9999-9999")
	private String telefoneSegurado;

	public String getTelefoneSegurado() {
		return telefoneSegurado;
	}

	public void setTelefoneSegurado(String telefoneSegurado) {
		this.telefoneSegurado = telefoneSegurado;
	}

	//bi-directional many-to-one association to Inspecao
	@JsonBackReference
	@OneToMany(mappedBy="segurado")
	private List<Inspecao> inspecaos;

	public Segurado() {
	}

	public int getIdSegurado() {
		return this.idSegurado;
	}

	public void setIdSegurado(int idSegurado) {
		this.idSegurado = idSegurado;
	}

	public String getCpfCnpj() {
		return this.cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getNomeSegurado() {
		return this.nomeSegurado;
	}

	public void setNomeSegurado(String nomeSegurado) {
		this.nomeSegurado = nomeSegurado;
	}

	public List<Inspecao> getInspecaos() {
		return this.inspecaos;
	}

	public void setInspecaos(List<Inspecao> inspecaos) {
		this.inspecaos = inspecaos;
	}
	
	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
/*
		StringBuilder sb = new StringBuilder();
		sb.append("cpfCnpj: ").append(this.getCpfCnpj() == null ? "" :this.getCpfCnpj().toString()).append("|")
		.append("nomeSegurado: ").append(this.getNomeSegurado() == null ? "" :this.getNomeSegurado().toString()).append("|")
		.append("telefoneSegurado: ").append(this.getTelefoneSegurado() == null ? "" : this.getTelefoneSegurado().toString());		
		return sb.toString();
		*/
	}

}