package com.cynical.euchre.netty.commands.server.login;

import com.cynical.euchre.netty.commands.server.EuchreCommandModel;

public class LoginCommandModel extends EuchreCommandModel {
	
	String username;
	
	public LoginCommandModel() {
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toLoggingString() {
		return "Username: " + getUsername();
	}

}
