package ch.trackdata.demo;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SBS1Parser extends ChannelHandlerAdapter {
	private static final Logger LOGGER = LoggerFactory.getLogger(SBS1Parser.class);
	
	@Produce(uri="direct:sbs1")
	private ProducerTemplate sender;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		// TODO Auto-generated method stub
		LOGGER.info("Received: {}", msg);
		try {
			sender.sendBody(msg);
		}
		catch (Exception ex) {
			LOGGER.warn("Send message failed.", ex);
		}
		
		super.channelRead(ctx, msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		LOGGER.warn("Exception!!!!", cause);
		ctx.close();
	}
}
