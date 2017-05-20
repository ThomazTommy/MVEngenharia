package br.com.mvengenharia.business.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.apache.commons.lang3.builder.ToStringBuilder;


@Entity
@NamedQuery(name="Feriado.findAll", query="SELECT s FROM Feriado s")
public class Feriado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idFeriado;

	private String descFeriado;
	
	private Date dataFeriado;
	
	private int anoFeriado;

	public Feriado() {
	}

	public long getIdFeriado() {
		return this.idFeriado;
	}

	public void setIdFeriado(long idFeriado) {
		this.idFeriado = idFeriado;
	}

	public String getDescFeriado() {
		return this.descFeriado;
	}

	public void setDescFeriado(String descFeriado) {
		this.descFeriado = descFeriado;
	}
	
	public Date getDataFeriado() {
		return dataFeriado;
	}

	public void setDataFeriado(Date dataFeriado) {
		this.dataFeriado = dataFeriado;
	}

	public int getAnoFeriado() {
		return anoFeriado;
	}

	public void setAnoFeriado(int anoFeriado) {
		this.anoFeriado = anoFeriado;
	}

	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
}