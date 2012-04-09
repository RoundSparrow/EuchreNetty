package com.cynical.euchre.netty.commands.model;

import com.cynical.euchre.netty.commands.EuchreCommand;
import com.cynical.euchre.netty.commands.HasCommand;
import com.cynical.euchre.netty.commands.server.EuchreCommandModel;

public class CommandPayloadEncoderModel implements HasCommand {
	
	private EuchreCommand command;
	private EuchreCommandModel payload;
	
	public CommandPayloadEncoderModel(EuchreCommand command, EuchreCommandModel payload) {
		this.command = command;
		this.payload = payload;
	}

	public EuchreCommand getCommand() {
		return command;
	}
	
	public Object getPayload() {
		return payload;
	}
	
	public void setPayload(EuchreCommandModel o) {
		this.payload = o;
	}

}
