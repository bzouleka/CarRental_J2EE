package com.campusnumerique.vehiclerental.entity;

import org.json.JSONObject;

public class Car {

	private String id="";
	private String brand="";
	private String model="";
	private String plateNumber="";
	
	public Car(String id, String brand, String model, String plateNumber){
		setId(id);
		setBrand(brand);
		setModel(model);
		setPlateNumber(plateNumber);
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
	
	public JSONObject getInfos(){
		JSONObject infos= new JSONObject();
		infos.put("Marque", brand);
		infos.put("Model", model);
		infos.put("Immatriculation", plateNumber);
		
		return infos;
	}
}