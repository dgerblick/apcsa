package mrstark;

import javax.swing.JFrame;

public class SnapViewer_DanielGerblick {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(1200, 780);
		frame.setTitle("i don't wanna go");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		SnapComponent_DanielGerblick c = new SnapComponent_DanielGerblick(); // Instantiate component
		frame.add(c); // Add component to frame
		frame.setVisible(true); // Set frame visible
	}

}
