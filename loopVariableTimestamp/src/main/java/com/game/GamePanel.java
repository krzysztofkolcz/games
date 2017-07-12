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

	public static final int WIDTH = 320;
	public static final int HEIGHT = 240;
	public static final int SCALE = 2;
	public static final int TARGET_FPS = 60;

  GameStateManager gsm;

	private BufferedImage image;
	private Graphics2D g;
	private boolean gameRunning;

  public GamePanel(){
		super();
		setPreferredSize(new Dimension(WIDTH * SCALE , HEIGHT * SCALE ));
		setFocusable(true);
		requestFocus();
    init();
  }

  public void addNotify(){
    super.addNotify();
    if(thread == null){
      thread = new Thread(this);
      addKeyListener(this);
      thread.start();
    }
  }

  public void doGameUpdates(double delta){
		try{
			Thread.sleep(40);
		}catch(Exception e){
			e.printStackTrace();
		}
		update();
  }

  private void render(){
		draw();
		drawToScreen();
	}

  public void run(){

    long lastLoopTime = System.nanoTime();
    long tmpTime = 0;
    long lastFpsTime = 0;
		long fps = 0;
		long sleepTime = 0;
    final int TARGET_FPS = 60;
    final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;   

    while(gameRunning){
			// work out how long its been since the last update, this
      // will be used to calculate how far the entities should
      // move this loop
      long now = System.nanoTime();
      long updateLength = now - lastLoopTime;
      lastLoopTime = now;
			/* System.out.println("lastLoopTime: " + lastLoopTime); */
      double delta = updateLength / ((double)OPTIMAL_TIME);

			// update the frame counter
      lastFpsTime += updateLength;
      fps++;

      // update our FPS counter if a second has passed since
      // we last recorded
      if (lastFpsTime >= 1000000000)
      {
         System.out.println("(FPS: "+fps+")");
         lastFpsTime = 0;
         fps = 0;
      }

      // update the game logic
      doGameUpdates(delta);
      
      // draw everyting
      render();
      
      // we want each frame to take 10 milliseconds, to do this
      // we've recorded when we started the frame. We add 10 milliseconds
      // to this and then factor in the current time to give 
      // us our final value to wait for
      // remember this is in ms, whereas our lastLoopTime etc. vars are in ns.
      try{
				tmpTime = System.nanoTime();
				sleepTime = (lastLoopTime - tmpTime + OPTIMAL_TIME)/1000000;
				if(sleepTime < 0){
					System.out.println("lastLoopTime: " + lastLoopTime);
					System.out.println("now.....Time: " + tmpTime);
					System.out.println("sleep...Time: "+ sleepTime);
					System.out.println();
				}else{
					Thread.sleep(sleepTime);
				}
			}catch(Exception e){
				e.printStackTrace(); 
			}

    } 

  }

  private void init() {
		gameRunning = true;
    image = new BufferedImage( WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		g.setColor(Color.WHITE);
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
		g2.drawImage(image, 0, 0, WIDTH * SCALE , HEIGHT * SCALE , null);
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
