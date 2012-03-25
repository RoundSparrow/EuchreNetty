package com.cynical.euchre.netty.commands.model;

import java.lang.reflect.Type;

import com.cynical.euchre.netty.commands.EuchreCommand;
import com.cynical.euchre.netty.commands.HasCommand;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class CommandPayloadDecodedModel implements HasCommand {
	
	private Gson gson;
	
	private EuchreCommand command;
	private String payload;
	
	public CommandPayloadDecodedModel(EuchreCommand command, String payload) {
		this.command = command;
		this.payload = payload;
		gson = new Gson();
	}

	public EuchreCommand getCommand() {
		return command;
	}
	
	public <T> T getPayload(Class<T> classOfT) {
		try {
			return gson.fromJson(payload, classOfT);
		} catch (JsonSyntaxException e) {
			return null;
		}
	}
	
	public <T> T getPayload(Type typeOfT) {
		return gson.fromJson(payload, typeOfT);
	}

}
