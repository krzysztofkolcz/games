package com.platformer01_ex01.bricks;

import java.awt.*;
import com.platformer01_ex01.GamePanel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class LBrick extends Brick{

  /* if brick should stop falling - touches ground or brick on the ground */
  public boolean shouldStopFalling(){
    return false;
  }

  private int position;

  public LBrick(){
    init();
  }

  public void init(){
    super.init();
    position = 0;
  }


  public boolean checkCollision(){
    switch(position){

      /* . */
      /* . */
      /* .. */
      case 0:
        if(ypos >= GamePanel.HEIGHT - brickSize){
          return false;
        }else{
          return true;
        }

      /*   . */
      /* ... */
      case 1: 
        if(ypos >= GamePanel.HEIGHT - brickSize){
          return false;
        }else{
          return true;
        }

      /* .. */
      /*  . */
      /*  . */
      case 2:
        if(ypos >= GamePanel.HEIGHT - (2*brickSize)){
          return false;
        }else{
          return true;
        }

      /* ... */
      /* .   */
      case 3: 
        if(ypos >= GamePanel.HEIGHT - brickSize){
          return false;
        }else{
          return true;
        }

      default:
        return false;

    }

  }

  public void draw(Graphics2D g){
		g.setColor(Color.BLACK);

    switch(position){

      /* . */
      /* . */
      /* .. */
      case 0:
        g.drawRect (xpos-(brickSize), ypos-(2*brickSize), brickSize, brickSize);
        g.drawRect (xpos-(brickSize), ypos-(brickSize), brickSize, brickSize);
        g.drawRect (xpos-(brickSize), ypos, brickSize, brickSize);
        g.drawRect (xpos, ypos, brickSize, brickSize);
        break;  

      /*   . */
      /* ... */
      case 1: 
        g.drawRect (xpos-(2*brickSize), ypos, brickSize, brickSize);
        g.drawRect (xpos-(brickSize), ypos, brickSize, brickSize);
        g.drawRect (xpos, ypos, brickSize, brickSize);
        g.drawRect (xpos, ypos-(brickSize), brickSize, brickSize);

        break;

      /* .. */
      /*  . */
      /*  . */
      case 2:
        g.drawRect (xpos-(brickSize), ypos-(brickSize), brickSize, brickSize);
        g.drawRect (xpos, ypos-(brickSize), brickSize, brickSize);
        g.drawRect (xpos, ypos, brickSize, brickSize);
        g.drawRect (xpos, ypos+brickSize, brickSize, brickSize);
        
        break;

      /* ... */
      /* .   */
      case 3: 
        g.drawRect (xpos-(brickSize), ypos-(brickSize), brickSize, brickSize);
        g.drawRect (xpos-(brickSize), ypos, brickSize, brickSize);
        g.drawRect (xpos, ypos-(brickSize), brickSize, brickSize);
        g.drawRect (xpos+brickSize, ypos-brickSize, brickSize, brickSize);
        break;

      default:break;

    }


    // draw xpos ypos
		g.setColor(Color.RED);
    g.drawRect (xpos, ypos, 1, 1);
  }

  public void setMoveLeft(){
    switch(position){

      /* . */
      /* . */
      /* .. */
      case 0:
        if(xpos > brickSize){
          xpos -= brickSize;
        }
        break;  

      /*   . */
      /* ... */
      case 1: 
        if(xpos > 2*brickSize){
          xpos -= brickSize;
        }
        break;

      /* .. */
      /*  . */
      /*  . */
      case 2:
        if(xpos > brickSize){
          xpos -= brickSize;
        }
        break;

      /* ... */
      /* .   */
      case 3: 
        if(xpos > brickSize){
          xpos -= brickSize;
        }
        break;

      default:break;

    }
  }

  public void setMoveRight(){
    switch(position){

      /* . */
      /* . */
      /* .. */
      case 0:
        if(xpos < GamePanel.WIDTH - GamePanel.FIX - brickSize){
          xpos += brickSize;
        }
        break;  

      /*   . */
      /* ... */
      case 1: 
        if(xpos < GamePanel.WIDTH - GamePanel.FIX - brickSize){
          xpos += brickSize;
        }
        break;

      /* .. */
      /*  . */
      /*  . */
      case 2:
        if(xpos < GamePanel.WIDTH - GamePanel.FIX - brickSize){
          xpos += brickSize;
        }
        break;

      /* ... */
      /* .   */
      case 3: 
        if(xpos < GamePanel.WIDTH - GamePanel.FIX - (2*brickSize)){
          xpos += brickSize;
        }
        break;

      default:break;

    }
  }

  public void setMoveDown(){
    /* ypos += brickSize; */
    switch(position){

      /* . */
      /* . */
      /* .. */
      case 0:
        if(ypos < GamePanel.HEIGHT - brickSize){
          ypos += brickSize;
        }
        break;  

      /*   . */
      /* ... */
      case 1: 
        if(ypos < GamePanel.HEIGHT - brickSize){
          ypos += brickSize;
        }
        break;

      /* .. */
      /*  . */
      /*  . */
      case 2:
        if(ypos < GamePanel.HEIGHT - 2*brickSize){
          ypos += brickSize;
        }
        break;

      /* ... */
      /* .   */
      case 3: 
        if(ypos < GamePanel.HEIGHT - brickSize){
          ypos += brickSize;
        }
        break;

      default:break;

    }
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
      /* .. */
      case 0:
        break;  

      /*   . */
      /* ... */
      case 1: 
        if(xpos <= 0 + brickSize){
          xpos = 2*brickSize;
        }
        break;

      /* .. */
      /*  . */
      /*  . */
      case 2:
        if(ypos >= GamePanel.HEIGHT - 2*brickSize){
          ypos = GamePanel.HEIGHT - (2*brickSize); 
        }
        break;

      /* ... */
      /* .   */
      case 3: 
        if(xpos >= GamePanel.WIDTH - GamePanel.FIX - brickSize){
          xpos = GamePanel.WIDTH - GamePanel.FIX - (2*brickSize); 
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
      /* .. */
      case 0:
        break;  

      /*   . */
      /* ... */
      case 1: 
        if(xpos <= 0 + brickSize){
          xpos = 2*brickSize;
        }
        break;

      /* .. */
      /*  . */
      /*  . */
      case 2:
        if(ypos >= GamePanel.HEIGHT - 2*brickSize){
          ypos = GamePanel.HEIGHT - (2*brickSize); 
        }
        break;

      /* ... */
      /* .   */
      case 3: 
        if(xpos >= GamePanel.WIDTH - GamePanel.FIX - brickSize){
          xpos = GamePanel.WIDTH - GamePanel.FIX - (2*brickSize); 
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
      /* .. */
      case 0:
        pos = new Pos(xpos-(brickSize), ypos-(2*brickSize));
        finalPos.add(pos);
        pos = new Pos(xpos-(brickSize), ypos-(brickSize));
        finalPos.add(pos);
        pos = new Pos(xpos-(brickSize), ypos);
        finalPos.add(pos);
        pos = new Pos(xpos, ypos);
        finalPos.add(pos);
        break;  

      /*   . */
      /* ... */
      case 1: 
        pos = new Pos(xpos-(2*brickSize), ypos);
        finalPos.add(pos);
        pos = new Pos(xpos-(brickSize), ypos);
        finalPos.add(pos);
        pos = new Pos(xpos, ypos);
        finalPos.add(pos);
        pos = new Pos(xpos, ypos-(brickSize));
        finalPos.add(pos);

        break;

      /* .. */
      /*  . */
      /*  . */
      case 2:
        pos = new Pos(xpos-(brickSize), ypos-(brickSize));
        finalPos.add(pos);
        pos = new Pos(xpos, ypos-(brickSize));
        finalPos.add(pos);
        pos = new Pos(xpos, ypos);
        finalPos.add(pos);
        pos = new Pos(xpos, ypos+brickSize);
        finalPos.add(pos);
        
        break;

      /* ... */
      /* .   */
      case 3: 
        pos = new Pos(xpos-(brickSize), ypos-(brickSize));
        finalPos.add(pos);
        pos = new Pos(xpos-(brickSize), ypos);
        finalPos.add(pos);
        pos = new Pos(xpos, ypos-(brickSize));
        finalPos.add(pos);
        pos = new Pos(xpos+brickSize, ypos-brickSize);
        finalPos.add(pos);
        break;

      default:break;

    }
    return finalPos;
  }
}
