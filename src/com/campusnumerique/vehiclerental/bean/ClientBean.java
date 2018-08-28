package com.campusnumerique.vehiclerental.bean;

public class ClientBean {

	private String login;


	public ClientBean(){
		setLogin("NoUserLogin");
	}

	public ClientBean(String aLogin){
		setLogin(aLogin);
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	
}
