package com.abrahms.game.model;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	private static final long	serialVersionUID	= 154847L;
	private static final int	WIDTH				= 1024;
	private static final int	HEIGHT				= WIDTH / 16 * 9;
	private final String		GAME_NAME			= "Ex Life";
	boolean						running				= false;

	public Game() {

		JFrame frame = new JFrame(GAME_NAME);
		frame.setSize(new Dimension(WIDTH, HEIGHT));
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setFocusable(true);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("Exiting Game...");
				stop();
				super.windowClosing(e);
			}
		});
		frame.add(this);
		frame.setVisible(true);
		frame.requestFocus();
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

	}

	public void render() {

		Graphics g;
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();

		// rendering Area 51 :P
		////////////////////////////////////////////////////////////////////////////////////////////////
		g.setColor(new Color(1, 5, 4));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		////////////////////////////////////////////////////////////////////////////////////////////////

		g.dispose();
		bs.show();

	}

	public void run() {
		// target is our Target of how many ticks in a second
		double target = 60.0d;
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
