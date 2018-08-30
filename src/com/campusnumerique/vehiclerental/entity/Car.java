
package com.campusnumerique.vehiclerental.entity;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

import com.campusnumerique.vehiclerental.dao.CarDAO;
import com.campusnumerique.vehiclerental.dao.ReservationDAO;

public class Car {

	private int id;
	private String brand;
	private String model;
	private String plateNumber;
	private String color;
	private float reservation;
	private float kmRate;
	private int cv;
	private Reservation booked;
	private Client client;

	public Car(int id, String brand, String model, String plateNumber, String color, float reservation, float kmRate,
			int cv) {
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

	public int getCv() {
		return cv;
	}

	public void setCv(int cv) {
		this.cv = cv;
	}

	public float getKmRate() {
		return kmRate;
	}

	public void setKmRate(float kmRate) {

		this.kmRate = kmRate;

	}

	public float getReservation() {
		return reservation;
	}

	public void setReservation(float reservation) {
		this.reservation = reservation;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public JSONObject getInfos() {
		JSONObject infos = new JSONObject();
		infos.put("Marque", brand);
		infos.put("Model", model);
		infos.put("Immatriculation", plateNumber);
		infos.put("Couleur", color);
		infos.put("Réservation", reservation);
		infos.put("Prix kilométrique", kmRate);
		infos.put("Chevaux fiscaux", cv);

		return infos;
	}

	public boolean isAvailable(Date startDate, Date finishDate) throws SQLException {
		ReservationDAO reservationDAO = new ReservationDAO();
		Reservation reservation = reservationDAO.findByCarId(this.id);

		if (reservation == null) {
			return true;
		}
		if (bookingPossible(reservationDAO, startDate, finishDate, reservation)) {
			return true;
		}
		return false;
	}

	private boolean isBetween(Date dateBooking, Date startDate, Date endDate) {

		if (dateBooking.after(startDate) && dateBooking.before(endDate)) {

			return true;
		}
		return false;

	}
	// fonction qui verifie les dates des disponibilité des vehicules

	private boolean bookingPossible(ReservationDAO reservationDAO, Date startDate, Date finishDate,
			Reservation reservation) {
		if (isBetween(startDate, reservation.getStartDate(), reservation.getEndDate())) {
			return false;
		}
		if (isBetween(finishDate, reservation.getStartDate(), reservation.getEndDate())) {
			return false;
		}
		if (isBetween(reservation.getStartDate(), startDate, finishDate)) {
			return false;
		}
		return true;
	}

	// fonction qui permet de calculer l'âge du client par rapport à sa date de
	// naissance.

	private int calculateAge(Date birthDate) {
		LocalDate now = LocalDate.now();
		LocalDate l = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		if ((birthDate != null) && (now != null)) {
			return Period.between(l, now).getYears();
		}
		return 0;

	}

	private boolean byAge(Client client) {
		int age = calculateAge(client.getBirhtDate());
		if (age > 25) {
			return true;
		}
		if (age >= 21 && age <= 25 && this.cv < 13){
			return true;
		}
		if (age < 21 && this.cv < 8){
			return true;
		}
		return false;
	}

}
