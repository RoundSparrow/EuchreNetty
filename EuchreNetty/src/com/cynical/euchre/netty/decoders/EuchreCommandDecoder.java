package com.cynical.euchre.netty.decoders;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.replay.ReplayingDecoder;
import org.jboss.netty.util.CharsetUtil;

import com.cynical.euchre.netty.commands.EuchreCommand;
import com.cynical.euchre.netty.commands.model.CommandPayloadDecodedModel;
import com.google.gson.Gson;

public class EuchreCommandDecoder extends ReplayingDecoder<EuchreCommandDecoder.DecodingState> {
	
	private static Gson gson = new Gson();
	
	enum DecodingState {
		COMMAND_LENGTH,
		COMMAND,
		PAYLOAD_LENGTH,
		PAYLOAD;
	}
	
	int commandLength = 0;
	EuchreCommand command;
	int payloadLength = 0;
	String payload;
	
	public EuchreCommandDecoder() {
		super(DecodingState.COMMAND_LENGTH);
	}

	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel,
			ChannelBuffer buffer, DecodingState state) throws Exception {
		switch(state) {
		case COMMAND_LENGTH:
			commandLength = buffer.readInt();
			checkpoint(DecodingState.COMMAND);
		case COMMAND:
			byte[] commandBytes = new byte[commandLength];
			buffer.readBytes(commandBytes, 0, commandLength);
			String commandJson = new String(commandBytes, CharsetUtil.UTF_8);
			command = gson.fromJson(commandJson, EuchreCommand.class);
			checkpoint(DecodingState.PAYLOAD_LENGTH);
		case PAYLOAD_LENGTH:
			payloadLength = buffer.readInt();
			checkpoint(DecodingState.PAYLOAD);
		case PAYLOAD:
			byte[] payloadBytes = new byte[payloadLength];
			buffer.readBytes(payloadBytes, 0, payloadLength);
			payload = new String(payloadBytes, CharsetUtil.UTF_8);
		}
		
		try {
			return new CommandPayloadDecodedModel(command, payload);
		} finally {
			reset();
		}
	}
	
	private void reset() {
		commandLength = 0;
		command = null;
		payloadLength = 0;
		payload = null;
		checkpoint(DecodingState.COMMAND_LENGTH);
	}

}
