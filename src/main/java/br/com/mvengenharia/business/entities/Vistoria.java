package br.com.mvengenharia.business.entities;

import java.io.Serializable;
import javax.persistence.*;
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
	private Date dataHoraFinalInspecao;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHoraInicioInspecao;

	//bi-directional many-to-one association to Inspecao
	@ManyToOne
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

	public Date getDataHoraFinalInspecao() {
		return this.dataHoraFinalInspecao;
	}

	public void setDataHoraFinalInspecao(Date dataHoraFinalInspecao) {
		this.dataHoraFinalInspecao = dataHoraFinalInspecao;
	}

	public Date getDataHoraInicioInspecao() {
		return this.dataHoraInicioInspecao;
	}

	public void setDataHoraInicioInspecao(Date dataHoraInicioInspecao) {
		this.dataHoraInicioInspecao = dataHoraInicioInspecao;
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