package com.campusnumerique.vehiclerental.servlet.caravailable;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.campusnumerique.vehiclerental.dao.CarDAO;
import com.campusnumerique.vehiclerental.dao.ReservationDAO;
import com.campusnumerique.vehiclerental.entity.Car;
import com.campusnumerique.vehiclerental.entity.Reservation;


/**
 * Servlet implementation class CarAvailable
 */
@WebServlet("/carAvailable")
public class CarAvailableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private CarDAO carDAO = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarAvailableServlet() {
        super();
        carDAO = new CarDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Reservation reservation = (Reservation) request.getAttribute("reservation");
		int distance = (int) request.getAttribute("distance");
		
		List<Car> allCars = null;		
		List<Car> cars = new ArrayList<Car>();
		
		Date startDate = reservation.getStartDate();
		Date endDate = reservation.getEndDate();
		
		try {
			allCars = carDAO.findAll();
			for(Car car : allCars){
				if(car.isAvailable(startDate, endDate) && car.byAge(reservation.getClient())){
					cars.add(car);
				}
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("reservation", reservation);
			session.setAttribute("distance", distance);
			
			request.setAttribute("cars", cars);
			request.getRequestDispatcher("pages/carAvailable.jsp").forward(request, response);
			response.setStatus(HttpServletResponse.SC_OK);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		int distance = (int) session.getAttribute("distance");
		Reservation reservation = (Reservation) session.getAttribute("reservtion");
		
		String idSelectCarString = request.getParameter("selection");
		int idSelectCar = Integer.parseInt(idSelectCarString);
		
		CarDAO carDAO = new CarDAO();
		try {
			Car seletedCar = carDAO.find(idSelectCar);
			reservation.setCar(seletedCar);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ReservationDAO reservationDAO = new ReservationDAO();
		reservationDAO.create(reservation);
		
		float totalPrice = reservation.totalPrice(distance);
			
		request.setAttribute("reservation", reservation);
		request.setAttribute("totalPrice", totalPrice);
		RequestDispatcher req = request.getRequestDispatcher("/recap");
		req.forward(request, response);
	}

}
