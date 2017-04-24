package com.platformer01.threadkey;

import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Color;

public class Circle{


    private final int B_WIDTH = 640;
    private final int B_HEIGHT = 480;

    private int dx;
    private int dy;
    private int r;

    private int cx;
    private int cy;



    public Circle(){ }
    
    double f(double x) {
          return Math.sin(x);
    }

    public void move() {
				cx += dx;
				cy = (int)(f(cx/100.0)*100 + 240);
				if(cx>B_WIDTH){
					cx=0;
				}
				if(cx<0){
					cx=B_WIDTH;
				}
    }

    public void draw(Graphics g){
				g.setColor(Color.RED);
				g.fillOval(cx-10,cy-10,20,20);
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -3; 
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 3;
        }

    }

    public void keyReleased(KeyEvent e) {
        
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

    }
}

