package netty.learn.mycom;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class MyServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// TODO Auto-generated method stub
		ByteBuf buf=(ByteBuf) msg;
		System.out.println(buf.toString(CharsetUtil.UTF_8));
		ctx.write(msg);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		ctx.writeAndFlush(ChannelFutureListener.CLOSE);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		ctx.close();
	}

}
