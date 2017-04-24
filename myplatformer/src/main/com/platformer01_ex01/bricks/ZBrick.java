package com.platformer01_ex01.bricks;

import java.awt.*;
import com.platformer01_ex01.GamePanel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class ZBrick extends Brick{

  /* if brick should stop falling - touches ground or brick on the ground */
  public boolean shouldStopFalling(){
    return false;
  }

  private boolean ishorizontal;

  public ZBrick(){
    init();
  }

  public void init(){
    super.init();
    ishorizontal = true;
  }


  public boolean checkCollision(){
    /* ..  */
    /*  .. */
      if(ishorizontal){
        if(ypos >= GamePanel.HEIGHT - brickSize){
          return false;
        }else{
          return true;
        }

    /*  . */
    /* .. */
    /* . */
      }else{
        if(ypos >= GamePanel.HEIGHT - 2*brickSize){
          return false;
        }else{
          return true;
        }
      }
  }

  public void draw(Graphics2D g){
		g.setColor(Color.BLACK);
    /* ..  */
    /*  .. */
    if(ishorizontal){
        g.drawRect (xpos-brickSize, ypos-brickSize, brickSize, brickSize);
        g.drawRect (xpos, ypos-brickSize, brickSize, brickSize);
        g.drawRect (xpos, ypos, brickSize, brickSize);
        g.drawRect (xpos+brickSize, ypos, brickSize, brickSize);
    /*  . */
    /* .. */
    /* . */
    }else{
        g.drawRect (xpos, ypos-brickSize, brickSize, brickSize);
        g.drawRect (xpos, ypos, brickSize, brickSize);
        g.drawRect (xpos-brickSize, ypos, brickSize, brickSize);
        g.drawRect (xpos-brickSize, ypos+brickSize, brickSize, brickSize);
    }
    // draw xpos ypos
		g.setColor(Color.RED);
    g.drawRect (xpos, ypos, 1, 1);
		/* g.setColor(Color.RED); */
    /* secBrickMap.forEach( (oPos,oTime) ->{ g.drawRect (xpos, oPos, brickSize, brickSize); g.drawString(Long.toString(oTime), 250,oPos);} ); */
  }

  public void setMoveLeft(){
    if(xpos > brickSize){
        xpos -= brickSize;
    }
  }

  public void setMoveRight(){
    /* ..  */
    /*  .. */
    if(ishorizontal){
      if(xpos < GamePanel.WIDTH - GamePanel.FIX - (2*brickSize)){
        xpos += brickSize;
      }
    /*  . */
    /* .. */
    /* . */
    }else{
      if(xpos < GamePanel.WIDTH - GamePanel.FIX - brickSize){
        xpos += brickSize;
      }
    }

  }

  public void setMoveDown(){
    /* ..  */
    /*  .. */
    if(ishorizontal){
      if(ypos < GamePanel.HEIGHT - brickSize){
        ypos += brickSize;
      }
    /*  . */
    /* .. */
    /* . */
    }else{
      if(ypos < GamePanel.HEIGHT - 2*brickSize){
        ypos += brickSize;
      }
    }
  }

  public void rotateLeft(){
    ishorizontal = !ishorizontal;
    /* ..  */
    /*  .. */
    if(ishorizontal){
      if(xpos > GamePanel.WIDTH  - (2*brickSize)){
        xpos = GamePanel.WIDTH - GamePanel.FIX - (2*brickSize);
      }
    /*  . */
    /* .. */
    /* . */
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
    /* ..  */
    /*  .. */
    if(ishorizontal){
      if(xpos > GamePanel.WIDTH  - (2*brickSize)){
        xpos = GamePanel.WIDTH - GamePanel.FIX - (2*brickSize);
      }
    /*  . */
    /* .. */
    /* . */
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
    if(ishorizontal){
        pos = new Pos(xpos-brickSize, ypos-brickSize);
        finalPos.add(pos);
        pos = new Pos(xpos, ypos-brickSize);
        finalPos.add(pos);
        pos = new Pos(xpos, ypos);
        finalPos.add(pos);
        pos = new Pos(xpos+brickSize, ypos);
        finalPos.add(pos);
    }else{
        pos = new Pos(xpos, ypos-brickSize);
        finalPos.add(pos);
        pos = new Pos(xpos, ypos);
        finalPos.add(pos);
        pos = new Pos(xpos-brickSize, ypos);
        finalPos.add(pos);
        pos = new Pos(xpos-brickSize, ypos+brickSize);
        finalPos.add(pos);
    }
    return finalPos;
  }

}
