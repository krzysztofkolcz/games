package com.platformer01_ex01.bricks;

import java.awt.*;
import com.platformer01_ex01.GamePanel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class SquareBrick extends Brick{

  /* if brick should stop falling - touches ground or brick on the ground */
  public boolean shouldStopFalling(){
    return false;
  }

  public SquareBrick(){
    init();
  }

  public void init(){
    super.init();
  }


  public boolean checkCollision(){
      /* System.out.println("check collision ypos:"+ypos); */
      if(ypos >= GamePanel.HEIGHT - brickSize){
        /* System.out.println("check collision stop:"); */
        return false;
      }else{
        /* System.out.println("check collision ++:"); */
        return true;
      }
  }

  /* public void draw(Graphics2D g){ */
	/* 	g.setColor(Color.BLACK); */
  /*   for(int j = -1; j < 1; j++){ */
  /*     for(int i = -1; i < 1; i++){ */
  /*       g.drawRect (xpos+(i*brickSize), ypos +(j*brickSize), brickSize, brickSize); */
  /*     } */
  /*   } */
	/* 	g.setColor(Color.RED); */
  /*   g.drawRect (xpos, ypos, 1, 1); */
  /* } */

  public void setMoveLeft(){
    if(xpos > brickSize){
      xpos -= brickSize;
    }
    //TODO - check collision
  }

  public void setMoveRight(){
    if(xpos < GamePanel.WIDTH - GamePanel.FIX - brickSize){
      xpos += brickSize;
    }
    //TODO - check collision
  }

  public void setMoveDown(){
    if(ypos >= GamePanel.HEIGHT - brickSize){
      /* System.out.println("move down stop:"); */
    }else{
      ypos += brickSize;
      /* System.out.println("move down ++:"); */
    }
    /* System.out.println("move down ypos:"+ypos); */
    //TODO - check collision
  }

  public void rotateLeft(){
    //TODO - check collision
  }

  public void rotateRight(){
    //TODO - check collision
  }


  public List<Pos> getCurrentPosition(){
    List<Pos> finalPos = new ArrayList<>();
    for(int j = -1; j < 1; j++){
      for(int i = -1; i < 1; i++){
        Pos pos = new Pos(xpos+(i*brickSize),ypos+(j*brickSize));
        finalPos.add(pos);
      }
    }
    return finalPos;
  }

}

