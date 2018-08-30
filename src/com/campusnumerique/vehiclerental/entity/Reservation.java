package com.campusnumerique.vehiclerental.entity;

import java.util.Date;

public class Reservation {
	
	private int id;
	private Car car;
	private Client client;
	private Date startDate;
	private Date endDate;
	
	public Reservation(){
	}
	
	public Reservation(int id, Car car, Client client, Date startDate, Date endDate){
		setId(id);
		setCar(car);
		setClient(client);
		setStartDate(startDate);
		setEndDate(endDate);
	}
	
	public Car getCar() {
		return car;
	}
	
	public void setCar(Car car) {
		this.car = car;
	}
	
	public Client getClient() {
		return client;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
