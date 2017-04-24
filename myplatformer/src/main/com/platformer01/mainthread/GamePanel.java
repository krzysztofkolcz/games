package com.platformer01.mainthread;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Polygon;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GamePanel extends JPanel
        implements Runnable {

    private final int B_WIDTH = 640;
    private final int B_HEIGHT = 480;
    private final int INITIAL_X = 320;
    private final int INITIAL_Y = 0;
    private final int DELAY = 25;

    private Image star;
    private Thread animator;
    private int x, y;
    private double ax, ay;
    private double circlex,circley;

    public GamePanel() {

        initGamePanel();
    }

    private void loadImage() {

        ImageIcon ii = new ImageIcon("com/platformer01/rocket3.png");
        star = ii.getImage();
    }

    private void initGamePanel() {

        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setDoubleBuffered(true);

        /* loadImage(); */
        /* drawSinusLine(); */

        x = INITIAL_X;
        y = INITIAL_Y;
				ax = 0;
				ay = 0;
    }

    @Override
    public void addNotify() {
        super.addNotify();

        animator = new Thread(this);
        animator.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        /* drawStar(g); */
        drawSinusLine(g);
        drawSinusCircle(g);
				/* drawSinus(g); */
    }

    private void drawStar(Graphics g) {

        g.drawImage(star, x, y, this);
        Toolkit.getDefaultToolkit().sync();
    }

    private void cycle() {

        x += 1;
        y += 1;

        if (y > B_HEIGHT) {

            y = INITIAL_Y;
            x = INITIAL_X;
        }
    }

    double f(double x) {
          return Math.sin(x);
    }


		private void drawSinusLine(Graphics g){
        Polygon p = new Polygon();
        int ax,ay;
				g.setColor(Color.WHITE);
        for(ax = 0;ax<640;ax++){
          p.addPoint(ax,(int)(f(ax/100.0)*100 + 240));
        }
        g.drawPolyline(p.xpoints, p.ypoints, p.npoints);
		}

		private void drawSinusCircle(Graphics g){
				circlex += 2;
				int cx = (int)circlex;
				int cy = (int)(f(circlex/100.0)*100 + 240);
				if(circlex>B_WIDTH){
					circlex=0;
				}
				g.setColor(Color.RED);
				g.fillOval(cx-10,cy-10,20,20);
		}

    @Override
    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (true) {

            /* cycle(); */
            repaint();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("Interrupted: " + e.getMessage());
            }

            beforeTime = System.currentTimeMillis();
        }
    }
}
