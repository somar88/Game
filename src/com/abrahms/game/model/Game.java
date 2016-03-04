package com.abrahms.game.model;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import com.abrahms.game.view.View;

public class Game implements Runnable {
	boolean					running	= false;
	private View			view;
	private BufferedImage	img;
	private Graphics		g;
	private BufferStrategy	bs;
	private Screen			screen;
	private int[]			rendering_Pixels;
	int						x;
	int						y;
	private Random			random	= new Random();

	public Game(View view) {
		this.view = view;
		this.screen = new Screen(view.WIDTH, view.HEIGHT);
		this.img = new BufferedImage(view.WIDTH, view.HEIGHT, BufferedImage.TYPE_INT_BGR);
		rendering_Pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
		for (int i = 0; i < rendering_Pixels.length; i++) {
			rendering_Pixels[i] = 0xFFFFFF;
		}
		x = 0;
		y = 0;
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

	public void tick() {
		if (x == view.WIDTH || x == 0) x = random.nextInt(view.WIDTH);
		if (y == view.HEIGHT || y == 0) y = random.nextInt(view.HEIGHT);
		int orientaion = random.nextInt(4);
		switch (orientaion) {
			case 0:
				screen.tickScreen(this.x++, this.y++);
			break;

			case 1:
				screen.tickScreen(this.x--, this.y++);
			break;

			case 2:
				screen.tickScreen(this.x++, this.y--);
			break;
			case 3:
				screen.tickScreen(this.x--, this.y--);
			break;

			default:
			break;
		}

	}

	public void render() {
		bs = view.getBufferStrategy();
		if (bs == null) {
			view.createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();

		// rendering Area 51 :P
		////////////////////////////////////////////////////////////////////////////////////////////////
		screen.renderScreen(rendering_Pixels);
		g.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), null);
		screen.resetArray(rendering_Pixels);
		//		System.out.println("testing");
		////////////////////////////////////////////////////////////////////////////////////////////////

		g.dispose();
		bs.show();

	}

	public void run() {
		// target is our Target of how many ticks in a second
		double target = 1000.0d;
		double nanoSecondPerTick = 1000_000_000.0d / target;
		long startCheck = System.nanoTime();
		// timer will be used for fps and tps printing to the console
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

}
