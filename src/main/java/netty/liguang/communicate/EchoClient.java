package netty.liguang.communicate;

import java.net.InetSocketAddress;
import java.util.Scanner;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.AttributeKey;

public class EchoClient {
	private final String host;
	private static final int port=8082;
	
	public EchoClient(String host) {
		super();
		this.host = host;
	}
	
	public void start() throws Exception{
		EventLoopGroup group=new NioEventLoopGroup();
		
		try {
			Bootstrap b = new Bootstrap();
			System.out.println("Client created, establishing connection with Server...");
			b.group(group)
			.channel(NioSocketChannel.class)
			.handler(new ChannelInitializer<SocketChannel>() {
					public void initChannel(SocketChannel ch) throws Exception {
						
						ChannelPipeline pipeline=ch.pipeline();
						pipeline
						.addLast("decoder", new StringDecoder())
						.addLast("encoder", new StringEncoder())
						.addLast(new EchoClientHandler());
					}
				});
			
			Scanner scanner=new Scanner(System.in);
			System.out.println("Ready to sync...");
			Channel channel=b.connect("localhost",port).sync().channel();
					
			while (true) {
				String input0=scanner.nextLine();
				if (input0.equals("end")) {
					break;
				}
				ByteBuf buf=Unpooled.buffer(1000);
				buf.writeBytes(input0.getBytes());
				channel.writeAndFlush(channel.attr(EchoClientHandler.id_key).get()+" sent a message:"+buf);
				//System.out.println(input);
			}
			channel.closeFuture().sync();
			System.out.println("Finishing...");
		} finally {
			// TODO: handle finally clause
			group.shutdownGracefully().sync();
		}
	}
	public static void main(String[] args) throws Exception{
		String host="localhost";
		new EchoClient(host).start();
	}
}
