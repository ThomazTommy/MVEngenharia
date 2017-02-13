package br.com.mvengenharia.business.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the Agendamento database table.
 * 
 */
@Entity
@NamedQuery(name="Agendamento.findAll", query="SELECT a FROM Agendamento a")
public class Agendamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idAgendamento;

	private byte confirmacao;
	
	@NotNull(message="Campo CPF Funcionario não pode ficar vazio")
	@ManyToOne
	@JoinColumn(name="cpfFuncionario")
	private Funcionario funcionario;
	
	public Funcionario getFuncionarioConfirmacao() {
		return funcionarioConfirmacao;
	}

	public void setFuncionarioConfirmacao(Funcionario funcionarioConfirmacao) {
		this.funcionarioConfirmacao = funcionarioConfirmacao;
	}

	@ManyToOne
	@JoinColumn(name="cpfFuncionarioConfirmacao")
	private Funcionario funcionarioConfirmacao;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dtAgendada;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dtAgendamento;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dtConfirmacao;
	
	@Size(min=1, message="Campo nome Contato não pode ficar vazio")
	private String nomeContato;
	
	@Pattern(regexp = "\\([1-9]{2}\\) [2-9][0-9]{3,4}\\-[0-9]{4}", message = "O telefone deve estar no seguinte formato: (99) 99999-9999 ou (99) 9999-9999")
	private String telefoneContato;

	public String getNomeContato() {
		return nomeContato;
	}

	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}

	public String getTelefoneContato() {
		return telefoneContato;
	}

	public void setTelefoneContato(String telefoneContato) {
		this.telefoneContato = telefoneContato;
	}

	public void setIdAgendamento(long idAgendamento) {
		this.idAgendamento = idAgendamento;
	}

	//bi-directional many-to-one association to Inspecao
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="idInspecao")
	private Inspecao inspecao;

	public Agendamento() {
	}

	public long getIdAgendamento() {
		return this.idAgendamento;
	}

	public void setIdAgendamento(int idAgendamento) {
		this.idAgendamento = idAgendamento;
	}

	public byte getConfirmacao() {
		return this.confirmacao;
	}

	public void setConfirmacao(byte confirmacao) {
		this.confirmacao = confirmacao;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Date getDtAgendada() {
		return this.dtAgendada;
	}

	public void setDtAgendada(Date dtAgendada) {
		this.dtAgendada = dtAgendada;
	}

	public Date getDtAgendamento() {
		return this.dtAgendamento;
	}

	public void setDtAgendamento(Date dtAgendamento) {
		this.dtAgendamento = dtAgendamento;
	}

	public Date getDtConfirmacao() {
		return this.dtConfirmacao;
	}

	public void setDtConfirmacao(Date dtConfirmacao) {
		this.dtConfirmacao = dtConfirmacao;
	}

	public Inspecao getInspecao() {
		return this.inspecao;
	}

	public void setInspecao(Inspecao inspecao) {
		this.inspecao = inspecao;
	}

}