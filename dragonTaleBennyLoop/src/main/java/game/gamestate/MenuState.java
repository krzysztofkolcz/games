package game.gamestate;

import game.main.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuState extends GameState {

  private final GameStateManager gsm;
  private final Input input;
  private final String menuPositions[] = {
    "Level 1",
    "Level 2"
  };
  private int currentPosition;

	public void init(){
    currentPosition = 0;
  }

  private void setNextPosition(){
    currentPosition++;
    if(currentPosition > menuPositions.length){
      currentPosition = 0;
    }
  }

  private void setPreviousPosition(){
    currentPosition--;
    if(currentPosition < 0){
      currentPosition = menuPositions.length - 1 ;
    }
  }

	public MenuState(GameStateManager gsm, Input input) {
		this.gsm = gsm;
		this.input = input;
		init();
	}

	public void update(){
    if(input.getKeyDown(KeyEvent.VK_UP)){
      System.out.println("up");
      setPreviousPosition();
    }

    if(input.getKeyDown(KeyEvent.VK_DOWN)){
      System.out.println("down");
      setNextPosition();
    }
  }
  
	public void draw(java.awt.Graphics2D g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		g.setColor(Color.BLACK);
    int i = 0;
    for(String menuPosition : menuPositions){
      g.setColor(Color.BLACK);
      if(i == currentPosition){
        g.setColor(Color.BLUE);
      }
      g.drawString(menuPosition,50,50+28*i);
      i++;
    }
  }
}
