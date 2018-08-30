package com.campusnumerique.vehiclerental.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.campusnumerique.vehiclerental.entity.Car;

public class CarDAO extends DAO<Car>{

	@Override
	public boolean create(Car obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Car obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Car obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Car find(int id) throws SQLException {
		Car car = new Car();
		
		ResultSet result = this.connection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY
				).executeQuery("SELECT - FROM car where id = " + id);
		if (result.first())
			
			car.setId(result.getInt("id"));
			car.setBrand(result.getString("brand"));
			car.setModel(result.getString("model"));
			car.setPlateNumber(result.getString("plateNumber"));
			car.setColor(result.getString("color"));
			car.setReservation(result.getFloat("reservation"));
			car.setKmRate(result.getFloat("kmRate"));
			car.setCv(result.getString("cv"));
			
			
		return car;
		
	}

	@Override
	public List<Car> findAll() throws SQLException {
		ArrayList<Car> cars = new ArrayList<Car>();
		ResultSet result = this.connection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM car");
		while(result.next()){
			Car car = new Car();
			car.setBrand(result.getString("brand"));
			car.setModel(result.getString("model"));
			car.setPlateNumber(result.getString("plateNumber"));
			car.setColor(result.getString("color"));
			car.setReservation(result.getFloat("reservation"));
			car.setKmRate(result.getFloat("kmRate"));
			car.setCv(result.getString("cv"));
			
			cars.add(car);
		}
					
		return cars;
	}
	
	

}
