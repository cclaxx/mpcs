package it.polimi.awt.mpcs.domain;

import java.awt.geom.Point2D;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@NamedQueries({
	@NamedQuery(name = "MountainPhoto.findAll", query = "SELECT m FROM MountainPhoto m where m.wrong = false"),
	@NamedQuery(name = "MountainPhoto.findSaved", query = "SELECT m FROM MountainPhoto m where m.wrong = false and m.saved = true"),
	@NamedQuery(name = "MountainPhoto.findBySource", query = "SELECT m FROM MountainPhoto m WHERE m.source = :source"),
	@NamedQuery(name = "MountainPhoto.findById", query = "SELECT m FROM MountainPhoto m WHERE m.id = :id"),
})
@Table(name="mountain")
public class MountainPhoto {
	
	@Id
	@GeneratedValue
	private int id;
	
	@NotBlank(message = "Insert image name")
	private String name;
	
	
	private String description;
	
	@NotBlank(message = "Insert image source")
	private String source;
	
	private double lat;

	private double lng;
	
	private String geoArea;
	
	private Boolean saved=false;
	
	private Boolean wrong=false;
	
	@Transient
	private Point2D geoPoint;
	
	public MountainPhoto(String name , String description, String source, Point2D geopoint, String geoarea)
	{
		this.name = name;
		this.description = description;
		this.source = source;
		this.geoPoint = geopoint;
		this.geoArea = geoarea;
		
	}

	public MountainPhoto() {
		
	}
	public String getGeoArea() {
		return geoArea;
	}
	public void setGeoArea(String geoArea) {
		this.geoArea = geoArea;
	}
	public Point2D getGeoPoint() {
		return geoPoint;
	}
	public void setGeoPoint(Point2D xy) {
		this.geoPoint = xy;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}
	
	public Boolean getSaved() {
		return saved;
	}

	public void setSaved(Boolean saved) {
		this.saved = saved;
	}
	
	public Boolean getWrong() {
		return wrong;
	}

	public void setWrong(Boolean wrong) {
		this.wrong = wrong;
	}
	
	public void finalize(){
		this.lat=geoPoint.getX();
		this.lng=geoPoint.getY();
	}

	
	
}
