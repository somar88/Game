package com.abrahms.game.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.abrahms.game.model.gfx.SpriteSheet;
import com.abrahms.game.model.gfx.Tile;

public class Level {

	public int					level_TWidth;						// the width of the level in tiles 
	public int					level_THeight;						// the height of the level in tiles 
	public BufferedImage		l_Img;
	//	private int[]				tiles_Set;
	private Tile[]				tiles_Set;
	public int[]				level_Pixels;
	public Random				random			= new Random();

	public static final Level	RANDOM_LEVEL	= new Level(8, 8);

	public Level(int width, int height) {
		this.level_TWidth = width;
		this.level_THeight = height;
		this.tiles_Set = new Tile[level_TWidth * level_THeight];
		this.level_Pixels = new int[(level_TWidth << 5) * (level_THeight << 5)];
		load_LevelTiles();
	}

	public Level(String path) {
		try {
			l_Img = (BufferedImage) ImageIO.read(SpriteSheet.class.getResourceAsStream(path));
		} catch (IOException e) {
			System.out.println("Level is Loaded");
			e.printStackTrace();
		}
		level_Pixels = l_Img.getRGB(0, 0, l_Img.getWidth(), l_Img.getHeight(), null, 0, l_Img.getWidth());
	}

	public void load_LevelTiles() {
		for (int i = 0; i < tiles_Set.length; i++) {

			switch (random.nextInt(8)) {
				case 0:

				break;
				case 1:
					this.tiles_Set[i] = new Tile();
				break;
				case 2:
					this.tiles_Set[i] = new Tile();

				break;
				case 3:
					this.tiles_Set[i] = new Tile();

				break;
				case 4:
					this.tiles_Set[i] = new Tile();

				break;
				case 5:
					this.tiles_Set[i] = new Tile();

				break;
				case 6:
					this.tiles_Set[i] = new Tile();

				break;
				case 7:
					this.tiles_Set[i] = new Tile();

				break;

				default:
				break;

			}

			//			System.out.println("LOADING LTILES");
		}
	}

	public void load_LPixels(Tile[] tiles) {
		for (int x = 0; x < level_TWidth; x++) {
			for (int y = 0; y < level_THeight; y++) {
				tiles[x + y * level_TWidth].tile_renderLevel(this, x, y);
			}
		}
	}
}