package game;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Level1State extends GameState {
	
  private GameStateManager gsm;
  private float x;
  private float y;
	
	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}
	
	public void init() {
    x = 0;
    y = 0;
	}
	
	
	public void update() {

    if(Input.getKeyDown(KeyEvent.VK_LEFT)){
      System.out.println("left down");
    }

    if(Input.getKeyCurrent(KeyEvent.VK_LEFT)){
      System.out.println("left current");
    }

    if(Input.getKeyDown(KeyEvent.VK_RIGHT) || Input.getKeyCurrent(KeyEvent.VK_RIGHT)){
    }

    if(Input.getKeyDown(KeyEvent.VK_UP) || Input.getKeyCurrent(KeyEvent.VK_UP)){
    }

    if(Input.getKeyDown(KeyEvent.VK_DOWN) || Input.getKeyCurrent(KeyEvent.VK_DOWN)){
    }
  
  }
	
	public void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Loop01.WIDTH, Loop01.HEIGHT);
		g.setColor(Color.BLACK);
		g.fillRect((int)x,(int)y, 4, 4);
	}
	
	
}
