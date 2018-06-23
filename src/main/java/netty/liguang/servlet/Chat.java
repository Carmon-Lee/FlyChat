package netty.liguang.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class Chat {

	@RequestMapping("/test")
	public String test() {
		return "test";
	}
}
