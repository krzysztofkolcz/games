package com.platformer01_ex01.bricks;

import java.awt.*;
import com.platformer01_ex01.GamePanel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class SquareBrick01 extends Brick{

  /* if brick should stop falling - touches ground or brick on the ground */
  public boolean shouldStopFalling(){
    return false;
  }

  public SquareBrick01(){
    init();
  }

  public void init(){
    super.init();
  }

  public void rotateLeft(){
  }

  public void rotateRight(){
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

