package com.company.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "films")
@Table(name = "films")
public class Films {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column
	int wolne_miejsca;
	
	@Column
	private String nazwa_filmu;

	public int getId() {
		return id;
	}

	public String getNazwa_filmu() {
		return nazwa_filmu;
	}

	public void setNazwa_filmu(String nazwa_filmu) {
		this.nazwa_filmu = nazwa_filmu;
	}

	public int getWolne_miejsca() {
		return wolne_miejsca;
	}

	public void setWolne_miejsca(int wolne_miejsca) {
		this.wolne_miejsca = wolne_miejsca;
	}


}
