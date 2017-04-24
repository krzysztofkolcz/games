package com.platformer01_ex01.bricks;

import java.awt.*;
import com.platformer01_ex01.GamePanel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class SquareBrick01 extends Brick{

  private int rotation;

  private Pos[][] subPosRelToCenter = {
      /* .. */
      /* .. */
   {new Pos(-1,-1), new Pos(-1,0),new Pos(0,-1),new Pos(0,0)},
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

  public SquareBrick01(){
    init();
  }

  public void init(){
    rotation = 0;
    super.init();
  }

  public void rotateLeft(){
  }

  public void rotateRight(){
  }

}

