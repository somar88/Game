package com.abrahms.game.model.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	public static final SpriteSheet	SS_DEFAULT		= new SpriteSheet("/SS_Empty.png");
	public static final SpriteSheet	FOREST_TILES	= new SpriteSheet("/Forest_Tiles.png");
	private int						ss_Width;
	private int						ss_Height;
	BufferedImage					ss_img;
	private String					path;
	public boolean					isLoaded;
	public int[]					ss_Pixels;

	public SpriteSheet(String path) {
		this.path = path;

		try {

			this.ss_img = (BufferedImage) ImageIO.read(SpriteSheet.class.getResourceAsStream(this.path));
			this.isLoaded = true;
		} catch (IOException e) {
			System.err.println("failed to load sprite sheet, No image found");
		}
		this.ss_Width = ss_img.getWidth();
		this.ss_Height = ss_img.getHeight();
//		this.ss_Pixels = new int[ss_img.getWidth() * ss_img.getHeight()];
		this.ss_Pixels = ss_img.getRGB(0, 0, ss_img.getWidth(), ss_img.getHeight(), null, 0, ss_img.getWidth());
	}

	public boolean isLoaded() {
		return this.isLoaded;
	}

	public int getSs_Width() {
		return ss_Width;
	}

	public void setSs_Width(int ss_Width) {
		this.ss_Width = ss_Width;
	}

	public int getSs_Height() {
		return ss_Height;
	}

	public void setSs_Height(int ss_Height) {
		this.ss_Height = ss_Height;
	}

}
