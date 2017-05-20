package br.com.mvengenharia.business.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.apache.commons.lang3.builder.ToStringBuilder;


@Entity
@NamedQuery(name="Log.findAll", query="SELECT s FROM Log s")
public class Log implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idLog;
	
	@Column(columnDefinition = "MediumText")
	private String descLog;
	
	private String cpfFuncionario;
	
	private long idInspecao;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtLog;
	
	public Log() {
	}

	public Log(String descLog, String cpfFuncionario, long idInspecao, Date dtLog) {
		super();
		this.descLog = descLog;
		this.cpfFuncionario = cpfFuncionario;
		this.idInspecao = idInspecao;
		this.dtLog = dtLog;
	}

	
	public String getCpfFuncionario() {
		return cpfFuncionario;
	}

	public void setCpfFuncionario(String cpfFuncionario) {
		this.cpfFuncionario = cpfFuncionario;
	}

	public long getIdInspecao() {
		return idInspecao;
	}

	public void setIdInspecao(long idInspecao) {
		this.idInspecao = idInspecao;
	}

	public Date getDtLog() {
		return dtLog;
	}

	public void setDtLog(Date dtLog) {
		this.dtLog = dtLog;
	}

	public long getIdLog() {
		return this.idLog;
	}

	public void setIdLog(long idLog) {
		this.idLog = idLog;
	}

	public String getDescLog() {
		return this.descLog;
	}

	public void setDescLog(String descLog) {
		this.descLog = descLog;
	}
	
	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this);

	}

}