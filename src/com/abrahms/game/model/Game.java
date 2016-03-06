package com.abrahms.game.model;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import javax.swing.JFrame;

import com.abrahms.game.model.gfx.Sprite;
import com.abrahms.game.view.View;

public class Game extends Canvas implements Runnable {
	/*
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	public static final int		WIDTH				= 1024;
	public static final int		HEIGHT				= WIDTH / 16 * 9;
	public static final int		SCALE				= 3;

	private boolean				running				= false;
	private BufferedImage		img;
	private Graphics			g;
	private BufferStrategy		bs;
	private Random				random				= new Random();
	private Screen				screen;
	private int[]				rendering_Pixels;
	private int					x;
	private int					y;

	// Constructor 
	public Game() {
		Dimension size = new Dimension(WIDTH, HEIGHT);
		setPreferredSize(size);
		this.screen = new Screen(WIDTH, HEIGHT);
		this.img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		rendering_Pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
		for (int i = 0; i < rendering_Pixels.length; i++) {
			rendering_Pixels[i] = 0x000000;
		}
	}

	// starting the game
	public void start() {
		if (running) return;
		running = true;
		new Thread(this, "Ex-Life_Thread").start();
	}

	// Exiting the Application
	public void stop() {
		if (!running) return;
		running = false;
	}

	// Manage 
	public void tick() {
		//		rendomPoint();
	}

	public void render() {
		bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		// rendering Area 51 :P
		////////////////////////////////////////////////////////////////////////////////////////////////
		screen.renderScreen(rendering_Pixels);
		//		Sprite.TEST_SPRITE.s_Render(rendering_Pixels, WIDTH);
		//		rendering_Pixels[20480] = 0xFFFFFF;
		g.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), null);
		//		System.out.println("testing");
		////////////////////////////////////////////////////////////////////////////////////////////////
		g.dispose();
		bs.show();
	}

	public void run() {
		// target is our Target of how many ticks in a second
		double target = 60.0d;
		double nanoSecondPerTick = 1000_000_000.0d / target;
		long startCheck = System.nanoTime();
		// timer will be used for FPS and TPS printing to the console
		long timer = System.currentTimeMillis();
		double unprocessed = 0.0d;
		int fps = 0;
		int tps = 0;
		boolean canrender = false;
		while (running) {
			long now = System.nanoTime();
			unprocessed += (now - startCheck) / nanoSecondPerTick;
			startCheck = now;

			if (unprocessed >= 1.0) {
				tick();
				unprocessed -= 1;
				tps++;
				canrender = true;
			} else canrender = false;
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (canrender) {
				render();
				fps++;
			}
			if (System.currentTimeMillis() - timer >= 1000) {
				timer += 1000;
				System.out.println("fps: " + fps + ", tps: " + tps);
				fps = 0;
				tps = 0;
			}
		}
		System.exit(0);
	}

	private void rendomPoint() {
		// rendering random point on the screen 
		//		if (x == width || x == 0) x = random.nextInt(width);
		//		if (y == height || y == 0) y = random.nextInt(height);
		//		if (x == this.width || x == 0) x = view.WIDTH / 2;
		//		if (y == this.height || y == 0) y = view.HEIGHT / 2;
		if (x >= WIDTH || x <= 0) x = 0;
		if (y >= HEIGHT || y <= 0) y = 0;
		int orientation = random.nextInt(4);
		switch (orientation) {
			case 0:
				screen.tickScreen(x++, y++);
			break;

			case 1:
				screen.tickScreen(x--, y++);
			break;

			case 2:
				screen.tickScreen(x--, y--);
			break;
			case 3:
				screen.tickScreen(x++, y--);
			break;

			default:
			break;
		}
	}
}
