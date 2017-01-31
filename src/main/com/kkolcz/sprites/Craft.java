package com.kkolcz.sprites;

import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.util.ArrayList;

public class Craft extends Sprite{

    private int dx;
    private int dy;
    /* private int x; */
    /* private int y; */
    private Image image;
		private ArrayList missiles;

    public Craft(int x,int y) {
				super(x,y);
        initCraft();
    }
    
    private void initCraft() {
				missiles = new ArrayList();
        ImageIcon ii = new ImageIcon("com/kkolcz/rocket3.png");
        image = ii.getImage();
        /* x = 40; */
        /* y = 60;         */
    }


    public void move() {
        x += dx;
        y += dy;
    }

		public ArrayList getMissiles() {
				return missiles;
		}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            fire();
        }

        if (key == KeyEvent.VK_LEFT) {
            dx = -3;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 3;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -3;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 3;
        }
    }

    public void fire() {
        missiles.add(new Missile(x + width, y + height / 2));
    }

    public void keyReleased(KeyEvent e) {
        
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
}
