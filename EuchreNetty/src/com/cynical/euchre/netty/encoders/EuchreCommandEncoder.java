package com.cynical.euchre.netty.encoders;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;
import org.jboss.netty.util.CharsetUtil;

import com.cynical.euchre.netty.commands.EuchreCommand;
import com.cynical.euchre.netty.commands.model.CommandPayloadEncoderModel;
import com.google.gson.Gson;

public class EuchreCommandEncoder extends OneToOneEncoder {
	
	//private Logger logger = Logger.getLogger(getClass()); 
	
	/**
	 * 						
	 *      (int)                  (byte[])                    (int)                         (byte[])
	 * --------------------------------------------------------------------------------------------------------------
	 * | commandLength |          commandBytes       |      payloadLength    |              payloadBytes            |
	 * --------------------------------------------------------------------------------------------------------------
	 * |               |                             |                       |                                      |
	 * 0               4                 (commandBytes.length + 4)        (N + 4)                        (P + payloadBytes.length)
	 * bytes                                        (N)                     (P)
	 */
	
	int commandLength = 0;
	byte[] commandBytes;
	int payloadLength = 0;
	byte[] payloadBytes;
	

	@Override
	protected Object encode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
		if(msg instanceof CommandPayloadEncoderModel) {
			
			Gson gson = new Gson();
			CommandPayloadEncoderModel c = (CommandPayloadEncoderModel) msg;
			EuchreCommand command = c.getCommand();
			
			//	Set Command Length and Byte Array
			String commandString = gson.toJson(command);
			commandBytes = commandString.getBytes(CharsetUtil.UTF_8);
			commandLength = commandBytes.length;
			
			//	Set Payload Length and Byte Array
			String payload = gson.toJson(c.getPayload());
			payloadBytes = payload.getBytes(CharsetUtil.UTF_8);
			payloadLength = payloadBytes.length;
			
			int size = 4 + commandLength + 4 + payloadLength; // (int) + (byte[]) + (int) + (byte[])
			
			ChannelBuffer buffer = ChannelBuffers.buffer(size);
			buffer.writeInt(commandLength);
			buffer.writeBytes(commandBytes);
			buffer.writeInt(payloadLength);
			buffer.writeBytes(payloadBytes);
			
			try {
				return buffer;
			} finally {
				reset();
			}
		}

		return msg;
	}
	
	private void reset() {
		commandLength = 0;
		commandBytes = null;
		payloadLength = 0;
		payloadBytes = null;
	}

}
