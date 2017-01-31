package br.com.mvengenharia.business.entities;

import java.io.Serializable;
import javax.persistence.*;
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

	private BigDecimal cpfAgendador;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dtAgendada;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dtAgendamento;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dtConfirmacao;
	
	private String nomeContato;
	
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

	public BigDecimal getCpfAgendador() {
		return this.cpfAgendador;
	}

	public void setCpfAgendador(BigDecimal cpfAgendador) {
		this.cpfAgendador = cpfAgendador;
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