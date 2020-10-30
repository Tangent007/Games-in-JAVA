package bricBreaker;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame jf = new JFrame();
		GamePlay gameplay = new GamePlay();
		jf.setBounds(10,10,700,600);
		jf.setResizable(false);
		jf.setVisible(true);
		jf.setTitle("Brick Breaker");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(gameplay);
		
	}

}
