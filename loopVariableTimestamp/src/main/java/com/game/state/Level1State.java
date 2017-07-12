package com.game.state;

import java.awt.*;
import java.awt.event.KeyEvent;

import static java.util.stream.Collectors.toList;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import com.game.GamePanel;

public class Level1State extends GameState{

  private Circle circle;

	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
    this.circle = new Circle();
	}

  public void init(){};
	
	public void update() {
    circle.update();
  }

	public void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
    /* System.out.println("Leve1State.draw()"); */
    /* System.out.println(GamePanel.WIDTH); */
    /* System.out.println(GamePanel.HEIGHT); */
    circle.draw(g);
	}

	public void keyPressed(int k) {
		if(k == KeyEvent.VK_LEFT) circle.setMoveLeft();
		if(k == KeyEvent.VK_H) circle.setMoveLeft();
		if(k == KeyEvent.VK_RIGHT) circle.setMoveRight();
		if(k == KeyEvent.VK_L) circle.setMoveRight();
		if(k == KeyEvent.VK_DOWN) circle.setMoveDown();
		if(k == KeyEvent.VK_J) circle.setMoveDown();
		if(k == KeyEvent.VK_UP) circle.setMoveUp();
		if(k == KeyEvent.VK_K) circle.setMoveUp();
  }
	
	public void keyReleased(int k) {
  }

}


