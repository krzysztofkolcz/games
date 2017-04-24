package com.platformer01.threadkey;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Polygon;

import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GamePanel extends JPanel
        implements Runnable, KeyListener{

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

    private Circle circle;

    public GamePanel() {
        super(); /* Potrzebne aby KeyListener działał! */
        initGamePanel();
    }


    public void keyTyped(KeyEvent key) {
      System.out.println("key typed");
    }
    public void keyPressed(KeyEvent key) {
      System.out.println("key pressed");
    }
    public void keyReleased(KeyEvent key) {
      System.out.println("key released");
    }

    private void initGamePanel() {
        circle = new Circle();
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setDoubleBuffered(true);

        setFocusable(true);
        requestFocus();

        x = INITIAL_X;
        y = INITIAL_Y;
				ax = 0;
				ay = 0;
    }

    @Override
    public void addNotify() {
        super.addNotify();
        animator = new Thread(this);
        /* addKeyListener(this); *//* również działa - jeżeli w klasie metody keyTyped, keyPressed, keyReleased */
        addKeyListener(new TAdapter());
        animator.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawSinusLine(g);
        drawSinusCircle(g);
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
      circle.draw(g);
		}

    public void update(){
      circle.move();
    }

    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;
        beforeTime = System.currentTimeMillis();
        while (true) {
            update();
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

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
          /* System.out.println("key released"); */
          circle.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
          /* System.out.println("key pressed"); */
          circle.keyPressed(e);
        }
    }
}

