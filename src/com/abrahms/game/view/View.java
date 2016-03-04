package com.abrahms.game.view;

import java.awt.Dimension;

import javax.swing.JFrame;

public class View extends JFrame {
	public final int		WIDTH				= 1024;
	public final int		HEIGHT				= WIDTH * 9 / 16;
	public String		titel;

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	public View(String title) {

//		setUndecorated(true);
		setTitle(title);
		setSize(new Dimension(WIDTH, HEIGHT));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setFocusable(true);
		setLocationRelativeTo(null);
		setVisible(true);
		requestFocus();
	}

}
