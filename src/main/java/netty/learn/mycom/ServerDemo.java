package netty.learn.mycom;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ServerDemo {
	private int port;
	
	public ServerDemo(int port) {
		super();
		this.port = port;
	}
	public void start() throws Exception{
		ServerBootstrap bootstrap=new ServerBootstrap();
		EventLoopGroup group=new NioEventLoopGroup();
		MyServerHandler handler=new MyServerHandler();
		
		bootstrap.group(group)
			.channel(NioServerSocketChannel.class)
			.localAddress(new InetSocketAddress(port))
			.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					// TODO Auto-generated method stub
					ch.pipeline().addLast(handler);
				}
				
			});
		try {
			System.out.println("Waiting for connection...");
			ChannelFuture future=bootstrap.bind().sync();
			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			group.shutdownGracefully().sync();
		}
	}
	public static void main(String[] args) throws Exception{
		ServerDemo serverDemo=new ServerDemo(8081);
		serverDemo.start();
	}
}
