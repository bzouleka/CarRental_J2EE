package com.campusnumerique.vehiclerental.entity;

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
}