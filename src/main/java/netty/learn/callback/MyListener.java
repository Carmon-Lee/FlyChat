package netty.learn.callback;

public class MyListener implements ListenerInt{

	private static int count=0;
	private int num;
	
	public MyListener() {
		super();
		num=count++;
	}

	public void listen(String msg) {
		System.out.println("listener "+num+" receive msg:"+msg);
	}
}
