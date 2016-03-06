package com.abrahms.game.model.gfx;

import com.abrahms.game.model.Level;

public class Tile {

	private static final int	TILE_WIDTH	= 32;
	private static final int	TILE_HEIGHT	= 32;
	private int[]				tile_Pixels	= new int[TILE_WIDTH * TILE_HEIGHT];

	public Tile() {

	}

	public void tile_renderLevel(Level l, int x_Pos, int y_Pos) {

		for (int x = 0; x < TILE_WIDTH; x++) {
			for (int y = 0; y < TILE_HEIGHT; y++) {
				l.level_Pixels[((x_Pos * TILE_WIDTH) + x) + (y_Pos * TILE_HEIGHT * (l.level_TWidth >> 5)
						+ (y * (l.level_TWidth >> 5)))] = this.tile_Pixels[x + y * TILE_WIDTH];
			}
		}
	}

}
