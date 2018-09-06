package com.campusnumerique.vehiclerental.entity;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import com.campusnumerique.vehiclerental.dao.ClientDAO;

public class Client {

	private int id;
	private String login;
	private String password;
	private String firstName;
	private String lastName;
	private String mail;
	private String permisNb;
	private String role;
	private Date birhtDate;
	private Date permisDate;


	public Client() {
		setLogin("guest");
		setRole("IS_GUEST");
	}

	public Client(int id, String login, String firstName, String lastName, String mail) {
		setId(id);
		setLogin(login);
		setFirstName(firstName);
		setLastName(lastName);
		setMail(mail);
		setRole("IS_GUEST");
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public boolean hasBooked(Date startDate, Date finishDate, Reservation reservation) throws SQLException {
		if (reservation == null) {
			return true;
		}
		if (bookingPossible( startDate, finishDate, reservation)) {
			return true;
		}
		return false;
	}

	private boolean bookingPossible( Date startDate, Date finishDate,
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin(){
		
		if(this.role.equals("ROLE_ADMIN")){
			return true;
		}
		
		return false;
			
	}
}