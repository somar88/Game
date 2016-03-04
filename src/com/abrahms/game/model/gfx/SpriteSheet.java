package com.abrahms.game.model.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	public int		ss_Width;
	public int		ss_Height;
	BufferedImage	ss_img;
	private String	path;

	public SpriteSheet(String path) {
		this.path = path;
		
	}

}
