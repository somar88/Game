package com.abrahms.game.applicarion;

import java.awt.Dimension;
import javax.swing.JFrame;

import com.abrahms.game.model.Game;

public class App {

	public static String	title	= "Game Name";
	public static JFrame	frame;

	public static void main(String[] args) {
		Game game = new Game();
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle(title);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		game.start();
	}

}
