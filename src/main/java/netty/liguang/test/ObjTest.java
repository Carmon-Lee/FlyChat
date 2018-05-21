package netty.liguang.test;

import java.util.ArrayList;
import java.util.List;

public class ObjTest{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testArray();
		separat();
		testOjb();
		
	}

	public static void separat() {
		System.out.println("=================");
	}
	public static void testOjb() {
		ObjTest objTest=new ObjTest();
		List<String> heros=new ArrayList<String>();
		heros.add("yase");
		heros.add("chengyaojin");
		
		Game game=new Game("wangzhe", 10000);
		game.heros=heros;
		
		System.out.println(game.hashCode());
		try {
			Game game2=(Game)game.clone();
			System.out.println(game2.hashCode());
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void testArray() {
		int[] ints= {1,2,3};
		for(int a:ints) {
			System.out.println(a);
		}
	}
	
}
