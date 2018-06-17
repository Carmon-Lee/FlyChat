package netty.liguang.communicate;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.CharsetUtil;

public class EchoClientHandler extends SimpleChannelInboundHandler<String> {

	public static AttributeKey<String> id_key=AttributeKey.newInstance("username1");
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		// TODO Auto-generated method stub
		//Attribute<String> username=ctx.channel().attr(id_key);
		System.out.println(msg);
		//ctx.writeAndFlush(username+" sent a message:"+msg);
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelRegistered(ctx);
		Attribute<String> attribute=ctx.channel().attr(id_key);
		String username=attribute.get();
		if (username==null) {
			attribute.setIfAbsent("liguang");
		}
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelActive(ctx);
		ctx.writeAndFlush(ctx.channel().attr(id_key).get());
	}

	public static void main(String[] args) {
		System.out.println("\n".getBytes()[0]);
	}

}
