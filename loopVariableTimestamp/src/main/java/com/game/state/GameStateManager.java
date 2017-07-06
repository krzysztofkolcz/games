package com.game.state;

import java.util.ArrayList;
import java.awt.*;

public class GameStateManager{

  private ArrayList<GameState> gameStates;
  private int currentState;
  public static final int LEVEL1STATE = 0;

  public GameStateManager(){
    currentState = LEVEL1STATE;
    gameStates = new ArrayList<GameState>();
    Level1State l1 = new Level1State(this);
    gameStates.add(l1);
  }

  public void setState(int state){
    this.currentState = state;
		gameStates.get(currentState).init();
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

