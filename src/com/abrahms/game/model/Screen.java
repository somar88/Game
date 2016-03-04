package com.abrahms.game.model;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

public class Screen {
	private BufferedImage	img;
	private int[]			pixels;
	private int				screen_width;
	private int				screen_height;

	public Screen(int width, int height) {
		screen_width = width;
		screen_height = height;
		pixels = new int[width * height];
		initScreen();
	}

	private void initScreen() {
		img = new BufferedImage(screen_width, screen_height, BufferedImage.TYPE_INT_BGR);
		pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] =  0xFFFFFF; // BBGGRR
		}
	}

	// here we will render every other thing and send it back to the game renderin method
	public void renderScreen(int[] rendering_Pixels) {
		

	}
}
