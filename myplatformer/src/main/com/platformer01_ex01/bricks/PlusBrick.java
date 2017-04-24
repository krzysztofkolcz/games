package com.platformer01_ex01.bricks;

import java.awt.*;
import com.platformer01_ex01.GamePanel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class PlusBrick extends Brick{

  public PlusBrick(){
    init();
  }

  public void init(){
    super.init();
  }

  public boolean checkCollision(){
    return canMoveDown();
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
  

  public void rotateLeft(){
    //TODO - general fun
  }

  public void rotateRight(){
    //TODO - general fun
  }

  public List<Pos> getCurrentPosition(){
    List<Pos> finalPos = new ArrayList<>();
    Pos pos; 
    for(int i = -1; i <= 1; i++){
      pos = new Pos(xpos+(i*brickSize),ypos);
      finalPos.add(pos);
    }
    pos = new Pos(xpos,ypos-brickSize);
    finalPos.add(pos);
    pos = new Pos(xpos,ypos+brickSize);
    finalPos.add(pos);
    return finalPos;
  }

}

