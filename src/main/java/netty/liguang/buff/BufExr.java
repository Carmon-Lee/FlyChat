package netty.liguang.buff;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class BufExr {

	public static void main(String[] args) {
		Channel channel=new NioSocketChannel();
		ByteBuf buf=channel.alloc().buffer();
		byte[] bytes=new byte[10];
		bufInfo(buf);
		buf.writeChar(10);
		bufInfo(buf);
		buf.writeBytes("I have a dream".getBytes());
		bufInfo(buf);
		buf.readBytes(bytes);
		bufInfo(buf);
		ByteBuf buf2=buf.slice();
		bufInfo(buf2);
		byte[] bytes2=buf2.array();
		System.out.println(bytes2.length);
	}
	
	public static void bufInfo(ByteBuf buf) {
		System.out.println("===========================");
		System.out.println("reader index:"+buf.readerIndex());
		System.out.println("writer index"+buf.writerIndex());
		System.out.println("===========================");
	}
}
