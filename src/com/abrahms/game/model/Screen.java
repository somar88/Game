package com.abrahms.game.model;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

public class Screen {
	private BufferedImage	img;
	private int[]			pixels;
	private int				screen_width;
	private int				screen_height;
	private Random			random;

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
			pixels[i] = 0x000000; // BBGGRR
		}
	}

	public void tickScreen(int x, int y) {
		if (x >= 0 & x < screen_width & y >= 0 & y < screen_height) {
			pixels[x + screen_width * y] = new Random().nextInt(0x1000000);
			;
		}

	}

	// here we will render every other thing and send it back to the game rendering method
	public void renderScreen(int[] rendering_Pixels) {
		for (int i = 0; i < rendering_Pixels.length; i++) {
			rendering_Pixels[i] = pixels[i];
		}
		resetArray(pixels);
	}

	public void resetArray(int[] arrayToReset) {
		for (int i = 0; i < arrayToReset.length; i++) {
			arrayToReset[i] = 0xFFFFFF;
		}
	}
	
	// rendering random point on the screen 
	public void screenRandomTick(int x, int y, int width, int height) {

		if (x == width || x == 0) x = random.nextInt(width);
		if (y == height || y == 0) y = random.nextInt(height);
		if (x == width || x == 0) x = width;
		if (y == height || y == 0) y = height;
		int orientation = random.nextInt(4);
		switch (orientation) {
			case 0:
				tickScreen(x++, y++);
			break;

			case 1:
				tickScreen(x--, y++);
			break;

			case 2:
				tickScreen(x++, y--);
			break;
			case 3:
				tickScreen(x--, y--);
			break;

			default:
			break;
		}
	}
}
