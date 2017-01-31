package com.platformer01.threadkey;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Game extends JFrame{

	public Game(){
		add(new GamePanel());
		setResizable(false);
		pack();
		setTitle("Platformer01");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run(){
				Game game = new Game();
				game.setVisible(true);
			}
		});
	}

}


