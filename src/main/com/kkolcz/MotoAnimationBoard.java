package com.kkolcz;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MotoAnimationBoard extends JPanel implements Runnable{

    private final int B_WIDTH = 350;
    private final int B_HEIGHT = 350;
    private final int INITIAL_X = -40;
    private final int INITIAL_Y = -40;
    private final int DELAY = 25;

    private Timer timer;
    /* private Image star; */
    private MotoBackground motoBackground;
    private Thread animator;
    private int x, y;

    public MotoAnimationBoard() {
        initBoard();
    }

    private void loadImage() {
        /* ImageIcon ii = new ImageIcon("com/kkolcz/star.png"); */
        /* star = ii.getImage(); */
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        motoBackground = new MotoBackground();
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        /* setDoubleBuffered(true); */
        /* loadImage(); */
        /* x = INITIAL_X; */
        /* y = INITIAL_Y; */
        /* timer = new Timer(DELAY, this); */
        /* timer.start();         */
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
        drawBackground(g);
    }

    private void drawBackground(Graphics g) {
        /* g.drawImage(star, x, y, this); */
        int i=0;
        for(i=motoBackground.getY();i<B_HEIGHT;i=i+10){
            g.drawLine(0,i,B_WIDTH,i);
        }
        for(i=motoBackground.getX();i<B_WIDTH;i=i+10){
            g.drawLine(i,0,i,B_HEIGHT);
        }
        
        Toolkit.getDefaultToolkit().sync();
    }

    private void cycle() {
        motoBackground.move();
        /* x += 1; */
        /* y += 1; */
        /* if (y > B_HEIGHT) { */
        /*     y = INITIAL_Y; */
        /*     x = INITIAL_X; */
        /* } */
    }

    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;
        beforeTime = System.currentTimeMillis();
        while (true) {
            cycle();
            repaint();
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff; /* aby opóźnienie każdego cyklu trwało tyle samo, niezależnie od tego, jak długo będzier trwało rysowanie */
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
            motoBackground.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            motoBackground.keyPressed(e);
        }
    }
}
