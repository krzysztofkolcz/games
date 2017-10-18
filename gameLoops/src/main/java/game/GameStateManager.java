package game;

import java.util.ArrayList;

public class GameStateManager {
	
	private ArrayList<GameState> gameStates;
	private int currentState;
	
	/* public static final int MENUSTATE = 0; */
	public static final int LEVEL1STATE = 0;

	public GameStateManager() {
		gameStates = new ArrayList<GameState>();
		
		currentState = LEVEL1STATE;
		gameStates.add(new Level1State(this));
		
	}
	
	public void setState(int state) {
		currentState = state;
		gameStates.get(currentState).init();
	}
	
	public void update() {
		gameStates.get(currentState).update();
	}
	
	public void draw(java.awt.Graphics2D g) {
		gameStates.get(currentState).draw(g);
	}
	
	
}
