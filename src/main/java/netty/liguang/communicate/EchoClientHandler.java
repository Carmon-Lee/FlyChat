package netty.liguang.communicate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.CharsetUtil;

public class EchoClientHandler extends SimpleChannelInboundHandler<String> {

	public static AttributeKey<String> id_key=AttributeKey.newInstance("username");
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		System.out.println(msg);
		int onlineUserCount=Integer.parseInt(msg.split("\n")[0]);
		if (onlineUserCount>0) {
			EchoClient.onlineCount=onlineUserCount;
		}
		System.out.println("onlineUserCount:"+onlineUserCount);
	}

	/**
	 * user login, and register the username for the current client
	 */
	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		super.channelRegistered(ctx);
		Attribute<String> attribute=ctx.channel().attr(id_key);
		if (attribute.get()==null) {
			attribute.setIfAbsent(login());
		}
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelActive(ctx);
		ctx.writeAndFlush(ctx.channel().attr(id_key).get());
	}
	
	/**
	 * user login; check the username and password until true
	 * @return the correct username
	 */
	private String login() {
		Map<String, String> loginResult=new HashMap<String,String>();
		List<String> userPool=new ArrayList<String>();
		userPool.add("liguang");
		userPool.add("wuyao");
		userPool.add("lipeng");
		userPool.add("lijielong");
		String username;
		String password;
		for(;;) {
			username=inputVar("username");
			password=inputVar("password");
			//if(username.equals("liguang") && password.equals("liguang")) break;
			if(userPool.contains(username)) break;
			else System.out.println("Wrong username and password match, please try again!");
		}
		return username;
	}
	
	/**
	 * get a string from system input, with varName as the prompt name during the input process
	 * @param varName
	 * @return 
	 */
	private String inputVar(String varName) {
		Scanner scanner=new Scanner(System.in);
		System.out.println("Input your "+varName+":");
		String returnVar=scanner.nextLine();
		while (returnVar.equals("")) {
			System.out.println("Input your "+varName+":");
			returnVar=scanner.nextLine();
		}
		return returnVar;
	}

//	public static void main(String[] args) {
//		System.out.println("\n".getBytes()[0]);
//		EchoClientHandler clientHandler=new EchoClientHandler();
//		clientHandler.login();
//	}

}
