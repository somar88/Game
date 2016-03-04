package com.abrahms.game.model.gfx;

public enum TileTypes {
	WATER(0, 0),

	ROCK(0, 1),

	DESERT(0, 2),

	WOOD(0, 3),

	GRASS(0, 4),

	WALL(0, 5);

	private int[] locationArray = new int[2];

	private TileTypes(int x, int y) {
		this.locationArray[0] = x;
		this.locationArray[1] = y;
	}

	public int[] location() {
		return locationArray;
	}
}
