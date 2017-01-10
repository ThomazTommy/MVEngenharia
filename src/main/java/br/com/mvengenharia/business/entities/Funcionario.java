package br.com.mvengenharia.business.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


@Entity
@NamedQuery(name = "Funcionario.findAll", query = "SELECT i FROM Funcionario i")
public class Funcionario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String cpf;

	private boolean ativo;

	private String email;

	private String nomeFuncionario;

	private String numeroMatricula;
	
	private String senha;
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	//bi-directional many-to-one association to Escritorio
	@ManyToOne
	@JoinColumn(name="idEscritorioRegional")
	private Escritorio escritorio;
	
	@ManyToMany(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)	
	private List<Permissoes> permissoes;

	public Funcionario() {
	}

	public boolean getAtivo() {
		return this.ativo;
	}

	public List<Permissoes> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissoes> permissoes) {
		this.permissoes = permissoes;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomeFuncionario() {
		return this.nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	public String getNumeroMatricula() {
		return this.numeroMatricula;
	}

	public void setNumeroMatricula(String numeroMatricula) {
		this.numeroMatricula = numeroMatricula;
	}


	public Escritorio getEscritorio() {
		return this.escritorio;
	}

	public void setEscritorio(Escritorio escritorio) {
		this.escritorio = escritorio;
	}
	

}