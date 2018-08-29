package com.campusnumerique.vehiclerental.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;


public abstract class DAO<T> {
  protected Connection connection = null;
  private String dbType = "mysql";
  private String url = "localhost:3306";
  private String dbName = "vehiclerental";
  private String user = "root";
  private String password = "Campus74*";
  private String connectionUrl=null;
   
  public DAO(){
	  buildConnectionUrl();

	  try {
	  	  Class.forName("com.mysql.cj.jdbc.Driver");
	  	  System.out.println(connectionUrl);
		  connection =
		       DriverManager.getConnection(connectionUrl);

		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  }
   
  /**
  * Méthode de création
  * @param obj
  * @return boolean 
  */
  public abstract boolean create(T obj);

  /**
  * Méthode pour effacer
  * @param obj
  * @return boolean 
  */
  public abstract boolean delete(T obj);

  /**
  * Méthode de mise à jour
  * @param obj
  * @return boolean
  */
  public abstract boolean update(T obj);

  /**
  * Méthode de recherche des informations
  * @param id
  * @return T
 * @throws SQLException 
  */
  public abstract T find(int id) throws SQLException;

  /**
  * Méthode de recherche des informations
  * @param id
  * @return T
 * @throws SQLException 
  */
  public abstract List<T> findAll() throws SQLException;
  
  private void buildConnectionUrl(){
	  StringBuilder connectionUrl = new StringBuilder("jdbc:");
	  connectionUrl.append(dbType);
	  connectionUrl.append("://");
	  connectionUrl.append(url);
	  connectionUrl.append("/");
	  connectionUrl.append(dbName);
	  connectionUrl.append("?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&");
	  connectionUrl.append("user=");
	  connectionUrl.append(user);
	  connectionUrl.append("&");
	  connectionUrl.append("password=");
	  connectionUrl.append(password);
	  this.connectionUrl=connectionUrl.toString();
  }
}