package com.campusnumerique.vehiclerental.entity;

import org.json.JSONObject;

public class Car {

	private String id="";
	private String brand="";
	private String model="";
	private String plateNumber="";
	private String color="";
	private String reservation="";
	private String kmRate="";
	private String cv="";
	
	public Car(String id, String brand, String model, String plateNumber, String color, String reservation, String kmRate, String cv){
		setId(id);
		setBrand(brand);
		setModel(model);
		setPlateNumber(plateNumber);
		setColor(color);
		setReservation(reservation);
		setKmRate(kmRate);
		setCv(cv);
	}
	
	
	public Car() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	
	public String getCv(){
		return cv;
	}
	public void setCv(String cv) {		
		this.cv = cv;
	}

	public String getKmRate(){
		return kmRate;
	}
	public void setKmRate(String kmRate) {
		
		this.kmRate = kmRate;
		
	}

	public String getReservation(){
		return reservation;
	}
	
	public void setReservation(String reservation) {
		this.reservation = reservation;
	}

	public String getColor(){
		return color;
	}
	
	public void setColor(String color) {
		this.color= color;
	}

	
	public JSONObject getInfos(){
		JSONObject infos= new JSONObject();
		infos.put("Marque", brand);
		infos.put("Model", model);
		infos.put("Immatriculation", plateNumber);
		infos.put("Couleur", color);
		infos.put("Réservation", reservation);
		infos.put("Prix kilométrique", kmRate);
		infos.put("Chevaux fiscaux", cv);
		
		return infos;
	}
}