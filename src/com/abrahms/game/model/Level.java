package com.abrahms.game.model;

import java.util.Random;

import com.abrahms.game.model.gfx.Tile;

public class Level {

	public int		level_Width;
	public int		level_Height;
	private Tile[]	tiles_Set;

	public Level(int width, int height) {
		this.level_Width = width;
		this.level_Height = height;
		this.tiles_Set = new Tile[level_Width * level_Height];
	}
	
	private void load_Level(){
		for (Tile tile : tiles_Set) {
			tile.tile_Load(new Random().nextInt(6));
		}
	}

}
