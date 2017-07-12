package com.game.state;

import java.awt.*;
import java.util.*;
import com.game.GamePanel;

import static java.util.stream.Collectors.toList;

public class Circle{

	public static final int MAXSPEED = 60; /* 60px/sec - to oznacza, że na 1px na jedną klatkę - gdy klatki lecą bez opóźnień. */

  protected int radius;

  protected int xpos;
  protected int ypos;

  protected int xVector;
  protected int yVector;
	private boolean moveRight;
	private boolean moveLeft;
	private boolean moveUp;
	private boolean moveDown;

  public Circle(){
    radius = 15;
    xpos = 40;
    ypos = 40;
    xVector = MAXSPEED/GamePanel.TARGET_FPS;
    yVector = MAXSPEED/GamePanel.TARGET_FPS;
  }

  public boolean update(){
		moveRight();
		moveLeft();
		moveUp();
		moveDown();
    return true;
  }

  public void setXVector(int xVector){
    this.xVector = xVector;
  }

  public int getXVector(){
    return xVector;
  }

  public void setYVector(int yVector){
    this.yVector = yVector;
  }

  public int getYVector(){
    return yVector;
  }

  public void setXPos(int x){
    this.xpos = x;
  }

  public int getXPos(){
    return this.xpos;
  }

  public void setYPos(int y){
    this.ypos = y;
  }

  public int getYPos(){
    return this.ypos;
  }

  public void draw(Graphics2D g){
    drawOutline(g);
    /* drawCenterPoint(g); */
  }

  private void drawOutline(Graphics2D g){
		g.setColor(Color.GREEN);
    g.drawOval(xpos,ypos,radius,radius);
  }

  private void drawCenterPoint(Graphics2D g){
		g.setColor(Color.RED);
    g.drawRect (xpos, ypos, 1, 1);
  }
  
  public void setMoveLeft(){
		moveLeft = true; 
  }

  public void moveLeft(){
    if(moveLeft && canMoveLeft()){
        xpos -= xVector;
				moveLeft = false;
    }
  }

  public void setMoveRight(){
		moveRight = true; 
  }

	public void moveRight(){
    if(moveRight && canMoveRight()){
			xpos += xVector;
			moveRight = false;
    }
	}

  public void setMoveDown(){
		moveDown = true;
  }

	public void moveDown(){
    if(moveDown && canMoveDown()){
			ypos += yVector;
			moveDown = false;
    }
	}

  public void setMoveUp(){
		moveUp = true;
  }

	public void moveUp(){
    if(moveUp && canMoveUp()){
			ypos -= yVector;
			moveUp = false;
    }
	}

  public boolean canMoveDown(){
    return ypos < GamePanel.HEIGHT - radius;
  }

  public boolean canMoveUp(){
    return ypos > 0; 
  }

  public boolean canMoveLeft(){
    return xpos > 0;
  }

  public boolean canMoveRight(){
    return xpos < GamePanel.WIDTH  - radius;
  }

}
