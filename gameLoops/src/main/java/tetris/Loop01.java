package tetris;

import javax.print.attribute.standard.MediaSize;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Loop01 extends JPanel
		implements Runnable, KeyListener {

	// dimensions
	public static final int WIDTH = 320;
	public static final int HEIGHT = 240;
	public static final int SCALE = 2;
	public static final long MS_PER_UPDATE = 5; //! ou need to make sure the time step is greater than the time it takes to process an update()
	public static final long NANOSECONDS_IN_SECOND = 1000000000;
  /*
  MS_PER_UPDATE But be careful not to make it too short. 
  You need to make sure the time step is greater than the time it takes to process an update(),
  even on the slowest hardware. Otherwise, your game simply can’t catch up. 
  */

	// game thread
	private Thread thread;
	private boolean running;

	// image
	private BufferedImage image;
	private Graphics2D g;

	// game state manager
	private GameStateManager gsm;

	public Loop01() {
		super();
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setFocusable(true);
		requestFocus();
	}

	public void addNotify() {
		super.addNotify();
		if (thread == null) {
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	}

	private void init() {
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		running = true;
		gsm = new GameStateManager();
	}

	public void run() {
		init();

		long current;/* startTime in Benny */
		long elapsed;/* passedTime in Benny */
		long totalElapsed = 0;
        double totalSecondsElapsed = 0;
		long secondAccumulator = 0;
		long previous = System.nanoTime();/* lastTime in Benny */
		long lag = 0;/* unprocessedTime in Benny */

		long wait;
		boolean render = false;
		long updateCounter = 0;
		long frameCounter = 0;

		// game loop
		while (running) {
            /* frameTime = 1.0 / FRAME_CAP */
			current = System.nanoTime();/* startTime in Benny */
//			System.out.println("Current:"+current);
			elapsed = current - previous;/* passedTime in Benny */
//			System.out.println("Elapsed:"+elapsed);
			secondAccumulator += elapsed;
			totalSecondsElapsed += elapsed / 1000000000.0;

			previous = current;
            /* uzyskuje lag w milisekundach - czyli ile milisekund gra musi nadrobić za czasem rzeczywistym. */
			lag += elapsed / 1000000; /* unprocessedTime += passedTime/(double)Time.SECOND; //Time.SECOND = 1000000000 */

			//processInput();//tutaj pobieranie inputu nie wydaje się sensowne.

			while (lag >= MS_PER_UPDATE) {
				updateCounter++;
				render = true;
				gsm.update();
				Input.update();//na podstawie video: https://www.youtube.com/watch?v=pBK-lb-k-rs&index=4&list=PLEETnX-uPtBXP_B2yupUKlflXBznWIlL5
				lag -= MS_PER_UPDATE;
//				System.out.println("update:" + updateCounter + "; lag:" + lag);
			}

			if (render) {
				frameCounter++;
				draw();/*renderowanie powinno zabierać stały czas?*/
				drawToScreen();
				render = false;
//				System.out.println("frame:" + frameCounter);
			} else {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			if(secondAccumulator > NANOSECONDS_IN_SECOND) {
				secondAccumulator -= NANOSECONDS_IN_SECOND;
//				System.out.println("Total seconds elapsed:");
//				System.out.println(totalSecondsElapsed);
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

	public void keyTyped(KeyEvent key) {
	}

	public void keyPressed(KeyEvent key) {
		// gsm.keyPressed(key.getKeyCode());
		Input.setKeyPressed(key.getKeyCode());
	}

	public void keyReleased(KeyEvent key) {
		// gsm.keyReleased(key.getKeyCode());
		Input.setKeyReleased(key.getKeyCode());
	}

}
