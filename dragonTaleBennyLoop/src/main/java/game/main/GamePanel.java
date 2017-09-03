package game.main;

import game.gamestate.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

  public static final double FRAME_CAP = 5000.0;//how many updates per second - phisics engine
	
	// dimensions
	public static final int WIDTH = 320;
	public static final int HEIGHT = 240;
	public static final int SCALE = 2;
	
	// game thread
	private Thread thread;
	private boolean running;
	private int FPS = 60;
	private long targetTime = 1000 / FPS;
	
	// image
	private BufferedImage image;
	private Graphics2D g;
	
	// game state manager
	private GameStateManager gsm;

  private Input input;
	
	public GamePanel() {
		super();
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setFocusable(true);
		requestFocus();
    this.input = new Input();
	}
	
	public void addNotify() {
		super.addNotify();
		if(thread == null) {
			thread = new Thread(this);
			addKeyListener(input);
			thread.start();
		}
	}
	
	private void init() {
		image = new BufferedImage( WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		running = true;
		gsm = new GameStateManager(input);
  }
	
	public void run() {
		init();
    long frameCounter = 0;
    int frames = 0;

    final double frameTime = 1.0/FRAME_CAP;//the amount of time one frame takes; 1/5000 => 2/10000 => 0.0002s - one frame of phisics.
    long lastTime = Time.getTime();//time when previous frame start to draw
    double unprocessedTime = 0;

    boolean render ;
    long startTime ;
    long passedTime ;//time - how long the previous frame took
    while(running){
      render = false;
      startTime = Time.getTime();
      passedTime = startTime - lastTime;//time - how long the previous frame took
      lastTime = startTime;

      unprocessedTime += passedTime / (double)Time.SECOND;
      frameCounter += passedTime;

      /* now the phisics engine try to catch up the real time */
      while(unprocessedTime > frameTime){

        render = true;
        unprocessedTime -= frameTime;
        input.update();
        update();

        if(frameCounter >= Time.SECOND){
          input.printCurrentKeys();
          System.out.println(frames);
          frames = 0;
          frameCounter = 0;
        }

      }

      /* this loop adpots - no need to sleep for long time*/
      /* depends on FRAME_CAP - phisical engine frame rate and how long drawing takes */
      if(render){
        draw();
        drawToScreen();
        frames++;
      }else{
        try{
          Thread.sleep(1);
        }catch(InterruptedException e){
          e.printStackTrace();
        }
      }

    }
		
	}
	
	private void update() {
		gsm.update();
	}
	private void draw() {
		gsm.draw(g);
	}
	private void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		g2.dispose();
	}
	

}

