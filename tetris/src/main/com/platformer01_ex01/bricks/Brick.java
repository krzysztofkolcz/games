package com.platformer01_ex01.bricks;

import java.awt.*;
import com.platformer01_ex01.GamePanel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import static java.util.stream.Collectors.toList;

public abstract class Brick{

  private List<Pos> currentPositions; 
  protected List<Pos> brickOnGround;
  protected int brickSize ;

  protected int fallSpeed; 
  protected double delay; 
	protected long startTime;

  protected long totalElapsed;

  protected int xpos;
  protected int ypos;

  protected int xnext;
  protected int ynext;

  protected boolean falling;

  private List<Integer> secBrickPos;
  private Map<Integer,Long> secBrickMap;

  public Brick(){
    init();
  }

  public int getBrickSize(){
    return brickSize;
  }

  public void setBrickOnGround(List<Pos> brickOnGround){
    this.brickOnGround = brickOnGround;
  }

  public void init(){
    brickSize = 5;
    xpos = 40;
    ypos = 0;
    falling = true;
    fallSpeed = 1000; /* 50 pixel na sekunde - 50px/s - TODO */
    delay = 200;
    totalElapsed = 0;
    startTime = System.nanoTime();
    secBrickPos = new ArrayList<Integer>();
    secBrickMap = new HashMap<Integer,Long>();
  }

  public boolean update(){
		if(delay == -1) return true;
		long elapsed = (System.nanoTime() - startTime) / 1000000;
    totalElapsed += elapsed;
		if(elapsed > delay) {
      fall();
			startTime = System.nanoTime();
		}

    if(totalElapsed > 1000){
      totalElapsed -= 1000;
      secBrickPos.add(ypos); 
      secBrickMap.put(ypos,totalElapsed);
    }

    return falling;
  }


  public void fall(){

    falling = checkCollision();
    if(falling){
      xnext = xpos;
      ynext = ypos + brickSize;
      xpos = xnext;
      ypos = ynext;
    }else{
    }
  }

  public boolean getFalling(){
    return falling;
  }

  public void setFalling(boolean f){
    this.falling = f;
  }

  public void setXPos(int x){
    this.xpos = x;
  }

  public void setYPos(int y){
    this.ypos = y;
  }

  public boolean checkCollision(){
    return canMoveDown();
  }

  public void draw(Graphics2D g){
    drawFill(g);
    drawOutline(g);
    drawCenterPoint(g);
  }

  private void drawFill(Graphics2D g){
		g.setColor(Color.GRAY);
    drawCurrentPositions(g);
  }

  private void drawOutline(Graphics2D g){
		g.setColor(Color.BLACK);
    drawCurrentPositions(g);
  }

  private void drawCurrentPositions(Graphics2D g){
    getCurrentPosition().forEach( (p) -> {g.drawRect(p.getX(), p.getY(), brickSize, brickSize);} );
  }

  private void drawCenterPoint(Graphics2D g){
		g.setColor(Color.RED);
    g.drawRect (xpos, ypos, 1, 1);
  }
  
  public void printPositions(){
    getCurrentPosition().forEach( (p) -> {System.out.println("x:"+p.getX()+"; y:"+p.getY());} );
  }

  public void setMoveLeft(){
    if(canMoveLeft()){
        xpos -= brickSize;
    }
  }

  public void setMoveRight(){
    if(canMoveRight()){
       xpos += brickSize;
    }
  }

  public void setMoveDown(){
    if(canMoveDown()){
        ypos += brickSize;
    }
  }

  public abstract void rotateLeft();
  public abstract void rotateRight();
  public abstract Pos[][] getSubPosRelToCenter();
  public abstract Pos[] getCurrentSubPosRelToCenter();
  public abstract int getCurrentRotation();

  public boolean canMoveDown(){
      List<Pos> nextPos = getNextPositions(0, brickSize);
      List<Pos> screenRelPos = getCurrentPosition().stream().filter( p -> p.getY() >= GamePanel.HEIGHT - brickSize ).collect(toList());

      if(isColisionWithBricksOnGround(nextPos) || (screenRelPos.size() > 0)){
        return false;
      }

      return true;
  }

  public boolean canMoveLeft(){
      List<Pos> nextPos = getNextPositions(-brickSize, 0);
      List<Pos> screenRelPos = getCurrentPosition().stream().filter( p -> p.getX() <= 0).collect(toList());

      if(isColisionWithBricksOnGround(nextPos) || (screenRelPos.size() > 0)){
        return false;
      }

      return true;
  }

  public boolean canMoveRight(){
      List<Pos> nextPos = getNextPositions(brickSize, 0);
      List<Pos> screenRelPos = getCurrentPosition().stream().filter( p -> p.getX() >= GamePanel.WIDTH - brickSize - GamePanel.FIX).collect(toList());

      if(isColisionWithBricksOnGround(nextPos) || (screenRelPos.size() > 0)){
        return false;
      }

      return true;
  }
 
  private List<Pos> getNextPositions(int dx, int dy){
      List<Pos> nextPos = getCurrentPosition().stream().map( p -> new Pos(p.getX()+dx, p.getY()+dy) ).collect(toList());
      return nextPos;
  }

  private boolean isColisionWithBricksOnGround(List<Pos> positions){
			if(hasCommonPos(positions,brickOnGround)){
				return true;
			}
      return false;
  }

	private boolean hasCommonPos(List<Pos> list1,List<Pos> list2){
      for(Pos el1: list1){
        if(list2.contains(el1)){
          return true;
        }
      }
      return false;
	}



  public synchronized List<Pos> getCurrentPosition(){
    currentPositions = new ArrayList<Pos>();
    for(Pos p : getCurrentSubPosRelToCenter()){
      addCurrentPositions(xpos+(brickSize*p.getX()), ypos+(brickSize*p.getY()));
    } 
    return currentPositions;
  }

  private void addCurrentPositions(int x,int y){
      Pos pos = new Pos(x,y);
      currentPositions.add(pos);
  }

}

/*
Typy klocków:
LongBrick:
....

SquareBrick:
..
..

..
 ..

 ..
..

XLBrick
..
.
.

LBrick
..
 .
 .




*/

