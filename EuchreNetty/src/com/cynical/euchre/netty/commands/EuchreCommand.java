package com.cynical.euchre.netty.commands;

public enum EuchreCommand {
	
	//////////////////////////////
	//	Pre-Game Commands
	//////////////////////////////
	LOGIN,
	REQUEST_LOBBY_REFRESH,
	JOIN_GAME,
	
	//////////////////////////////
	//	In-Game Commands
	//////////////////////////////
	REQUEST_GAME_STATE,
	ORDER_UP,
	PASS,
	DISCARD,
	PLAY_CARD,
	
	//////////////////////////////
	//	Error
	//////////////////////////////
	NOT_A_COMMAND;

}
