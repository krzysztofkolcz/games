package com.platformer01_ex01.bricks;

import java.awt.*;
import com.platformer01_ex01.GamePanel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class LongBrick01 extends Brick{

  /* if brick should stop falling - touches ground or brick on the ground */
  public boolean shouldStopFalling(){
    return false;
  }

  private boolean ishorizontal;

  public LongBrick01(){
    init();
  }

  public void init(){
    super.init();
    ishorizontal = true;
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
