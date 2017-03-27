package br.com.mvengenharia.business.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.Date;


/**
 * The persistent class for the Vistoria database table.
 * 
 */
@Entity
@NamedQuery(name="Vistoria.findAll", query="SELECT v FROM Vistoria v")
public class Vistoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idVistoria;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHoraChegadaLocal;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dtInicioInspecao;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dtFimInspecao;


	//bi-directional many-to-one association to Inspecao
	@JsonBackReference
	@OneToOne
	@JoinColumn(name="idInspecao")
	private Inspecao inspecao;

	//bi-directional many-to-one association to Funcionario
	@ManyToOne
	@JoinColumn(name="cpfVistoriador")
	private Funcionario funcionario;

	public Vistoria() {
	}

	public long getIdVistoria() {
		return this.idVistoria;
	}

	public void setIdVistoria(long idVistoria) {
		this.idVistoria = idVistoria;
	}

	public Date getDataHoraChegadaLocal() {
		return this.dataHoraChegadaLocal;
	}

	public void setDataHoraChegadaLocal(Date dataHoraChegadaLocal) {
		this.dataHoraChegadaLocal = dataHoraChegadaLocal;
	}

	
	public Date getDtInicioInspecao() {
		return dtInicioInspecao;
	}

	public void setDtInicioInspecao(Date dtInicioInspecao) {
		this.dtInicioInspecao = dtInicioInspecao;
	}

	public Date getDtFimInspecao() {
		return dtFimInspecao;
	}

	public void setDtFimInspecao(Date dtFimInspecao) {
		this.dtFimInspecao = dtFimInspecao;
	}

	public Inspecao getInspecao() {
		return this.inspecao;
	}

	public void setInspecao(Inspecao inspecao) {
		this.inspecao = inspecao;
	}

	public Funcionario getFuncionario() {
		return this.funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

}