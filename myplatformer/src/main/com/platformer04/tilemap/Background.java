package com.platformer04.tilemap;

import javax.imageio.ImageIO;
import com.platformer04.Const;
import java.awt.*;
import java.awt.image.*;


public class Background{

	private BufferedImage image;

  private double x;
  private double y;
  private double dx;
  private double dy;

	private double moveScale;

  public Background(String s, double ms){
    System.out.println(s); 
    System.out.println(getClass());
    System.out.println(getClass().getResourceAsStream(s));
		try {
			image = ImageIO.read(
          getClass().getResourceAsStream(s)
			);
			moveScale = ms;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
  }

  public void setVector(double dx,double dy){
		this.dx = dx;
		this.dy = dy;
  }

  public void update(){
		x += dx;
    if(x<(-Const.WIDTH)){
      x = Const.WIDTH;
    }
    if(x>Const.WIDTH){
      x = -Const.WIDTH;
    }
		y += dy;
    if(y<(-Const.HEIGHT)){
      y = Const.HEIGHT;
    }
    if(y>Const.HEIGHT){
      y = -Const.HEIGHT;
    }
  }

  public void draw(Graphics2D g){
		g.drawImage(image, (int)x, (int)y, Const.WIDTH, Const.HEIGHT, null);
    if(x<0){
      g.drawImage(image, (int)x+Const.WIDTH, (int)y,Const.WIDTH, Const.HEIGHT, null);
    }
    if(x>0){
      /* -((int)Const.WIDTH-x) = x-Const.WIDTH */
      g.drawImage(image,(int)x-Const.WIDTH, (int)y, Const.WIDTH, Const.HEIGHT,null);
    }

  }

}

