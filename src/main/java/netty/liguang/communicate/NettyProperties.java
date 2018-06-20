package netty.liguang.communicate;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class NettyProperties {

	private static Properties properties=new Properties();
	
	/* static block to initialize the configuration file 
	 * and properties class*/
	static {		
		InputStream in=NettyProperties.class.getClassLoader()
				.getResourceAsStream("config.properties");
		try {
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * util method to help fetch string property
	 * @param prop
	 * @return
	 */
	public static String getString (String prop) {
		return properties.getProperty(prop);
	}
	
	/**
	 * util method to help fetch int property
	 * @param prop
	 * @return
	 */
	public static int getInt(String prop) {
		return Integer.parseInt(properties.getProperty(prop));
	}
	
	public static void main(String[] args) throws Exception {
		
		System.out.println(getString("port"));
	}
}
