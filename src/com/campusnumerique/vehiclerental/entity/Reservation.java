package com.campusnumerique.vehiclerental.entity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import com.campusnumerique.vehiclerental.dao.CarDAO;
import com.campusnumerique.vehiclerental.dao.ReservationDAO;

public class Reservation {

	private int id;
	private Car car;
	private Client client;
	private Date startDate;
	private Date endDate;
	private int price;

	public Reservation() {
	}

	public Reservation(int id, Car car, Client client, Date startDate, Date endDate) {
		setId(id);
		setCar(car);
		setClient(client);
		setStartDate(startDate);
		setEndDate(endDate);
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
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

	public float totalPrice(int dist) {
		float totalPrice = car.getReservation() + (car.getKmRate() * dist);
		return totalPrice;
	}

	public boolean fidelity() {
		ReservationDAO reservationDAO = new ReservationDAO();
		ArrayList<Reservation> reservationList;

		try {
			reservationList = reservationDAO.findAllById(this.client.getId());			
			float allInvoice = 0;
			for (Reservation reservation : reservationList) {

				allInvoice = allInvoice + reservation.price;

			}

			if (reservationList.size() >= 3 || allInvoice >= 1000) {

				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

}
