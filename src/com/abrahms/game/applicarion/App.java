package com.abrahms.game.applicarion;

import com.abrahms.game.model.Game;
import com.abrahms.game.view.View;

public class App {

	public static void main(String[] args) {
		View view = new View("Ex Life");
		Game game = new Game(view);
		view.add(game);
		game.start();
		
	}

}
