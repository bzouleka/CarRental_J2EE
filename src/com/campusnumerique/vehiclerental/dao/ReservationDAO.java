package com.campusnumerique.vehiclerental.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.campusnumerique.vehiclerental.entity.Reservation;

public class ReservationDAO extends DAO<Reservation>{

	@Override
	public boolean create(Reservation obj) {
		// TODO Auto-generated method stub
		return false;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Reservation findByCarId(int carId) throws SQLException {
		Reservation reservation = new Reservation();
		ResultSet result = this.connection.createStatement(
		ResultSet.TYPE_SCROLL_INSENSITIVE,
		ResultSet.CONCUR_READ_ONLY
		).executeQuery("SELECT - FROM car where car_id = " + carId);
		if (result.first())
			
			reservation.setId(result.getInt("car_id"));
			
		
		return null;
	}


}