package it.polimi.awt.mpcs.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@NamedQueries({
		@NamedQuery(name = "SeedQuery.findAll", query = "SELECT s FROM SeedQuery s"),
		@NamedQuery(name = "SeedQuery.findByName", query = "SELECT s FROM SeedQuery s WHERE s.name = :name") })
@Table(name = "seedquery")
public class SeedQuery {

	@Id
	@GeneratedValue
	private int id;

	@NotBlank(message = "Insert seed name")
	private String name;

	@NotBlank(message = "Insert seed lat")
	private String lat;

	@NotBlank(message = "Insert seed lng")
	private String lng;

	private int elev;

	private int prom;

	public SeedQuery() {

	}

	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public int getElev() {
		return elev;
	}

	public void setElev(int elev) {
		this.elev = elev;
	}

	public int getProm() {
		return prom;
	}

	public void setProm(int prom) {
		this.prom = prom;
	}

}
