package netty.liguang.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyProxy {
	
	public static void main(String[] args) {
		PollSubject pollSubject=new PollSubject();
		MyInvocationHandler myInvocationHandler=new MyInvocationHandler(pollSubject);
		Subject subject=(Subject)Proxy.newProxyInstance(pollSubject.getClass().getClassLoader(), pollSubject.getClass().getInterfaces(), myInvocationHandler);
		subject.name();
	}	
}

class MyInvocationHandler implements InvocationHandler{

	Object target;
	
	public MyInvocationHandler(Object target) {
		super();
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("before invoke");
		return method.invoke(target, args);
	}
	
}

class PollSubject implements Subject{

	@Override
	public void name() {
		// TODO Auto-generated method stub
		System.out.println("the poll starts at 3pm");
	}
	
}
interface Subject{
	public void name();
}
