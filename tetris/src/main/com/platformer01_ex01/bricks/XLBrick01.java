package com.platformer01_ex01.bricks;

import java.awt.*;
import com.platformer01_ex01.GamePanel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class XLBrick01 extends Brick{

  /* if brick should stop falling - touches ground or brick on the ground */
  public boolean shouldStopFalling(){
    return false;
  }

  private int rotation;

  private Pos[][] subPosRelToCenter = {
      /* . */
      /* . */
      /*.. */
    {new Pos(0,-2), new Pos(0,-1),new Pos(0,0),new Pos(-1,0)},
      /* .   */
      /* ... */
    {new Pos(-1,-1), new Pos(-1,0),new Pos(0,0),new Pos(1,0)},
      /* .. */
      /* . */
      /* . */
    {new Pos(0,-1), new Pos(-1,-1),new Pos(-1,0),new Pos(-1,1)},
      /* ... */
      /*   . */
    {new Pos(-2,-1), new Pos(-1,-1),new Pos(0,0),new Pos(0,-1)}
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

  public XLBrick01(){
    init();
  }

  public void init(){
    super.init();
    rotation = 0;
  }

  public void rotateLeft(){
    if(rotation==0){
      rotation = 3;
    }else{
      rotation--;
    }

    switch(rotation){

      /* . */
      /* . */
      /*.. */
      case 0:
        break;  

      /* .   */
      /* ... */
      case 1: 
        if(xpos >= GamePanel.WIDTH - GamePanel.FIX - brickSize){
          xpos = GamePanel.WIDTH - GamePanel.FIX - (2*brickSize); 
        }
        break;

      /* .. */
      /* . */
      /* . */
      case 2:
        if(ypos >= GamePanel.HEIGHT - 2*brickSize){
          ypos = GamePanel.HEIGHT - (2*brickSize); 
        }
        break;

      /* ... */
      /*   . */
      case 3: 
        if(xpos <= 0 + brickSize){
          xpos = 2*brickSize;
        }
        break;

      default:break;

    }
    
    //TODO - check collision
  }

  public void rotateRight(){
    if(rotation==3){
      rotation = 0;
    }else{
      rotation++;
    }
    switch(rotation){

      /* . */
      /* . */
      /*.. */
      case 0:
        break;  

      /* .   */
      /* ... */
      case 1: 
        if(xpos >= GamePanel.WIDTH - GamePanel.FIX - brickSize){
          xpos = GamePanel.WIDTH - GamePanel.FIX - (2*brickSize); 
        }
        break;

      /* .. */
      /* . */
      /* . */
      case 2:
        if(ypos >= GamePanel.HEIGHT - 2*brickSize){
          ypos = GamePanel.HEIGHT - (2*brickSize); 
        }
        break;

      /* ... */
      /*   . */
      case 3: 
        if(xpos <= 0 + brickSize){
          xpos = 2*brickSize;
        }
        break;

      default:break;

    }
    //TODO - check collision
  }

}
