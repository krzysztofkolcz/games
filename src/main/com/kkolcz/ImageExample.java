package com.kkolcz;

import java.awt.EventQueue;
import javax.swing.JFrame;

import java.net.URL;
import java.net.URLClassLoader;

public class ImageExample extends JFrame {

    public ImageExample() {

        initUI();
    }

    private void initUI() {

        add(new BoardImage());

        pack();

        setTitle("Bardejov");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        URL[] urls = ((URLClassLoader)cl).getURLs();
        for(URL url: urls){
            System.out.println(url.getFile());
        }

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                ImageExample ex = new ImageExample();
                ex.setVisible(true);
            }
        });
    }
}
