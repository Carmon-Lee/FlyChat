package netty.liguang.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import netty.liguang.communicate.EchoServer;
import netty.liguang.communicate.NettyProperties;

@Configuration
@ComponentScan(basePackages= {"netty"},excludeFilters= {
		@Filter(type=FilterType.ANNOTATION,value=EnableWebMvc.class)
		})
@PropertySource(value= {"classpath:config.properties"})
@EnableAsync
public class RootConfig {

	static {
		Thread thread=new Thread(()-> {
			EchoServer server=new EchoServer(NettyProperties.getInt("port"));
			try {
				server.start();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		thread.start();
	}
}


