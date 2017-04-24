package com.platformer04.gamestate;
import java.awt.*;
import java.awt.event.KeyEvent;

import com.platformer04.tilemap.*;
import com.platformer04.Const;

public class Level1State extends GameState{

	private TileMap tileMap;
  private Background bg;
	
	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}
	
	public void init() {
		
		tileMap = new TileMap(30);
		tileMap.loadTiles("/resources/Tilesets/grasstileset.gif");
		tileMap.loadMap("/resources/Maps/level1-1.map");
		tileMap.setPosition(0, 0);
    this.bg = new Background("/resources/backgrounds/grassbg1.gif",0);
		
	}
	
	
	public void update() {}
	
	public void draw(Graphics2D g) {
		
		// clear screen
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Const.WIDTH, Const.HEIGHT);

    this.bg.draw(g);
		
		// draw tilemap
		tileMap.draw(g);
		
	}
	
	public void keyPressed(int k) {}
	
	public void keyReleased(int k) {}

}
