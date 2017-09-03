package game;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Level1State extends GameState {
	
  private GameStateManager gsm;
  private float x;
  private float y;
  private Input input;
	
	public Level1State(GameStateManager gsm, Input input) {
		this.gsm = gsm;
		this.input = input;
		init();
	}
	
	public void init() {
    x = 0;
    y = 0;
	}
	
	
	public void update() {

    if(input.getKeyDown(KeyEvent.VK_LEFT) || input.getCurrentKey(KeyEvent.VK_LEFT)){
      if(x > 0){
        x -= 0.1;
      }else{
        x = GamePanel.WIDTH; 
      } 
    }

    if(input.getKeyDown(KeyEvent.VK_RIGHT) || input.getCurrentKey(KeyEvent.VK_RIGHT)){
      if(x < GamePanel.WIDTH){
        x += 0.1;
      }else{
        x = 0; 
      } 
    }

    if(input.getKeyDown(KeyEvent.VK_UP) || input.getCurrentKey(KeyEvent.VK_UP)){
      if(y > 0){
        y -= 0.1;
      }else{
        y = GamePanel.HEIGHT; 
      } 
    }

    if(input.getKeyDown(KeyEvent.VK_DOWN) || input.getCurrentKey(KeyEvent.VK_DOWN)){
      if(y < GamePanel.HEIGHT){
        y += 0.1;
      }else{
        y = 0; 
      } 
    }
  
  }
	
	public void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		g.setColor(Color.BLACK);
		g.fillRect((int)x,(int)y, 4, 4);
	}
	
	
}













