package com.platformer04.tilemap;

import java.awt.image.*;

public class Tile{
  private BufferedImage image;
  private int type;

  public static final int NORMAL = 0;
  public static final int BLOCKED = 1;

  public Tile(BufferedImage tailimage, int type){
    this.image = tailimage;
    this.type = type;
  }

  public BufferedImage getImage(){return this.image;}
  public int getType(){return this.type;}


}
