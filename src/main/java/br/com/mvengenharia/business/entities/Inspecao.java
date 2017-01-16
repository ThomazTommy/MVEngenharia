package br.com.mvengenharia.business.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Inspecao database table.
 * 
 */
@Entity
@NamedQuery(name="Inspecao.findAll", query="SELECT i FROM Inspecao i")
public class Inspecao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idInspecao;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dtSolicitacaoInspecao;

	private String nomeContato;

	private String nomeCorretor;

	private int numInspecaoCliente;

	@Min(value = 1, message="Número da Proposta não pode ser 0")
	private int numPropostaCliente;

	private String observacao;

	private int qtdBlocos;

	private boolean roubo;

	private BigDecimal telefoneContato;

	private BigDecimal telefoneCorretor;

	private double valorTotalRisco;

	//bi-directional many-to-one association to Agendamento
	@OneToMany(mappedBy="inspecao")
	private List<Agendamento> agendamentos;

	//bi-directional many-to-one association to CustoInspecao
	@OneToMany(mappedBy="inspecao")
	private List<CustoInspecao> custoInspecaos;

	//bi-directional many-to-one association to Designacao
	@OneToMany(mappedBy="inspecao")
	private List<Designacao> designacaos;

	//bi-directional many-to-one association to Honorario
	@OneToOne(mappedBy="inspecao")
	private Honorario honorario;

	//bi-directional many-to-one association to InsercaoSistema
	@OneToMany(mappedBy="inspecao")
	private List<InsercaoSistema> insercaoSistemas;

	//bi-directional many-to-one association to TipoInspecao
	@NotNull(message="Campo Tipo de Inspecao não pode ficar vazio")
	@ManyToOne
	@JoinColumn(name="idTipoInspecao")
	private TipoInspecao tipoInspecao;

	//bi-directional many-to-one association to Cliente
	@NotNull(message="Campo Cliente não pode ficar vazio")
	@ManyToOne
	@JoinColumn(name="idCliente")
	private Cliente cliente;

	//bi-directional many-to-one association to Ramo
	@NotNull(message="Ramo deve ser preenchido")
	@ManyToOne
	@JoinColumn(name="idRamo")
	private Ramo ramo;

	//bi-directional many-to-one association to Endereco

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="idEndereco")
	private Endereco endereco;

	//bi-directional many-to-one association to Status
	@NotNull(message="Status deve ser preenchido")
	@ManyToOne
	@JoinColumn(name="idStatus")
	private Status status;

	//bi-directional many-to-one association to Segurado
	@NotNull(message="Dados do Segurado devem ser preenchidos")
	@ManyToOne(cascade={CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST})
	@JoinColumn(name="idSegurado")
	private Segurado segurado;

	//bi-directional many-to-many association to Atividade
	@ManyToMany
	private List<Atividade> inspecaoAtividadeInformada;

	//bi-directional many-to-many association to Atividade
	@ManyToMany
	private List<Atividade> inspecaoAtividadeApurada;

	//bi-directional many-to-one association to Inspecao_Cobertura
	@OneToMany(mappedBy="inspecao")
	private List<Inspecao_Cobertura> inspecaoCoberturas;

	//bi-directional many-to-one association to Relatorio
	@OneToMany(mappedBy="inspecao")
	private List<Relatorio> relatorios;

	//bi-directional many-to-one association to Revisao
	@OneToMany(mappedBy="inspecao")
	private List<Revisao> revisaos;

	//bi-directional many-to-one association to Vistoria
	@OneToMany(mappedBy="inspecao")
	private List<Vistoria> vistorias;

	public Inspecao() {
		
	}

	public long getIdInspecao() {
		return this.idInspecao;
	}

	public void setIdInspecao(long idInspecao) {
		this.idInspecao = idInspecao;
	}

	public Date getDtSolicitacaoInspecao() {
		return this.dtSolicitacaoInspecao;
	}

	public void setDtSolicitacaoInspecao(Date dtSolicitacaoInspecao) {
		this.dtSolicitacaoInspecao = dtSolicitacaoInspecao;
	}

	public String getNomeContato() {
		return this.nomeContato;
	}

	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}

	public String getNomeCorretor() {
		return this.nomeCorretor;
	}

	public void setNomeCorretor(String nomeCorretor) {
		this.nomeCorretor = nomeCorretor;
	}

	public int getNumInspecaoCliente() {
		return this.numInspecaoCliente;
	}

	public void setNumInspecaoCliente(int numInspecaoCliente) {
		this.numInspecaoCliente = numInspecaoCliente;
	}

	public int getNumPropostaCliente() {
		return this.numPropostaCliente;
	}

	public void setNumPropostaCliente(int numPropostaCliente) {
		this.numPropostaCliente = numPropostaCliente;
	}

	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public int getQtdBlocos() {
		return this.qtdBlocos;
	}

	public void setQtdBlocos(int qtdBlocos) {
		this.qtdBlocos = qtdBlocos;
	}

	public boolean getRoubo() {
		return this.roubo;
	}

	public void setRoubo(boolean roubo) {
		this.roubo = roubo;
	}

	public BigDecimal getTelefoneContato() {
		return this.telefoneContato;
	}

	public void setTelefoneContato(BigDecimal telefoneContato) {
		this.telefoneContato = telefoneContato;
	}

	public BigDecimal getTelefoneCorretor() {
		return this.telefoneCorretor;
	}

	public void setTelefoneCorretor(BigDecimal telefoneCorretor) {
		this.telefoneCorretor = telefoneCorretor;
	}

	public double getValorTotalRisco() {
		return this.valorTotalRisco;
	}

	public void setValorTotalRisco(double valorTotalRisco) {
		this.valorTotalRisco = valorTotalRisco;
	}

	public List<Agendamento> getAgendamentos() {
		return this.agendamentos;
	}

	public void setAgendamentos(List<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}

	public Agendamento addAgendamento(Agendamento agendamento) {
		getAgendamentos().add(agendamento);
		agendamento.setInspecao(this);

		return agendamento;
	}

	public Agendamento removeAgendamento(Agendamento agendamento) {
		getAgendamentos().remove(agendamento);
		agendamento.setInspecao(null);

		return agendamento;
	}

	public List<CustoInspecao> getCustoInspecaos() {
		return this.custoInspecaos;
	}

	public void setCustoInspecaos(List<CustoInspecao> custoInspecaos) {
		this.custoInspecaos = custoInspecaos;
	}

	public CustoInspecao addCustoInspecao(CustoInspecao custoInspecao) {
		getCustoInspecaos().add(custoInspecao);
		custoInspecao.setInspecao(this);

		return custoInspecao;
	}

	public CustoInspecao removeCustoInspecao(CustoInspecao custoInspecao) {
		getCustoInspecaos().remove(custoInspecao);
		custoInspecao.setInspecao(null);

		return custoInspecao;
	}

	public List<Designacao> getDesignacaos() {
		return this.designacaos;
	}

	public void setDesignacaos(List<Designacao> designacaos) {
		this.designacaos = designacaos;
	}

	public Designacao addDesignacao(Designacao designacao) {
		getDesignacaos().add(designacao);
		designacao.setInspecao(this);

		return designacao;
	}

	public Designacao removeDesignacao(Designacao designacao) {
		getDesignacaos().remove(designacao);
		designacao.setInspecao(null);

		return designacao;
	}

	public Honorario getHonorario() {
		return this.honorario;
	}

	public void setHonorario(Honorario honorario) {
		this.honorario = honorario;
		honorario.setInspecao(this);
	}

	public List<InsercaoSistema> getInsercaoSistemas() {
		return this.insercaoSistemas;
	}

	public void setInsercaoSistemas(List<InsercaoSistema> insercaoSistemas) {
		this.insercaoSistemas = insercaoSistemas;
	}

	public InsercaoSistema addInsercaoSistema(InsercaoSistema insercaoSistema) {
		getInsercaoSistemas().add(insercaoSistema);
		insercaoSistema.setInspecao(this);

		return insercaoSistema;
	}

	public InsercaoSistema removeInsercaoSistema(InsercaoSistema insercaoSistema) {
		getInsercaoSistemas().remove(insercaoSistema);
		insercaoSistema.setInspecao(null);

		return insercaoSistema;
	}

	public TipoInspecao getTipoInspecao() {
		return this.tipoInspecao;
	}

	public void setTipoInspecao(TipoInspecao tipoInspecao) {
		this.tipoInspecao = tipoInspecao;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Ramo getRamo() {
		return this.ramo;
	}

	public void setRamo(Ramo ramo) {
		this.ramo = ramo;
	}

	public Endereco getEndereco() {
		return this.endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Segurado getSegurado() {
		return this.segurado;
	}

	public void setSegurado(Segurado segurado) {
		this.segurado = segurado;
	}

	public List<Atividade> getInspecaoAtividadeApurada() {
		return this.inspecaoAtividadeApurada;
	}

	public void setInspecaoAtividadeApurada(List<Atividade> inspecao_Atividade_Apurada) {
		this.inspecaoAtividadeApurada = inspecao_Atividade_Apurada;
	}

	public List<Atividade> getInspecaoAtividadeInformada() {
		return this.inspecaoAtividadeInformada;
	}

	public void setInspecaoAtividadeInformada(List<Atividade> inspecao_Atividade_Informada) {
		this.inspecaoAtividadeInformada = inspecao_Atividade_Informada;
	}

	public List<Inspecao_Cobertura> getInspecaoCoberturas() {
		return this.inspecaoCoberturas;
	}

	public void setInspecaoCoberturas(List<Inspecao_Cobertura> inspecaoCoberturas) {
		this.inspecaoCoberturas = inspecaoCoberturas;
	}

	public Inspecao_Cobertura addInspecaoCobertura(Inspecao_Cobertura inspecaoCobertura) {
		getInspecaoCoberturas().add(inspecaoCobertura);
		inspecaoCobertura.setInspecao(this);

		return inspecaoCobertura;
	}

	public Inspecao_Cobertura removeInspecaoCobertura(Inspecao_Cobertura inspecaoCobertura) {
		getInspecaoCoberturas().remove(inspecaoCobertura);
		inspecaoCobertura.setInspecao(null);

		return inspecaoCobertura;
	}

	public List<Relatorio> getRelatorios() {
		return this.relatorios;
	}

	public void setRelatorios(List<Relatorio> relatorios) {
		this.relatorios = relatorios;
	}

	public Relatorio addRelatorio(Relatorio relatorio) {
		getRelatorios().add(relatorio);
		relatorio.setInspecao(this);

		return relatorio;
	}

	public Relatorio removeRelatorio(Relatorio relatorio) {
		getRelatorios().remove(relatorio);
		relatorio.setInspecao(null);

		return relatorio;
	}

	public List<Revisao> getRevisaos() {
		return this.revisaos;
	}

	public void setRevisaos(List<Revisao> revisaos) {
		this.revisaos = revisaos;
	}

	public Revisao addRevisao(Revisao revisao) {
		getRevisaos().add(revisao);
		revisao.setInspecao(this);

		return revisao;
	}

	public Revisao removeRevisao(Revisao revisao) {
		getRevisaos().remove(revisao);
		revisao.setInspecao(null);

		return revisao;
	}

	public List<Vistoria> getVistorias() {
		return this.vistorias;
	}

	public void setVistorias(List<Vistoria> vistorias) {
		this.vistorias = vistorias;
	}

	public Vistoria addVistoria(Vistoria vistoria) {
		getVistorias().add(vistoria);
		vistoria.setInspecao(this);

		return vistoria;
	}

	public Vistoria removeVistoria(Vistoria vistoria) {
		getVistorias().remove(vistoria);
		vistoria.setInspecao(null);

		return vistoria;
	}

}