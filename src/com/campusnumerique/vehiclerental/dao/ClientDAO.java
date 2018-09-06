package com.campusnumerique.vehiclerental.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import com.campusnumerique.vehiclerental.entity.Client;

public class ClientDAO extends DAO<Client>{

	@Override
	public boolean create(Client obj) {
		
		PreparedStatement ps;
		String sql = "INSERT INTO client(firstName,lastName,mail,birthDate,permisNb,getPermisDate)VALUES(?,?,?,?,?,?)";
		
		try {
			ps = (PreparedStatement) this.connection
					.prepareStatement(sql);
			
			java.sql.Date sqlBirthDate = new java.sql.Date(obj.getBirhtDate().getTime());
			java.sql.Date sqlPermisDate = new java.sql.Date(obj.getPermisDate().getTime());
			
			ps.setString(1, obj.getFirstName());
			ps.setString(2, obj.getLastName());
			ps.setString(3, obj.getMail());
			ps.setDate(4, sqlBirthDate);
			ps.setString(5, obj.getPermisNb());
			ps.setDate(6, sqlPermisDate);

			ps.executeUpdate();
	
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return true;
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
		Client client = null;  
		
		ResultSet result = this.connection.createStatement(
		    ResultSet.TYPE_SCROLL_INSENSITIVE, 
		    ResultSet.CONCUR_READ_ONLY
		  ).executeQuery("SELECT * FROM client WHERE id = " + id);

		if(result.first()){
			client = new Client();
			Date birthDate = new Date(result.getDate("birthDate").getTime());
			Date permisDate = new Date(result.getDate("getPermisDate").getTime());
			
			client.setId(result.getInt("id"));
			client.setFirstName(result.getString("firstName"));
			client.setLastName(result.getString("lastName"));
			client.setMail(result.getString("mail"));
			client.setPermisNb(result.getString("permisNb"));
			client.setBirhtDate(birthDate);
			client.setPermisDate(permisDate);
			client.setPassword(result.getString("password"));
			client.setRole(result.getString("role"));
		}
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
			
			Date birthDate = new Date(result.getDate("birthDate").getTime());
			Date permisDate = new Date(result.getDate("getPermisDate").getTime());
			
			Client client = new Client(); 
			client.setId(result.getInt("id"));
			client.setFirstName(result.getString("firstName"));
			client.setLastName(result.getString("lastName"));
			client.setMail(result.getString("mail"));
			client.setPermisNb(result.getString("permisNb"));
			client.setBirhtDate(birthDate);
			client.setPermisDate(permisDate);
			client.setPassword(result.getString("password"));
			client.setRole(result.getString("role"));
			
			clients.add(client);
		}
		return clients;
	}

	public Client findByPermisNb(String permisNb) throws SQLException{
		Client client =null;  
		
		ResultSet result = this.connection.createStatement(
		    ResultSet.TYPE_SCROLL_INSENSITIVE, 
		    ResultSet.CONCUR_READ_ONLY
		  ).executeQuery("SELECT * FROM client WHERE permisNb = " + permisNb);
		if(result.first()){
			
			Date birthDate = new Date(result.getDate("birthDate").getTime());
			Date permisDate = new Date(result.getDate("getPermisDate").getTime());
			
			client = new Client();
			client.setId(result.getInt("id"));
			client.setFirstName(result.getString("firstName"));
			client.setLastName(result.getString("lastName"));
			client.setMail(result.getString("mail"));
			client.setPermisNb(result.getString("permisNb"));
			client.setBirhtDate(birthDate);
			client.setPermisDate(permisDate);
			client.setPassword(result.getString("password"));
			client.setRole(result.getString("role"));
		}
		return client;
	}
	
	public Client findByEmail(String email)throws SQLException {
		Client client = null;
		
		ResultSet result = this.connection.createStatement(
			    ResultSet.TYPE_SCROLL_INSENSITIVE, 
			    ResultSet.CONCUR_READ_ONLY
			  ).executeQuery("SELECT * FROM client WHERE mail = \"" + email + "\" ");
			if(result.first()){
				
				Date birthDate = new Date(result.getDate("birthDate").getTime());
				Date permisDate = new Date(result.getDate("getPermisDate").getTime());
				
				client = new Client();
				client.setId(result.getInt("id"));
				client.setFirstName(result.getString("firstName"));
				client.setLastName(result.getString("lastName"));
				client.setMail(result.getString("mail"));
				client.setPermisNb(result.getString("permisNb"));
				client.setBirhtDate(birthDate);
				client.setPermisDate(permisDate);
				client.setPassword(result.getString("password"));
				client.setRole(result.getString("role"));
			}
			return client;
		
	}
	
}
