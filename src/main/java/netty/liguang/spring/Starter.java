package netty.liguang.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import netty.liguang.config.RootConfig;

public class Starter {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("spring.xml");
		System.out.println(context.getBean("user"));
		AnnotationConfigApplicationContext context2=new AnnotationConfigApplicationContext();
		context2.register(RootConfig.class);
		context2.refresh();
		System.out.println(context2.getBean("user"));
	}
}
