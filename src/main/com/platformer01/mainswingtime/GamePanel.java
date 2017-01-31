package com.platformer01.mainswingtime;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {

		private final int B_WIDTH = 640;
    private final int B_HEIGHT = 480;
		private final int DELAY = 15;
		private Timer timer;
		private boolean ingame;

		public GamePanel() {
        initGamePanel();
    }

    private void initGamePanel() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        ingame = true;
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (ingame) {
            drawObjects(g);
        } else {
            drawGameOver(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("Game",  5, 15);
    }

    private void drawGameOver(Graphics g) {
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }


   @Override
    public void actionPerformed(ActionEvent e) {
        inGame();
        repaint();
    }

    private void inGame() {
        if (!ingame) {
            timer.stop();
        }
    }


    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            /* craft.keyReleased(e); */
        }

        @Override
        public void keyPressed(KeyEvent e) {
            /* craft.keyPressed(e); */
        }
    }

}
