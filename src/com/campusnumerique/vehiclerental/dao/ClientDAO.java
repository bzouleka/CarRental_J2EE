package com.campusnumerique.vehiclerental.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import com.campusnumerique.vehiclerental.entity.Client;

public class ClientDAO extends DAO<Client>{

	@Override
	public boolean create(Client obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Client obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Client obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Client find(int id) throws SQLException{
		Client client = new Client();  
		
		ResultSet result = this.connection.createStatement(
		    ResultSet.TYPE_SCROLL_INSENSITIVE, 
		    ResultSet.CONCUR_READ_ONLY
		  ).executeQuery("SELECT * FROM client WHERE id = " + id);
		if(result.first())
			client.setId(result.getInt("id"));
			client.setFirstName(result.getString("firstName"));
			client.setLastName(result.getString("lastName"));
			client.setMail(result.getString("mail"));
			client.setPermisNb(result.getString("permisNb"));
			client.setBirhtDate(result.getDate("birthDate"));
			client.setPermisDate(result.getDate("permisDate"));
		return client;
	}

	@Override
	public List<Client> findAll() throws SQLException{
		ArrayList<Client> clients = new ArrayList<Client>();
		ResultSet result = this.connection.createStatement(
		    ResultSet.TYPE_SCROLL_INSENSITIVE, 
		    ResultSet.CONCUR_READ_ONLY
		  ).executeQuery("SELECT * FROM client");
		while(result.next()){
			Client client = new Client(); 
			client.setId(result.getInt("id"));
			client.setFirstName(result.getString("firstName"));
			client.setLastName(result.getString("lastName"));
			client.setMail(result.getString("mail"));
			client.setPermisNb(result.getString("permisNb"));
			client.setBirhtDate(result.getDate("birthDate"));
			client.setPermisDate(result.getDate("permisDate"));    
			clients.add(client);
		}
		return clients;
	}

}
