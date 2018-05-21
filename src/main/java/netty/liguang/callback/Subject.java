package netty.liguang.callback;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Subject {

	List<ListenerInt> listeners;
	
	public Subject() {
		listeners=new ArrayList<ListenerInt>();
	}

	public void addListener(ListenerInt listener) {
		this.listeners.add(listener);
	}
	
	public void changeSub(String msg) {
		for (ListenerInt myListener : listeners) {
			myListener.listen(msg);
		}
	}
	
	public static void main(String[] args) {
		ListenerInt listener1=new MyListener();
		ListenerInt listener2=new MyListener();
		ListenerInt listener3=new MyDineListener();
		Subject subject=new Subject();
		subject.addListener(listener1);
		subject.addListener(listener2);
		subject.addListener(listener3);
		ListenerInt listenerInt4=new MyRunListener();
		subject.addListener(listenerInt4);
		subject.changeSub("hello everyone");
	}
}
