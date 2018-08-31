package com.campusnumerique.vehiclerental.servlet.formReservation;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.campusnumerique.vehiclerental.dao.ClientDAO;
import com.campusnumerique.vehiclerental.dao.ReservationDAO;
import com.campusnumerique.vehiclerental.entity.Client;
import com.campusnumerique.vehiclerental.entity.Reservation;

/**
 * Servlet implementation class formReservation
 */
@WebServlet("/reservation")
public class formReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public formReservationServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("pages/carAvailable.jsp").forward(request, response);
		response.setStatus(HttpServletResponse.SC_OK);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Date birthDate = null;
		Date startDate = null;
		Date finishDate = null;

		String lastName = request.getParameter("nom");
		String firstName = request.getParameter("prenom");
		String email = request.getParameter("email");
		String permisNb = request.getParameter("premisNb");

		try {
			birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("birthDate"));
			startDate = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("startDate"));
			finishDate = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("finishDate"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Client currentClient = new Client();
		currentClient.setFirstName(firstName);
		currentClient.setLastName(lastName);
		currentClient.setMail(email);
		currentClient.setPermisNb(permisNb);
		currentClient.setBirhtDate(birthDate);
		
		
		if (!currentClient.isAdult()) {
			// Message erreur trop jeune
		}
		Client client = null;
		ClientDAO clientDAO = new ClientDAO();
		try {
			client = clientDAO.findByPermisNb(permisNb);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if( client == null){
			client = currentClient;
			clientDAO.create(client);
		}
		
		ReservationDAO reservationDAO = new ReservationDAO();
		Reservation reservation = null;
		try {
			reservation = reservationDAO.findByClientId(client.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if (client.hasBooked(startDate, finishDate, reservation)) {
						// Message erreur deja une location
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		reservation.setCar(null);
		reservation.setClient(client);
		reservation.setStartDate(startDate);
		reservation.setEndDate(finishDate);
		
				

		doGet(request, response);
	}

}
