package com.platformer01_ex01.bricks;

import java.awt.*;
import com.platformer01_ex01.GamePanel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class XZBrick01 extends Brick{

  /* if brick should stop falling - touches ground or brick on the ground */
  public boolean shouldStopFalling(){
    return false;
  }

  private boolean ishorizontal;

  public XZBrick01(){
    init();
  }

  public void init(){
    super.init();
    ishorizontal = true;
  }

  public void rotateLeft(){
    ishorizontal = !ishorizontal;
    /*  .. */
    /* ..  */
    if(ishorizontal){
      if(xpos > GamePanel.WIDTH  - (2*brickSize)){
        xpos = GamePanel.WIDTH - GamePanel.FIX - (2*brickSize);
      }
    /* .  */
    /* .. */
    /*  . */
    }else{
      if(ypos >= GamePanel.HEIGHT - brickSize){
        ypos = GamePanel.HEIGHT - (2*brickSize); 
        //TODO - ale wtedy koniec spadania!
      }
    }
    //TODO - check collision
  }

  public void rotateRight(){
    ishorizontal = !ishorizontal;
    /*  .. */
    /* ..  */
    if(ishorizontal){
      if(xpos > GamePanel.WIDTH  - (2*brickSize)){
        xpos = GamePanel.WIDTH - GamePanel.FIX - (2*brickSize);
      }
    /* .  */
    /* .. */
    /*  . */
    }else{
      if(ypos >= GamePanel.HEIGHT - brickSize){
        ypos = GamePanel.HEIGHT - (2*brickSize); 
        //TODO - ale wtedy koniec spadania!
      }
    }
    //TODO - check collision
  }

  public List<Pos> getCurrentPosition(){
    List<Pos> finalPos = new ArrayList<>();
    Pos pos;
    /*  .. */
    /* ..  */
    if(ishorizontal){
        pos = new Pos(xpos-brickSize, ypos);
        finalPos.add(pos);
        pos = new Pos(xpos, ypos);
        finalPos.add(pos);
        pos = new Pos(xpos, ypos-brickSize);
        finalPos.add(pos);
        pos = new Pos(xpos+brickSize, ypos-brickSize);
        finalPos.add(pos);
    /* .  */
    /* .. */
    /*  . */
    }else{
        pos = new Pos(xpos-brickSize, ypos-brickSize);
        finalPos.add(pos);
        pos = new Pos(xpos-brickSize, ypos);
        finalPos.add(pos);
        pos = new Pos(xpos, ypos);
        finalPos.add(pos);
        pos = new Pos(xpos, ypos+brickSize);
        finalPos.add(pos);
    }
    return finalPos;
  }

}
