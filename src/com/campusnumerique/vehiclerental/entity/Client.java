package com.campusnumerique.vehiclerental.entity;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import org.json.JSONObject;

import com.campusnumerique.vehiclerental.dao.ReservationDAO;

public class Client {

	private int id;
	private String login;
	private String firstName;
	private String lastName;
	private String mail;
	private String permisNb;
	private Date birhtDate;
	private Date permisDate;
	private boolean isGuest;

	public Client() {
		setLogin("guest");
		setGuest(true);
	}

	public Client(int id, String login, String firstName, String lastName, String mail) {
		setId(id);
		setLogin(login);
		setFirstName(firstName);
		setLastName(lastName);
		setMail(mail);
		setGuest(false);
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		if (login != null && !login.equals(""))
			this.login = login;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public boolean isGuest() {
		return isGuest;
	}

	public void setGuest(boolean isGuest) {
		this.isGuest = isGuest;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public JSONObject getInfos() {
		JSONObject infos = new JSONObject();
		infos.put("login", login);
		infos.put("id", id);
		infos.put("firstName", firstName);
		infos.put("lastName", lastName);
		infos.put("mail", mail);
		infos.put("isGuest", isGuest);
		return infos;
	}

	public String toString() {
		return getInfos().toString();
	}

	public String getPermisNb() {
		return permisNb;
	}

	public void setPermisNb(String permisNb) {
		this.permisNb = permisNb;
	}

	public Date getBirhtDate() {
		return birhtDate;
	}

	public void setBirhtDate(Date birhtDate) {
		this.birhtDate = birhtDate;
	}

	public Date getPermisDate() {
		return permisDate;
	}

	public void setPermisDate(Date permisDate) {
		this.permisDate = permisDate;
	}

	public boolean isAdult() {
		
		if ( this.getAge() >= 18) {
			return true;
		}
		return false;
	}
	
	public int getAge() {
		LocalDate now = LocalDate.now();
		LocalDate l = this.birhtDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return Period.between(l, now).getYears();
	}

	public boolean hasBooked(Date startDate, Date finishDate) throws SQLException {
		ReservationDAO reservationDAO = new ReservationDAO();
		Reservation reservation = reservationDAO.findByClientId(this.id);
		if (reservation == null) {
			return true;
		}
		if (bookingPossible(reservationDAO, startDate, finishDate, reservation)) {
			return true;
		}
		return false;
	}

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

	private boolean isBetween(Date dateBooking, Date startDate, Date endDate) {

		if (dateBooking.after(startDate) && dateBooking.before(endDate)) {

			return true;
		}
		return false;

	}

}