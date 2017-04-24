package com.kkolcz;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class MotoAnimationExample extends JFrame {

    public MotoAnimationExample() {
        initUI();
    }
    
    private void initUI() {
        add(new MotoAnimationBoard());
        setResizable(false);
        pack();
        setTitle("Star");    
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {                
                JFrame ex = new MotoAnimationExample();
                ex.setVisible(true);                
            }
        });
    }
}
