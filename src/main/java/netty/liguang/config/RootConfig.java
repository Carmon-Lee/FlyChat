package netty.liguang.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//@Configuration
//@ComponentScan(basePackages= {"netty"},excludeFilters= {
		//@Filter(type=FilterType.ANNOTATION,value=EnableWebMvc.class)
	//	})
public class RootConfig {

	static {
		System.out.println("RootConfig running!");
	}
	@Value("test")
	private String pass;
	@Bean
	public User user() {
		return new User(pass);
	}
}

class User{
	private String name="liguang";

	public User(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [name=" + name + "]";
	}
	
}
