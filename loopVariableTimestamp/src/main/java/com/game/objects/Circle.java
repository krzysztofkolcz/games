package com.game.state;

import java.awt.*;
import java.util.*;
import com.game.GamePanel;

import static java.util.stream.Collectors.toList;

public class Circle{

  protected int radius;

  protected int xpos;
  protected int ypos;

  protected int xVector;
  protected int yVector;

  public Circle(){
    radius = 5;
    xpos = 40;
    ypos = 40;
    xVector = 1;
    yVector = 1;
  }

  public boolean update(){
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
    drawCenterPoint(g);
  }

  private void drawOutline(Graphics2D g){
		g.setColor(Color.BLACK);
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g.drawOval(xpos,ypos,radius,radius);
  }

  private void drawCenterPoint(Graphics2D g){
		g.setColor(Color.RED);
    g.drawRect (xpos, ypos, 1, 1);
  }
  
  public void setMoveLeft(){
    if(canMoveLeft()){
        xpos -= xVector;
    }
  }

  public void setMoveRight(){
    if(canMoveRight()){
       xpos += xVector;
    }
  }

  public void setMoveDown(){
    if(canMoveDown()){
        ypos += yVector;
    }
  }

  public boolean canMoveDown(){
    return ypos < GamePanel.HEIGHT - radius;
  }

  public boolean canMoveUp(){
    return ypos > radius; 
  }

  public boolean canMoveLeft(){
    return xpos > radius;
  }

  public boolean canMoveRight(){
    return xpos < GamePanel.WINDOWWIDTH  - radius;
  }

}
