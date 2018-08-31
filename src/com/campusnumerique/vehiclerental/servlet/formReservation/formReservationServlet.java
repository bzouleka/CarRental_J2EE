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
		request.getRequestDispatcher("pages/formReservation.jsp").forward(request, response);
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

		Client client = new Client();
		client.setFirstName(firstName);
		client.setLastName(lastName);
		client.setMail(email);
		client.setPermisNb(permisNb);
		client.setBirhtDate(birthDate);

		if (!client.isAdult()) {
			// Message erreur trop jeune
		}
		
		try {
			if (client.hasBooked(startDate, finishDate)) {
				// Message erreur deja une location
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Reservation reservation = new Reservation();
		reservation.setClient(client);
		reservation.setStartDate(startDate);
		reservation.setEndDate(finishDate);
		
		ClientDAO clientDAO = new ClientDAO();
		clientDAO.create(client);

		doGet(request, response);
	}

}
