package com.kkolcz;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BoardImage extends JPanel {

    private Image bardejov;

    public BoardImage() {

        initBoard();
    }
    
    private void initBoard() {
        
        loadImage();
        
        int w = bardejov.getWidth(this);
        System.out.println("width:" + w);
        int h =  bardejov.getHeight(this);
        System.out.println("height:" + h);
        setPreferredSize(new Dimension(w, h));        
    }
    
    private void loadImage() {
        
        ImageIcon ii = new ImageIcon("com/kkolcz/ship.jpg");
        bardejov = ii.getImage();        
    }

    @Override
    public void paintComponent(Graphics g) {

        g.drawImage(bardejov, 0, 0, null);
    }
}

