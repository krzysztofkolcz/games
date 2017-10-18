package game;

import javax.swing.JFrame;

public class Start{
	
	public static void main(String[] args) {
		JFrame window = new JFrame("Loop01");
		window.setContentPane(new Loop01());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
	}
	
}


