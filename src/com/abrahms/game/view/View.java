package com.abrahms.game.view;

import java.awt.Dimension;
import javax.swing.JFrame;

public class View extends JFrame {
	public final int			WIDTH				= 1024;
	public final int			HEIGHT				= WIDTH * 9 / 16;
	public String				titel;
	private Dimension			dimension			= new Dimension(WIDTH, HEIGHT);

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	public View(String title) {
		setTitle(title);
		setSize(dimension);
		setMinimumSize(dimension);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setFocusable(true);
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
		requestFocus();
	}

}
