package br.com.mvengenharia.business.entities;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@NamedQuery(name="Status.findAll", query="SELECT s FROM Status s")
public class Status implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idStatus;

	private String descStatus;

	public Status() {
	}

	public long getIdStatus() {
		return this.idStatus;
	}

	public void setIdStatus(long idStatus) {
		this.idStatus = idStatus;
	}

	public String getDescStatus() {
		return this.descStatus;
	}

	public void setDescStatus(String descStatus) {
		this.descStatus = descStatus;
	}

}