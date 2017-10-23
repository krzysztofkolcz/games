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

    if(Input.getKeyDown(KeyEvent.VK_LEFT) || Input.getKeyCurrent(KeyEvent.VK_LEFT)){
      x--;
      if(x < 0){
        x = Loop01.WIDTH;
      }
    }

    if(Input.getKeyDown(KeyEvent.VK_RIGHT) || Input.getKeyCurrent(KeyEvent.VK_RIGHT)){
      x++;
      if(x > Loop01.WIDTH){
        x = 0;
      }
    }

    if(Input.getKeyDown(KeyEvent.VK_UP) || Input.getKeyCurrent(KeyEvent.VK_UP)){
      y--;
      if(y < 0){
        y = Loop01.HEIGHT;
      }
    }

    if(Input.getKeyDown(KeyEvent.VK_DOWN) || Input.getKeyCurrent(KeyEvent.VK_DOWN)){
      y++;
      if(y > Loop01.HEIGHT){
        y = 0;
      }
    }
  
  }
	
	public void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Loop01.WIDTH, Loop01.HEIGHT);
		g.setColor(Color.BLACK);
		g.fillRect((int)x,(int)y, 4, 4);
	}
	
	
}
