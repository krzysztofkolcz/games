package com.platformer01.mainmenu.gamestate;
import java.awt.Graphics2D; 
public abstract class GameState{

  public GameStateManager gsm;

  public void init(){};
  public void update(){};
  public void draw(Graphics2D g){};
  public void keyPressed(int k){};
  public void keyReleased(int k){};

}
