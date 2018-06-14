package netty.liguang.communicate;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

@Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

	public static  ChannelGroup group=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	public static ChannelHandlerContext context=null;
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// TODO Auto-generated method stub
		String receive=(String)msg;
		
		System.out.println("server received message:"+receive);
		group.writeAndFlush(ctx.channel()+" sent a message:"+receive);
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("channel registered!");
		super.channelRegistered(ctx);
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("channel unregistered!");
		super.channelUnregistered(ctx);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		this.context=ctx;
		System.out.println("channel active!");
		super.channelActive(ctx);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("channel inactive!");
		super.channelInactive(ctx);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		//super.exceptionCaught(ctx, cause);
		System.out.println(cause);
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("channel handler added!");
		Channel incoming=ctx.channel();
		for(Channel channel:group) {
//			ByteBuf buf=channel.alloc().buffer(100);
//			buf.writeBytes("attention:new member add:"+channel);
			channel.writeAndFlush("attention:new member add:"+channel);
		}
		group.add(incoming);
		//super.handlerAdded(ctx);
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("channel handler removed!");
		Channel incoming=ctx.channel();
		for(Channel channel:group) {
//			ByteBuf buf=channel.alloc().buffer(100);
//			buf.writeBytes(("attention:a member left:"+channel).getBytes());
			channel.writeAndFlush("attention:a member left:"+channel);
		}
		group.remove(incoming);
		//super.handlerRemoved(ctx);
	}
	
	


}
