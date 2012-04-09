package com.cynical.euchre.netty.commands;

public enum EuchreCommand {
	
	//////////////////////////////
	//	Pre-Game Commands
	//////////////////////////////
	LOGIN("LOGIN"),
	REQUEST_LOBBY_REFRESH("REQUEST_LOBBY_REFRESH"),
	JOIN_GAME("JOIN_GAME"),
	
	//////////////////////////////
	//	In-Game Commands
	//////////////////////////////
	REQUEST_GAME_STATE("REQUEST_GAME_STATE"),
	ORDER_UP("ORDER_UP"),
	PASS("PASS"),
	DISCARD("DISCARD"),
	PLAY_CARD("PLAY_CARD"),
	
	//////////////////////////////
	//	Error
	//////////////////////////////
	NOT_A_COMMAND("NOT_A_COMMAND");
	
	private String value;
	
	private EuchreCommand(String s) {
		this.value = s;
	}
	
	public String toString() {
		return this.value;
	}

}
