package br.com.mvengenharia.business.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Relatorio database table.
 * 
 */
@Entity
@NamedQuery(name="Relatorio.findAll", query="SELECT r FROM Relatorio r")
public class Relatorio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idRelatorio;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInicioRelatorio;
	

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFimRelatorio;

	//bi-directional many-to-one association to Inspecao
	@ManyToOne
	@JoinColumn(name="idInspecao")
	private Inspecao inspecao;

	//bi-directional many-to-one association to Funcionario
	@ManyToOne
	@JoinColumn(name="cpfRelator")
	private Funcionario funcionario;

	//bi-directional many-to-many association to NaoConformidade
	@ManyToMany
	private List<NaoConformidade> naoConformidades;

	public Relatorio() {
	}

	public Date getDataInicioRelatorio() {
		return dataInicioRelatorio;
	}

	public void setDataInicioRelatorio(Date dataInicioRelatorio) {
		this.dataInicioRelatorio = dataInicioRelatorio;
	}

	public Date getDataFimRelatorio() {
		return dataFimRelatorio;
	}

	public void setDataFimRelatorio(Date dataFimRelatorio) {
		this.dataFimRelatorio = dataFimRelatorio;
	}

	public long getIdRelatorio() {
		return this.idRelatorio;
	}

	public void setIdRelatorio(long idRelatorio) {
		this.idRelatorio = idRelatorio;
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

	public List<NaoConformidade> getNaoConformidades() {
		return this.naoConformidades;
	}

	public void setNaoConformidades(List<NaoConformidade> naoConformidades) {
		this.naoConformidades = naoConformidades;
	}

}