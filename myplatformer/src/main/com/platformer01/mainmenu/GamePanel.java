package com.platformer01.mainmenu;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.JPanel;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;

import com.platformer01.mainmenu.gamestate.*;

public class GamePanel extends JPanel implements Runnable, KeyListener{

	private Thread thread;
	private boolean running;

	private int FPS = 60;
	private long delay = 1000 / FPS; /* sleep for 1/60 of the second */

  private GameStateManager gsm;

	// image
	private BufferedImage image;
	private Graphics2D g;

  public GamePanel(){
		super();
    System.out.println("game panel");
		setPreferredSize(new Dimension(Const.WIDTH , Const.HEIGHT ));
		setFocusable(true);
		requestFocus();
  }

  public void addNotify(){
    System.out.println("notify");
		super.addNotify();
    if(thread == null){
      thread = new Thread(this);
			addKeyListener(this);
			thread.start();
    }
  }

  public void run(){
    init();
    draw();
    drawToScreen();
		/* repaint(); - nie działa repaint - TODO dlaczego */

		long startTime;
		long elapsedTime;
		long waitTime;

    while(running){
      startTime = System.nanoTime();
      update();
      draw();
			drawToScreen();
      elapsedTime = System.nanoTime()- startTime;
      waitTime = delay - elapsedTime;
      if(waitTime < 0){
        waitTime  = delay;
      }
      try{
        thread.sleep(delay);
      }catch(Exception e){
        e.printStackTrace();
      }
    }
  }

  private void init() {
    image = new BufferedImage( Const.WIDTH, Const.HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		running = true;
		gsm = new GameStateManager();
	}

	private void update() {
		gsm.update();
	}

	private void draw() {
		gsm.draw(g);
	}

	private void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, Const.WIDTH , Const.HEIGHT , null);
		g2.dispose();
	}

  private void drawStringTitle(Graphics2D g){
    Color titleColor;
    Font titleFont;
    titleFont = new Font( "Century Gothic", Font.PLAIN, 28);
    g.setColor(Color.RED);
		g.setFont(titleFont);
		g.drawString("Dragon Tale", 80, 70);

  }

	private void drawPoints(Graphics2D g){
			Path2D polyline =  new Path2D.Double();
			g.setPaint(Color.WHITE );
      int ax,ay;
			for(ay=0;ay<Const.HEIGHT;ay+=2){
        polyline.moveTo (0, ay);
        polyline.lineTo (0,Const.WIDTH );
			}
      for(ax = 0;ax<Const.WIDTH;ax+=2){
        polyline.moveTo (ax, 0);
        polyline.lineTo (ax,Const.HEIGHT);
      }
			g.setPaint( new GradientPaint( 0,0, Color.BLACK, Const.HEIGHT,Const.WIDTH, Color.RED, true ) );
			g.draw(polyline);
	}

	private void drawSinusLine(Graphics2D g){ 
			Path2D polyline =  new Path2D.Double();
			int ax,ay;

			for(int i=0;i<Const.HEIGHT;i+=2){
				polyline.moveTo (0, i);
				for(ax = 0;ax<Const.WIDTH;ax++){
					polyline.lineTo (ax,f(ax/100.0)*100 +  i);
				}
				g.draw(polyline);
			}
			g.setColor(Color.WHITE);
	}   

  double f(double x) {
        return Math.sin(x);
  }

	public void keyTyped(KeyEvent key) {
  
  }

	public void keyPressed(KeyEvent key) {
		gsm.keyPressed(key.getKeyCode());
  }

	public void keyReleased(KeyEvent key) {
		gsm.keyReleased(key.getKeyCode());
  }

  /*
   * nie działa repaint 
	@Override
	public void paintComponent(Graphics g) {
			super.paintComponent(g);
	}
  */

}  
