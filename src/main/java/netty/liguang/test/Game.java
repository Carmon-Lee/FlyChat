package netty.liguang.test;

import java.util.ArrayList;
import java.util.List;

public class Game implements Cloneable{
	String into;
	int playerNum;
	List<String> heros;
	public Game(String into, int playerNum) {
		super();
		this.into = into;
		this.playerNum = playerNum;
	}

	
	protected Game clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Object object=super.clone();
		Game game=(Game) object;
		game.heros=new ArrayList<String>();
		game.heros.add("jingke");
		
		return game;
	}

	
	
}
