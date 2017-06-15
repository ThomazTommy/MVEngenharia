
package br.com.mvengenharia.business.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the Inspecao database table.
 * 
 */
@Entity
@NamedQuery(name = "Inspecao.findAll", query = "SELECT i FROM Inspecao i")
public class Inspecao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idInspecao;

	@NotNull(message = "Data não pode ser nula")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtSolicitacaoInspecao;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dtLimite;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dtAgendada;

	private String nomeCorretor;

	private int numInspecaoCliente;

	@Min(value = 1, message = "Número da Proposta não pode ser 0")
	private int numPropostaCliente;

	@Column(length = 65535, columnDefinition = "Text")
	private String observacao;

	@ManyToOne
	@JoinColumn(name = "idFase")
	private Fase fase;

	@Min(value = 1, message = "Quantidade de Blocos não pode ser 0")
	private int qtdBlocos = 1;

	@NotNull
	private boolean roubo;

	// @Pattern(regexp = "\\([1-9]{2}\\) [2-9][0-9]{3,4}\\-[0-9]{4}", message =
	// "O telefone deve estar no seguinte formato: (99) 99999-9999 ou (99)
	// 9999-9999")
	private String telefoneCorretor;	
	
	@NotNull
	private String nomeContato;
	
	@Pattern(regexp = "\\([1-9]{2}\\) [2-9][0-9]{3,4}\\-[0-9]{4}", message = "O telefone deve estar no seguinte formato: (99) 99999-9999 ou (99) 9999-9999")
	private String telefoneContato;

	@NotNull(message = "Valor do risco não pode ficar vazio.")
	private BigDecimal valorTotalRisco;

	// bi-directional many-to-one association to Agendamento
	@JsonManagedReference
	@OneToMany(mappedBy = "inspecao", cascade = CascadeType.ALL)
	private List<Agendamento> agendamentos;

	// bi-directional many-to-one association to CustoInspecao
	@JsonManagedReference
	@OneToOne(mappedBy = "inspecao", cascade = CascadeType.ALL)
	private CustoInspecao custoInspecao;

	// bi-directional many-to-one association to Designacao
	@JsonManagedReference
	@OneToMany(mappedBy = "inspecao", cascade = CascadeType.ALL)
	private List<Designacao> designacoes;

	// bi-directional many-to-one association to Honorario
	@JsonManagedReference
	@OneToOne(mappedBy = "inspecao", cascade = CascadeType.ALL)
	private Honorario honorario;

	// bi-directional many-to-one association to InsercaoSistema
	@JsonManagedReference
	@OneToMany(mappedBy = "inspecao", cascade = CascadeType.ALL)
	private List<InsercaoSistema> insercaoSistemas;

	@JsonManagedReference
	@OneToMany(mappedBy = "inspecao", cascade = CascadeType.ALL)
	private List<AprovacaoSistema> aprovacaoSistemas;

	// bi-directional many-to-one association to TipoInspecao
	@NotNull(message = "Campo Tipo de Inspecao não pode ficar vazio")
	@ManyToOne
	@JoinColumn(name = "idTipoInspecao")
	private TipoInspecao tipoInspecao;

	// bi-directional many-to-one association to Cliente
	@NotNull(message = "Campo Cliente não pode ficar vazio")
	@ManyToOne
	@JoinColumn(name = "idCliente")
	private Cliente cliente;

	// bi-directional many-to-one association to Ramo
	@NotNull(message = "Ramo deve ser preenchido")
	@ManyToOne
	@JoinColumn(name = "idRamo")
	private Ramo ramo;

	// bi-directional many-to-one association to Endereco
	@Valid
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "idEndereco")
	private Endereco endereco;

	// bi-directional many-to-one association to Status
	@NotNull(message = "Status deve ser preenchido")
	@ManyToOne
	@JoinColumn(name = "idStatus")
	private Status status;

	@OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST })
	@JoinColumn(name = "cpfVistoriador")
	private Funcionario funcionarioVistoriador;
	
	@OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST })
	@JoinColumn(name = "cpfCadastrador")
	private Funcionario funcionarioCadastrador;


	@Temporal(TemporalType.TIMESTAMP)
	private Date dtVistoria;

	// bi-directional many-to-one association to Segurado
	@JsonManagedReference
	@Valid
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST })
	@JoinColumn(name = "idSegurado")
	private Segurado segurado;

	// bi-directional many-to-many association to Atividade
	@Valid
	@NotEmpty(message = "Atividade deve ser preenchido")
	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST })
	private List<Atividade> inspecaoAtividadeInformada;

	// bi-directional many-to-many association to Atividade
	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST })
	private List<Atividade> inspecaoAtividadeApurada;

	// bi-directional many-to-one association to Inspecao_Cobertura
	//@Valid
	//@NotEmpty(message = "Cobertura deve ser preenchido")
	//@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST })
	@Column(length = 65535, columnDefinition = "Text")
	private String coberturas;

	// bi-directional many-to-one association to Relatorio
	@JsonManagedReference
	@OneToMany(mappedBy = "inspecao", cascade = CascadeType.ALL)
	private List<Relatorio> relatorios;

	// bi-directional many-to-one association to Revisao
	@JsonManagedReference
	@OneToMany(mappedBy = "inspecao", cascade = CascadeType.ALL)
	private List<Revisao> revisaos;

	// bi-directional many-to-one association to Vistoria
	@JsonManagedReference
	@OneToOne(mappedBy = "inspecao", cascade = CascadeType.ALL)
	private Vistoria vistoria;

	public Inspecao() {

	}
	
	public Funcionario getFuncionarioCadastrador() {
		return funcionarioCadastrador;
	}

	public void setFuncionarioCadastrador(Funcionario funcionarioCadastrador) {
		this.funcionarioCadastrador = funcionarioCadastrador;
	}

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


	public Funcionario getFuncionarioVistoriador() {
		return funcionarioVistoriador;
	}

	public void setFuncionarioVistoriador(Funcionario funcionarioVistoriador) {
		this.funcionarioVistoriador = funcionarioVistoriador;
	}

	public Date getDtVistoria() {
		return dtVistoria;
	}

	public void setDtVistoria(Date dtVistoria) {
		this.dtVistoria = dtVistoria;
	}

	public Fase getFase() {
		return fase;
	}

	public void setFase(Fase fase) {
		this.fase = fase;
	}

	public List<AprovacaoSistema> getAprovacaoSistemas() {
		return aprovacaoSistemas;
	}

	public void setAprovacaoSistemas(List<AprovacaoSistema> aprovacaoSistemas) {
		this.aprovacaoSistemas = aprovacaoSistemas;
	}

	public long getIdInspecao() {
		return this.idInspecao;
	}

	public void setIdInspecao(long idInspecao) {
		this.idInspecao = idInspecao;
	}

	public Date getDtAgendada() {
		return dtAgendada;
	}

	public void setDtAgendada(Date dtAgendada) {
		this.dtAgendada = dtAgendada;
	}

	public Date getDtLimite() {
		return dtLimite;
	}

	public void setDtLimite(Date dtLimite) {
		this.dtLimite = dtLimite;
	}

	public Date getDtSolicitacaoInspecao() {
		return this.dtSolicitacaoInspecao;
	}

	public void setDtSolicitacaoInspecao(Date dtSolicitacaoInspecao) {
		this.dtSolicitacaoInspecao = dtSolicitacaoInspecao;
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

	public String getTelefoneCorretor() {
		return this.telefoneCorretor;
	}

	public void setTelefoneCorretor(String telefoneCorretor) {
		this.telefoneCorretor = telefoneCorretor;
	}

	public BigDecimal getValorTotalRisco() {
		return this.valorTotalRisco;
	}

	public void setValorTotalRisco(BigDecimal valorTotalRisco) {
		this.valorTotalRisco = valorTotalRisco;
	}

	public List<Agendamento> getAgendamentos() {
		if (this.agendamentos == null) {
			this.agendamentos = new ArrayList<Agendamento>();
		}
		return this.agendamentos;
	}

	public void setAgendamentos(List<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}

	/*
	 * public Agendamento addAgendamento(Agendamento agendamento) {
	 * getAgendamentos().add(agendamento); agendamento.setInspecao(this);
	 * 
	 * return agendamento; }
	 * 
	 * public Agendamento removeAgendamento(Agendamento agendamento) {
	 * getAgendamentos().remove(agendamento); agendamento.setInspecao(null);
	 * 
	 * return agendamento; }
	 */

	public CustoInspecao getCustoInspecao() {
		return this.custoInspecao;
	}

	public void setCustoInspecao(CustoInspecao custoInspecao) {
		this.custoInspecao = custoInspecao;
	}

	public List<Designacao> getDesignacoes() {
		return this.designacoes;
	}

	public void setDesignacoes(List<Designacao> designacoes) {
		this.designacoes = designacoes;
	}

	public Honorario getHonorario() {
		return this.honorario;
	}

	public void setHonorario(Honorario honorario) {
		this.honorario = honorario;
	}

	public List<InsercaoSistema> getInsercaoSistemas() {
		return this.insercaoSistemas;
	}

	public void setInsercaoSistemas(List<InsercaoSistema> insercaoSistemas) {
		this.insercaoSistemas = insercaoSistemas;
	}

	/*
	 * public InsercaoSistema addInsercaoSistema(InsercaoSistema
	 * insercaoSistema) { getInsercaoSistemas().add(insercaoSistema);
	 * insercaoSistema.setInspecao(this);
	 * 
	 * return insercaoSistema; }
	 * 
	 * public InsercaoSistema removeInsercaoSistema(InsercaoSistema
	 * insercaoSistema) { getInsercaoSistemas().remove(insercaoSistema);
	 * insercaoSistema.setInspecao(null);
	 * 
	 * return insercaoSistema; }
	 */

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

	public String getCoberturas() {
		return this.coberturas;
	}

	public void setCoberturas(String coberturas) {
		this.coberturas = coberturas;
	}

	public List<Relatorio> getRelatorios() {
		return this.relatorios;
	}

	public void setRelatorios(List<Relatorio> relatorios) {
		this.relatorios = relatorios;
	}

	/*
	 * public Relatorio addRelatorio(Relatorio relatorio) {
	 * getRelatorios().add(relatorio); relatorio.setInspecao(this);
	 * 
	 * return relatorio; }
	 * 
	 * public Relatorio removeRelatorio(Relatorio relatorio) {
	 * getRelatorios().remove(relatorio); relatorio.setInspecao(null);
	 * 
	 * return relatorio; }
	 */

	public List<Revisao> getRevisaos() {
		return this.revisaos;
	}

	public void setRevisaos(List<Revisao> revisaos) {
		this.revisaos = revisaos;
	}

	/*
	 * public Revisao addRevisao(Revisao revisao) { getRevisaos().add(revisao);
	 * revisao.setInspecao(this);
	 * 
	 * return revisao; }
	 * 
	 * public Revisao removeRevisao(Revisao revisao) {
	 * getRevisaos().remove(revisao); revisao.setInspecao(null);
	 * 
	 * return revisao; }
	 */

	public Vistoria getVistoria() {

		return this.vistoria;
	}

	public void setVistoria(Vistoria vistoria) {
		this.vistoria = vistoria;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
/*
		StringBuilder sb = new StringBuilder();
		sb.append("Criada ou Alterada a Inspeção:").append("idInspecao: ").append(this.getIdInspecao())
				.append("Cliente: ")
				.append(this.getCliente().getDescCliente() == null ? "" : this.getCliente().getDescCliente())
				.append("|").append("custoInspecao: ")
				.append(this.getCustoInspecao() == null ? "" : this.getCustoInspecao().toString()).append("|")
				.append("dtAgendada: ").append(this.getDtAgendada() == null ? "" : this.getDtAgendada().toString())
				.append("|").append("dtLimite: ")
				.append(this.getDtLimite() == null ? "" : this.getDtLimite().toString()).append("|")
				.append("dtSolicitacaoInspecao: ")
				.append(this.getDtSolicitacaoInspecao() == null ? "" : this.getDtSolicitacaoInspecao().toString())
				.append("|").append("dtVistoria: ")
				.append(this.getDtVistoria() == null ? "" : this.getDtVistoria().toString()).append("|")
				.append("endereco: ").append(this.getEndereco() == null ? "" : this.getEndereco().toString())
				.append("|").append("fase: ")
				.append(this.getFase().getDescFase() == null ? "" : this.getFase().getDescFase()).append("|")
				.append("funcionarioVistoriador: ")
				.append(this.getFuncionarioVistoriador() == null ? "" : this.getFuncionarioVistoriador().toString())
				.append("|").append("honorario: ")
				.append(this.getHonorario() == null ? "" : this.getHonorario().toString()).append("|")
				.append("nomeCorretor: ").append(this.getNomeCorretor() == null ? "" : this.getNomeCorretor())
				.append("|").append("numInspecaoCliente: ").append(this.getNumInspecaoCliente()).append("|")
				.append("numPropostaCliente: ").append(this.getNumPropostaCliente()).append("|")
				.append("observacao: ").append(this.getObservacao() == null ? "" : this.getObservacao()).append("|")
				.append("qtdBlocos: ").append(this.getQtdBlocos()).append("|").append("ramo: ")
				.append(this.getRamo().getDescRamo() == null ? "" : this.getRamo().getDescRamo()).append("|")
				.append("roubo: ").append(this.getRoubo()).append("|").append("segurado ")
				.append(this.getSegurado() == null ? "" : this.getSegurado().toString()).append("|")
				.append("status: ")
				.append(this.getStatus().getDescStatus() == null ? "" : this.getStatus().getDescStatus()).append("|")
				.append("telefoneCorretor: ")
				.append(this.getTelefoneCorretor() == null ? "" : this.getTelefoneCorretor()).append("|")
				.append("tipoInspecao: ")
				.append(this.getTipoInspecao().getDescTipoInspecao() == null ? ""
						: this.getTipoInspecao().getDescTipoInspecao())
				.append("|").append("valorTotalRisco: ")
				.append(this.getValorTotalRisco() == null ? "" : this.getValorTotalRisco()).append("|")
				.append("coberturas: ")
				.append(this.getCoberturas() == null ? "" : this.getCoberturas()).append("|")
				.append("vistoria: ").append(this.getVistoria() == null ? "" : this.getVistoria().toString())
				.append("|").append("revisoes: ").append("|");
		if (this.getRevisaos() != null) {
			for (Revisao rev : this.getRevisaos()) {
				sb.append(rev.toString()).append("|");
			}
		}
		sb.append("relatorios: ").append("|");
		if (this.getRelatorios() != null) {
			for (Relatorio rel : this.getRelatorios()) {
				sb.append(rel.toString()).append("|");
			}
		}
		sb.append("atividadesInformadas: ").append("|");
		if (this.getInspecaoAtividadeInformada() != null) {
			for (Atividade atv : this.getInspecaoAtividadeInformada()) {
				sb.append(atv.getDescAtividade()).append("|");
			}
		}
		sb.append("atividadesApuradas: ").append("|");
		if (this.getInspecaoAtividadeApurada() != null) {
			for (Atividade atv : this.getInspecaoAtividadeApurada()) {
				sb.append(atv.getDescAtividade()).append("|");
			}
		}
		sb.append("insercoesSistema: ").append("|");
		if (this.getInsercaoSistemas() != null) {
			for (InsercaoSistema insercao : this.getInsercaoSistemas()) {
				sb.append(insercao.toString()).append("|");
			}
		}
		sb.append("designacoes: ").append("|");
		if (this.getDesignacoes() != null)
			for (Designacao des : this.getDesignacoes()) {
				sb.append(des.toString()).append("|");
			}
		
		sb.append("aprovacoesSistema: ").append("|");
		if (this.getAprovacaoSistemas() != null) {
			for (AprovacaoSistema aprv : this.getAprovacaoSistemas()) {
				sb.append(aprv.toString()).append("|");
			}
		}
		sb.append("agendamentos: ").append("|");
		if (this.getAgendamentos() != null) {
			for (Agendamento agd : this.getAgendamentos()) {
				sb.append(agd.toString());
			}
		}

		return sb.toString();
		*/
	}
}