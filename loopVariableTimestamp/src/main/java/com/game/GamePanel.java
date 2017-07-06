package com.game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.JPanel;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;

import com.game.state.*;

public class GamePanel extends JPanel implements Runnable, KeyListener{

	private Thread thread;
	private boolean running;

	private int FPS = 60;
	private long delay = 1000 / FPS; /* sleep for 1/60 of the second */

  public static final int FIX = 1;
	public static final int LINEWIDTH = 80 ;
	public static final int WIDTH = LINEWIDTH + FIX;

	public static final int WINDOWWIDTH = 120 + FIX;
	public static final int HEIGHT = 240;
	public static final int SCALE = 2;

  GameStateManager gsm;

	private BufferedImage image;
	private Graphics2D g;

  public GamePanel(){
		super();
		setPreferredSize(new Dimension(WINDOWWIDTH * SCALE , HEIGHT * SCALE ));
		setFocusable(true);
		requestFocus();
  }

  public void addNotify(){
    super.addNotify();
    if(thread == null){
      thread = new Thread(this);
      addKeyListener(this);
      thread.start();
    }
  }

  public void run(){
    init();

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
        waitTime = 0;
      }try{
        thread.sleep(delay);
      }catch(Exception e){
        e.printStackTrace();
      }
    }
  }

  private void init() {
    image = new BufferedImage( WINDOWWIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		running = true;
		gsm = new GameStateManager();
    gsm.setState(0);
	}

	private void update() {
		gsm.update();
	}

	private void draw() {
		gsm.draw(g);
	}

	private void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, WINDOWWIDTH * SCALE , HEIGHT * SCALE , null);
		g2.dispose();
	}

	public void keyTyped(KeyEvent key) {
  }

	public void keyPressed(KeyEvent key) {
		gsm.keyPressed(key.getKeyCode());
  }

	public void keyReleased(KeyEvent key) {
		gsm.keyReleased(key.getKeyCode());
  }
}
