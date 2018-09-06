package com.campusnumerique.vehiclerental.bean;

public class ClientBean {

	private String login;
	private String role;


	public ClientBean(){
		setLogin("NoUserLogin");
		setRole("IS_GUEST");
	}

	public ClientBean(String aLogin, String aRole){
		setLogin(aLogin);
		setRole(aRole);
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
