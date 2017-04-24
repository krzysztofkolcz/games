package com.platformer01_ex01.bricks;

import java.awt.*;
import com.platformer01_ex01.GamePanel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class LongBrick extends Brick{

  /* if brick should stop falling - touches ground or brick on the ground */
  public boolean shouldStopFalling(){
    return false;
  }

  private boolean ishorizontal;

  public LongBrick(){
    init();
  }

  public void init(){
    super.init();
    ishorizontal = true;
  }

  public boolean checkCollision(){
      if(ishorizontal){
        if(ypos >= GamePanel.HEIGHT - brickSize){
          return false;
        }else{
          return true;
        }
      }else{
        if(ypos >= GamePanel.HEIGHT - 2*brickSize){
          return false;
        }else{
          return true;
        }
      }
  }

  public void setMoveLeft(){
    if(ishorizontal){
      if(xpos > 2*brickSize){
        xpos -= brickSize;
      }
    }else{
      if(xpos > 0){
        xpos -= brickSize;
      }
    }
  }

  public void setMoveRight(){

    if(ishorizontal){
      if(xpos < GamePanel.WIDTH - GamePanel.FIX - (2*brickSize)){
        xpos += brickSize;
      }
    }else{
      if(xpos < GamePanel.WIDTH - GamePanel.FIX - brickSize){
        xpos += brickSize;
      }
    }

  }

  public void setMoveDown(){

    if(ishorizontal){
      if(ypos < GamePanel.HEIGHT - brickSize){
        ypos += brickSize;
      }
    }else{
      if(ypos < GamePanel.HEIGHT - 2*brickSize){
        ypos += brickSize;
      } 
    }
  }
  

  public void rotateLeft(){
    if(ishorizontal){
    }else{
      if(xpos < 2*brickSize){
        xpos = 2*brickSize;
      }else if(xpos > GamePanel.WIDTH  - (2*brickSize)){
        xpos = GamePanel.WIDTH - GamePanel.FIX - (2*brickSize);
      }
    }
    ishorizontal = !ishorizontal;
    //TODO - check collision
  }

  public void rotateRight(){
    if(ishorizontal){
    }else{
      if(xpos < 2*brickSize){
        xpos = 2*brickSize;
      }else if(xpos > GamePanel.WIDTH - (2*brickSize)){
        xpos = GamePanel.WIDTH -GamePanel.FIX - (2*brickSize);
      }
    }
    ishorizontal = !ishorizontal;
    //TODO - check collision
  }

  public List<Pos> getCurrentPosition(){
    List<Pos> finalPos = new ArrayList<>();
    if(ishorizontal){
      for(int i = -2; i < 2; i++){
        Pos pos = new Pos(xpos+(i*brickSize),ypos);
        finalPos.add(pos);
      }
    }else{
      for(int i = -2; i < 2; i++){
        Pos pos = new Pos(xpos,ypos+(i*brickSize));
        finalPos.add(pos);
      }
    }
    return finalPos;
  }

}
