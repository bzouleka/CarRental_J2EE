package com.campusnumerique.vehiclerental.servlet.formreservation;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
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
public class FormReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FormReservationServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("pages/formResercation.jsp").forward(request, response);
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
		Date permisDate = null;

		String lastName = request.getParameter("nom");
		String firstName = request.getParameter("prenom");
		String email = request.getParameter("email");
		String permisNb = request.getParameter("permisNb");
		
		
		String distanceString = request.getParameter("distance");
		
		int distance = Integer.parseInt(distanceString);
		
		try {
			birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthDate"));
			startDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("startDate"));
			finishDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("finishDate"));
			permisDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("permisDate"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Client currentClient = new Client();
		currentClient.setFirstName(firstName);
		currentClient.setLastName(lastName);
		currentClient.setMail(email);
		currentClient.setPermisNb(permisNb);
		currentClient.setBirhtDate(birthDate);
		currentClient.setPermisDate(permisDate);
		
		
		if (!currentClient.isAdult()) {
			System.out.print("enfant");
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
			try {
				client = clientDAO.findByPermisNb(client.getPermisNb());
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
		
		reservation = new Reservation();
		reservation.setCar(null);
		reservation.setClient(client);
		reservation.setStartDate(startDate);
		reservation.setEndDate(finishDate);
		
		request.setAttribute("reservation", reservation);
		request.setAttribute("distance", distance);
		RequestDispatcher req = request.getRequestDispatcher("carAvailable.jsp");
		req.forward(request, response);

	}

}
