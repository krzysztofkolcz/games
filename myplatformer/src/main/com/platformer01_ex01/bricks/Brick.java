package com.platformer01_ex01.bricks;

import java.awt.*;
import com.platformer01_ex01.GamePanel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import static java.util.stream.Collectors.toList;

public abstract class Brick{

  
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
    xpos = 160;
    ypos = 0;
    falling = true;
    fallSpeed = 1000; /* 50 pixel na sekunde - 50px/s - TODO */
    /* System.out.println("fallSpeed:"+fallSpeed); */
    //delay = (1000/fallSpeed) ; /* co ile milisekund klocek spada o 1px*/
    delay = 200;
    /* System.out.println("delay:"+delay); */
    totalElapsed = 0;
    startTime = System.nanoTime();
    secBrickPos = new ArrayList<Integer>();
    secBrickMap = new HashMap<Integer,Long>();
  }

  /* update leci 60 razy na sek */
  public boolean update(){
		if(delay == -1) return true;
		long elapsed = (System.nanoTime() - startTime) / 1000000;
    /* System.out.println("startTime:"+startTime); */
    /* System.out.println("elapsed:"+elapsed); */
    totalElapsed += elapsed;
		if(elapsed > delay) {
      fall();
			startTime = System.nanoTime();
		}

    /* System.out.println(totalElapsed); */
    /* System.out.println(ypos); */

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


  /* public abstract boolean checkCollision(); */

  public boolean checkCollision(){
    return canMoveDown();
  }


  /* public boolean checkCollision(){ */
  /*     if(ypos >= GamePanel.HEIGHT - brickSize){ */
  /*       return false; */
  /*     } */
  /*     return true; */
  /* } */

  /* public void draw(Graphics2D g){ */
	/* 	g.setColor(Color.BLACK); */
  /*   g.drawRect (xpos, ypos, brickSize, brickSize); */
	/* 	g.setColor(Color.RED); */
  /*   secBrickMap.forEach( (oPos,oTime) ->{ g.drawRect (xpos, oPos, brickSize, brickSize); g.drawString(Long.toString(oTime), 250,oPos);} ); */
  /* } */


  public void draw(Graphics2D g){
		g.setColor(Color.GRAY);
    getCurrentPosition().forEach( (p) -> {g.fillRect(p.getX(), p.getY(), brickSize, brickSize);} );

		g.setColor(Color.BLACK);
    getCurrentPosition().forEach( (p) -> {g.drawRect(p.getX(), p.getY(), brickSize, brickSize);} );

		g.setColor(Color.RED);
    g.drawRect (xpos, ypos, 1, 1);
  }

  public void printPositions(){
    getCurrentPosition().forEach( (p) -> {System.out.println("x:"+p.getX()+"; y:"+p.getY());} );
  }
  /* public abstract void draw(Graphics2D g); */

  /* public void setMoveLeft(){ */
  /*     if(xpos > 0){ */
  /*       xpos -= brickSize; */
  /*     } */
  /* } */
  /*  */
  /* public void stopMoveLeft(){ */
  /* } */
  /*  */
  /* public void setMoveRight(){ */
  /*     if(xpos < GamePanel.WIDTH - brickSize){ */
  /*       xpos += brickSize; */
  /*     } */
  /* } */
  /*  */
  /* public void stopMoveRight(){ */
  /* } */
  /*  */
  /* public void setMoveDown(){ */
  /*   ypos += brickSize; */
  /* } */
  /*  */
  /* public void rotateLeft(){ */
  /*  */
  /* } */
  /*  */
  /* public void rotateRight(){ */
  /*  */
  /* } */

  /* public abstract void setMoveLeft(); */
  /* public abstract void setMoveRight(); */
  /* public abstract void setMoveDown(); */

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
  public abstract List<Pos> getCurrentPosition();

	public boolean hasCommonPos(List<Pos> list1,List<Pos> list2){
      for(Pos el1: list1){
        if(list2.contains(el1)){
          return true;
        }
      }
      return false;
	}

  public boolean canMoveDown(){
      List<Pos> nextPos = getCurrentPosition().stream().map( p -> new Pos(p.getX(), p.getY() + brickSize) ).collect(toList());

      List<Pos> screenRelPos = getCurrentPosition().stream().filter( p -> p.getY() >= GamePanel.HEIGHT - brickSize ).collect(toList());

			if(screenRelPos.size() > 0){
				return false;
			}

			/* nextPos.forEach( (p) -> {System.out.println("x:"+p.getX()+"; y:"+p.getY());} ); */
			if(hasCommonPos(nextPos,brickOnGround)){
				return false;
			}
      return true;
  }

  public boolean canMoveLeft(){
      List<Pos> nextPos = getCurrentPosition().stream().map( p -> new Pos(p.getX()-brickSize, p.getY()) ).collect(toList());

      List<Pos> screenRelPos = getCurrentPosition().stream().filter( p -> p.getX() <= 0).collect(toList());

			if(screenRelPos.size() > 0){
				return false;
			}

			if(hasCommonPos(nextPos,brickOnGround)){
				return false;
			}
      return true;
  }

  public boolean canMoveRight(){
      List<Pos> nextPos = getCurrentPosition().stream().map( p -> new Pos(p.getX()+brickSize, p.getY()) ).collect(toList());

      List<Pos> screenRelPos = getCurrentPosition().stream().filter( p -> p.getX() >= GamePanel.WIDTH - brickSize - GamePanel.FIX).collect(toList());

			if(screenRelPos.size() > 0){
				return false;
			}
			if(hasCommonPos(nextPos,brickOnGround)){
				return false;
			}
      return true;
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

