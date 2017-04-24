package com.platformer01.mainmenu.gamestate;

import java.util.ArrayList;
import java.awt.*;

public class GameStateManager{

  private ArrayList<GameState> gameStates;
  private int currentState;
  private final int MAINMENU = 0;

  public GameStateManager(){
    currentState = MAINMENU;
    gameStates = new ArrayList<GameState>();
    MenuState ms = new MenuState(this);
    gameStates.add(ms);
  }

  public void setState(int state){
    /* if(state > gameStates.length()){ */
    /*   state = 0; */
    /* }else if(state < 0){ */
    /*   state = gameStates.length()-1; */
    /* } */
    this.currentState = state;
  }

  public int getState(){
    return this.currentState;
  }

  public void draw(Graphics2D g){
    gameStates.get(currentState).draw(g);
  }

  public void update(){
    gameStates.get(currentState).update();
  }

	public void keyPressed(int keyCode) {
    gameStates.get(currentState).keyPressed(keyCode);
  }

	public void keyReleased(int keyCode) {
    gameStates.get(currentState).keyReleased(keyCode);
  }

}
