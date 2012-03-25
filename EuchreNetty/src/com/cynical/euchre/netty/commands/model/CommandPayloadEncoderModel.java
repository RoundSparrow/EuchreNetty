package com.cynical.euchre.netty.commands.model;

import com.cynical.euchre.netty.commands.EuchreCommand;
import com.cynical.euchre.netty.commands.HasCommand;

public class CommandPayloadEncoderModel implements HasCommand {
	
	private EuchreCommand command;
	private Object payload;
	
	public CommandPayloadEncoderModel(EuchreCommand command, Object payload) {
		this.command = command;
		this.payload = payload;
	}

	public EuchreCommand getCommand() {
		return command;
	}
	
	public Object getPayload() {
		return payload;
	}
	
	public void setPayload(Object o) {
		this.payload = o;
	}

}
