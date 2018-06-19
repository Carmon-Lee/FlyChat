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
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

@Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

	public static  ChannelGroup group=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	public static ChannelHandlerContext context=null;
	//username attribute, used to store the username after login
	public static AttributeKey<String> id_key=AttributeKey.newInstance("username");
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String username0=ctx.channel().attr(id_key).get();
		if (username0==null || "".equals(username0)) {
			ctx.channel().attr(id_key).setIfAbsent((String)msg);
		}
		String receive=(String)msg;
		Attribute<String> username=ctx.channel().attr(id_key);
		System.out.println(username+" sent a message:"+receive);
		group.writeAndFlush("hello,"+username+" what can I do for you");
	}


	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// store ChannelHandlerContext in the handler context for further usage
		this.context=ctx;
		System.out.println("channel active!");
		super.channelActive(ctx);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println(cause);
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("channel handler added!");
		Channel incoming=ctx.channel();
		for(Channel channel:group) {
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
			channel.writeAndFlush("attention:a member left:"+channel);
		}
		group.remove(incoming);
		//super.handlerRemoved(ctx);
	}

}
