package com.abrahms.game.model;

import java.awt.image.BufferedImage;

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
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0x000000;
		}
	}

	public void renderScreen(int[] rendering_Pixels) {
		for (int i = 0; i < pixels.length; i++) {
			rendering_Pixels[i] = pixels[i];
		}

	}
}
