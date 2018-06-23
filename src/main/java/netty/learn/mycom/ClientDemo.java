package netty.learn.mycom;

import io.netty.util.AttributeKey;
import io.netty.util.AttributeMap;
import io.netty.util.DefaultAttributeMap;

public class ClientDemo {
	public static void main(String[] args) {

		AttributeKey<String> key=AttributeKey.newInstance("key1");
		System.out.println(key);
		AttributeMap map=new DefaultAttributeMap();
		System.out.println(map.attr(key).get());
			
	}
}
