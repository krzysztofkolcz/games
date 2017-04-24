package com.platformer01_ex01.bricks;

import java.awt.*;
import com.platformer01_ex01.GamePanel;
import java.util.*;
import java.lang.Override;

public class PlusBrick extends Brick{


  private Pos[][] subPosRelToCenter = {
      /*  .  */
      /* ... */
      /*  .  */
   {new Pos(-1,0), new Pos(0,0),new Pos(1,0),new Pos(0,-1),new Pos(0,1)}
  };


  @Override
  public Pos[][] getSubPosRelToCenter(){
    return subPosRelToCenter;
  }

  @Override
  public int getCurrentRotation(){
    return 0;
  }

  @Override
  public Pos[] getCurrentSubPosRelToCenter(){
    return subPosRelToCenter[getCurrentRotation()];
  }

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

}

