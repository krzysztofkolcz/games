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

  private int position;

  public XLBrick01(){
    init();
  }

  public void init(){
    super.init();
    position = 0;
  }

  public void rotateLeft(){
    if(position==0){
      position = 3;
    }else{
      position--;
    }

    switch(position){

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
    if(position==3){
      position = 0;
    }else{
      position++;
    }
    switch(position){

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

  public List<Pos> getCurrentPosition(){
    List<Pos> finalPos = new ArrayList<>();
    Pos pos;
    switch(position){

      /* . */
      /* . */
      /*.. */
      case 0:
        pos = new Pos(xpos, ypos-(2*brickSize));
        finalPos.add(pos);
        pos = new Pos(xpos, ypos-(brickSize));
        finalPos.add(pos);
        pos = new Pos(xpos, ypos);
        finalPos.add(pos);
        pos = new Pos(xpos-(brickSize), ypos);
        finalPos.add(pos);
        break;  

      /* .   */
      /* ... */
      case 1: 
        pos = new Pos(xpos-brickSize, ypos-brickSize);
        finalPos.add(pos);
        pos = new Pos(xpos-brickSize, ypos);
        finalPos.add(pos);
        pos = new Pos(xpos, ypos);
        finalPos.add(pos);
        pos = new Pos(xpos+brickSize, ypos);
        finalPos.add(pos);

        break;

      /* .. */
      /* . */
      /* . */
      case 2:
        pos = new Pos(xpos, ypos-(brickSize));
        finalPos.add(pos);
        pos = new Pos(xpos-(brickSize), ypos-(brickSize));
        finalPos.add(pos);
        pos = new Pos(xpos-(brickSize), ypos);
        finalPos.add(pos);
        pos = new Pos(xpos-(brickSize), ypos+brickSize);
        finalPos.add(pos);

        break;

      /* ... */
      /*   . */
      case 3: 
        pos = new Pos(xpos-(2*brickSize), ypos-(brickSize));
        finalPos.add(pos);
        pos = new Pos(xpos-(brickSize), ypos-(brickSize));
        finalPos.add(pos);
        pos = new Pos(xpos, ypos);
        finalPos.add(pos);
        pos = new Pos(xpos, ypos-brickSize);
        finalPos.add(pos);
        break;

      default:break;

    }

    return finalPos;
  }

}
