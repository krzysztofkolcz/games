package com.platformer01_ex01.bricks;

import java.awt.*;
import com.platformer01_ex01.GamePanel;
import java.util.*;
import java.lang.Override;

public class ZBrick01 extends Brick{

  private int rotation;

  private Pos[][] subPosRelToCenter = {
    /* ..  */
    /*  .. */
    {new Pos(-1,-1), new Pos(0,-1),new Pos(0,0),new Pos(1,0)},
    /*  . */
    /* .. */
    /* .  */
    {new Pos(0,-1), new Pos(0,0),new Pos(-1,0),new Pos(-1,1)}
  };

  @Override
  public Pos[][] getSubPosRelToCenter(){
    return subPosRelToCenter;
  }

  @Override
  public int getCurrentRotation(){
    return rotation;
  }

  @Override
  public Pos[] getCurrentSubPosRelToCenter(){
    return subPosRelToCenter[getCurrentRotation()];
  }

  /* if brick should stop falling - touches ground or brick on the ground */
  public boolean shouldStopFalling(){
    return false;
  }

  private boolean ishorizontal;

  public ZBrick01(){
    init();
  }

  public void init(){
    super.init();
    ishorizontal = true;
    rotation = 0;
  }

  public void rotateLeft(){
    rotation = Math.abs((rotation - 1) % 2);
    ishorizontal = !ishorizontal;
    /* ..  */
    /*  .. */
    if(ishorizontal){
      if(xpos > GamePanel.WIDTH  - (2*brickSize)){
        xpos = GamePanel.WIDTH - GamePanel.FIX - (2*brickSize);
      }
    /*  . */
    /* .. */
    /* . */
    }else{
      if(ypos >= GamePanel.HEIGHT - brickSize){
        ypos = GamePanel.HEIGHT - (2*brickSize); 
        //TODO - ale wtedy koniec spadania!
      }
    }
    //TODO - check collision
  }

  public void rotateRight(){
    rotation = Math.abs((rotation + 1) % 2);
    ishorizontal = !ishorizontal;
    /* ..  */
    /*  .. */
    if(ishorizontal){
      if(xpos > GamePanel.WIDTH  - (2*brickSize)){
        xpos = GamePanel.WIDTH - GamePanel.FIX - (2*brickSize);
      }
    /*  . */
    /* .. */
    /* . */
    }else{
      if(ypos >= GamePanel.HEIGHT - brickSize){
        ypos = GamePanel.HEIGHT - (2*brickSize); 
        //TODO - ale wtedy koniec spadania!
      }
    }
    //TODO - check collision
  }


}
