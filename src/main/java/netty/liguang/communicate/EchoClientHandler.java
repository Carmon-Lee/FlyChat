package netty.liguang.communicate;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("client received message:"+msg.getCharSequence(0, msg.readableBytes(), CharsetUtil.UTF_8));
	}

	public static void main(String[] args) {
		System.out.println("\n".getBytes()[0]);
	}

}
