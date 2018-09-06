package com.campusnumerique.vehiclerental.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.campusnumerique.vehiclerental.entity.Reservation;

public class ReservationDAO extends DAO<Reservation>{

	@Override
	public boolean create(Reservation obj) {
		
		PreparedStatement ps;
		String sql = "INSERT INTO reservation(car_id,client_id,startDate,endDate,price)VALUES(?,?,?,?,?)";
		
		try{
			
			ps = (PreparedStatement) this.connection.prepareStatement(sql);
			
			java.sql.Date sqlStartDate = new java.sql.Date(obj.getStartDate().getTime());
			java.sql.Date sqlEndDate = new java.sql.Date(obj.getEndDate().getTime());
			
			ps.setInt(1, obj.getCar().getId());
			ps.setInt(2, obj.getClient().getId());
			ps.setDate(3, sqlStartDate);
			ps.setDate(4, sqlEndDate);
			ps.setFloat(5, obj.getPrice());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean delete(Reservation obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Reservation obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Reservation find(int id) throws SQLException {
		
		return null;
	}

	@Override
	public List<Reservation> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Reservation findByCarId(int carId) throws SQLException {
		Reservation reservation = new Reservation();
		CarDAO carDAO = new CarDAO();
		ClientDAO clientDAO = new ClientDAO();
		ResultSet result = this.connection.createStatement(
		ResultSet.TYPE_SCROLL_INSENSITIVE,
		ResultSet.CONCUR_READ_ONLY
		).executeQuery("SELECT * FROM reservation WHERE car_id = " + carId);
		if (result.first()){
			
		Date startDate =  new Date(result.getDate("startDate").getTime());
		Date endDate = new Date(result.getDate("endDate").getTime());
			
			reservation.setId(result.getInt("id"));
			reservation.setStartDate(startDate);
			reservation.setEndDate(endDate);
			reservation.setCar(carDAO.find(result.getInt("car_id")));
			reservation.setClient(clientDAO.find(result.getInt("client_id")));
			reservation.setPrice(result.getInt("price"));
			return reservation;
		}	
		return null;
	}
	
	public Reservation findByClientId(int clientId) throws SQLException{
		Reservation reservation = null;
		CarDAO carDAO = new CarDAO();
		ClientDAO clientDAO = new ClientDAO();
		ResultSet result = this.connection.createStatement(
		ResultSet.TYPE_SCROLL_INSENSITIVE,
		ResultSet.CONCUR_READ_ONLY
		).executeQuery("SELECT * FROM reservation WHERE client_id = \"" + clientId + "\"");		
		if (result.first()){
			
			reservation = new Reservation();
			
			Date startDate =  new Date(result.getDate("startDate").getTime());
			Date endDate = new Date(result.getDate("endDate").getTime());
			
			
			reservation = new Reservation();
			reservation.setId(result.getInt("id"));
			reservation.setStartDate(startDate);
			reservation.setEndDate(endDate);
			reservation.setCar(carDAO.find(result.getInt("car_id")));
			reservation.setClient(clientDAO.find(result.getInt("client_id")));
			reservation.setPrice(result.getInt("price"));
			return reservation;
		}
		return reservation;
	}
	
	public ArrayList<Reservation> findAllById(int id)throws SQLException{
		ArrayList<Reservation> reservationList = new ArrayList<Reservation>();
		CarDAO carDAO = new CarDAO();
		ClientDAO clientDAO = new ClientDAO();
		ResultSet result = this.connection
				.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
				.executeQuery("SELECT * FROM reservation WHERE client_id = \"" + id + "\"");
		while(result.next()){
			
			Date startDate =  new Date(result.getDate("startDate").getTime());
			Date endDate = new Date(result.getDate("endDate").getTime());
			
			
			Reservation reservation = new Reservation();
			reservation.setId(result.getInt("id"));
			reservation.setId(result.getInt("id"));
			reservation.setStartDate(startDate);
			reservation.setEndDate(endDate);
			reservation.setCar(carDAO.find(result.getInt("car_id")));
			reservation.setClient(clientDAO.find(result.getInt("client_id")));
			reservation.setPrice(result.getInt("price"));
			
			reservationList.add(reservation);
			
		}
		return reservationList;
		
	
	}

	
}
