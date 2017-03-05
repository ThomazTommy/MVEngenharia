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

	private String email;

	private String nomeFuncionario;

	private String senha;
	
	private boolean situacao;
	
	public boolean getSituacao() {
		return situacao;
	}

	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}
	
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

	
	public List<Permissoes> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissoes> permissoes) {
		this.permissoes = permissoes;
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

	public Escritorio getEscritorio() {
		return this.escritorio;
	}

	public void setEscritorio(Escritorio escritorio) {
		this.escritorio = escritorio;
	}
	

}