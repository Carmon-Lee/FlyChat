package netty.liguang.communicate;

import java.net.InetSocketAddress;
import java.util.Scanner;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.AttributeKey;

public class EchoServer {

	private final int port;
	
	public EchoServer(int port) {
		super();
		this.port = port;
	}
	
	
	public static void main(String[] args) throws Exception{
		new EchoServer(8082).start();
	}
	
	public void start() throws Exception{
		final EchoServerHandler serverHandler=new EchoServerHandler();
		
		EventLoopGroup bossgroup=new NioEventLoopGroup();
		EventLoopGroup workergroup=new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			//
			System.out.println("Server started, waiting for connection...");
			b.group(bossgroup,workergroup).
			channel(NioServerSocketChannel.class)
			.localAddress(new InetSocketAddress(port))
			.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) {
					ch.pipeline()
					.addLast("decoder", new StringDecoder())
					.addLast("encoder", new StringEncoder())
					.addLast("server",new EchoServerHandler());				
				}
			}); 
			
			ChannelFuture future = b.bind().sync();
			Channel channel=future.channel();
			EchoServerHandler handler=(EchoServerHandler)channel.pipeline().get("server");
			
			
			
			for(int i=0;i<100;i++) {
				
				Scanner scanner=new Scanner(System.in);
				ByteBuf buf=channel.alloc().buffer(100);
				String input0=scanner.nextLine();
				buf.writeBytes(("message from server: "+input0).getBytes());
				handler.context.writeAndFlush(buf);
			}
			future.channel().closeFuture().sync();
		} finally {
			// TODO: handle finally clause
			
		}
	}
}
