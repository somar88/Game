package com.abrahms.game.model.gfx;

public class Sprite {

	public static final Sprite	TEST_SPRITE	= new Sprite(SpriteSheet.FOREST_TILES, 32, 32, 0, 12);

	public int					s_Width;
	public int					s_Height;
	public int[]				s_pixels;
	private int					s_XPos;
	private int					s_YPos;
	public boolean				s_isLoaded	= false;

	public Sprite(SpriteSheet ss, int s_Width, int s_Height, int s_XPos, int s_YPos) {
		this.s_Width = s_Width;
		this.s_Height = s_Height;
		this.s_XPos = s_XPos;
		this.s_YPos = s_YPos;
		s_pixels = new int[s_Height * s_Width];
		spriteLoad(ss, this.s_XPos, this.s_YPos);
	}

	private void spriteLoad(SpriteSheet ss, int x_Pos, int y_Pos) {
		if (x_Pos > (ss.getSs_Width() / this.s_Width)) {
			x_Pos = 0;
			System.out.println("x is out of bound");
		}
		if (y_Pos > (ss.getSs_Height() / this.s_Height)) {
			y_Pos = 0;
			System.out.println("y is out of bound ");
		}
		for (int x = 0; x < s_Width; x++) {
			for (int y = 0; y < s_Height; y++) {
				s_pixels[x + y * s_Width] = ss.ss_Pixels[((x_Pos * s_Width) + x)
						+ ((y_Pos * s_Height * ss.getSs_Width()) + y * ss.getSs_Width())];
			}
		}
		this.s_isLoaded = true;
	}

	public void s_Render(int[] rendering_Pixels, int width) {
		for (int i = 0; i < this.s_Width; i++) {
			for (int j = 0; j < this.s_Height; j++) {
				rendering_Pixels[i + j * width] = this.s_pixels[i + j * this.s_Width];
			}
		}
	}

}
