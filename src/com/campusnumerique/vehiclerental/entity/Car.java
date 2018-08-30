package com.campusnumerique.vehiclerental.entity;

import org.json.JSONObject;

public class Car {

	private int id;
	private String brand;
	private String model;
	private String plateNumber;
	private String color;
	private float reservation;
	private float kmRate;
	private String cv;
	
	public Car(int id, String brand, String model, String plateNumber, String color, float reservation, float kmRate, String cv){
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

	public int getId() {
		return id;
	}
	public void setId(int id) {
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

	public float getKmRate(){
		return kmRate;
	}
	public void setKmRate(float kmRate) {
		
		this.kmRate = kmRate;
		
	}

	public float getReservation(){
		return reservation;
	}
	
	public void setReservation(float reservation) {
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