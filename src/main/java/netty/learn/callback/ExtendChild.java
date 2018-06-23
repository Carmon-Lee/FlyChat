package netty.learn.callback;

import io.netty.bootstrap.AbstractBootstrap;

public class ExtendChild<U extends ExtendChild<U,V>,V extends Song> {

	private int age;
	private String name;
	@Override
	public String toString() {
		return "ExtendChild [age=" + age + ", name=" + name + "]";
	}
	public static void main(String[] args) {
		Song song=new Song(1);
		D ds=new D();
		ds.d();
		
	}
	
}

class Song{
	private int age;

	public Song(int age) {
		super();
		this.age = age;
	}

	@Override
	public String toString() {
		return "Song [age=" + age + "]";
	}
	
}
class ExtendChild1 extends ExtendChild<ExtendChild1, Song>{
	
}
class D<Song> {
	Song song;
	public void d() {
		System.out.println(song);
	}
}
class E<T> {
	T t;
	public void e() {
		System.out.println(t);
	}
}

class Glass{}
class Cup<B extends Cup<B,C>,C extends Cup<B,C>> extends Glass{}
class WhiteCup extends Cup<WhiteCup,WhiteCup>{
}
class BlackCup extends Cup<BlackCup,BlackCup>{
}