package netty.liguang.config;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class MyAnnotationConfigWebAC extends AnnotationConfigWebApplicationContext {

	static {
		System.out.println("MyAnnotationConfigWebAC started");
	}
}
