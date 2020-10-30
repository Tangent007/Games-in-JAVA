package bricBreaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import javax.swing.JPanel;

public class GamePlay extends JPanel implements KeyListener, ActionListener {
	
	private boolean play=false;
	private int score=0;
	
	private int totalbricks=21;
	
	private Timer timer;
	private int delay=8;
	
	private int playerX=310;
	
	private int ballposX=120;
	private int ballposY=350;
	private int balldirX=-1;
	private int balldirY=-2;
	
	public GamePlay() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
		 
	}
	
	public void paint(Graphics g) {
		// background
		g.setColor(Color.black);
		g.fillRect(1,1,692,592);
		
		// borders
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(681, 0, 3, 592);
		
		// the paddle
		g.setColor(Color.green);
		g.fillRect(playerX, 550, 100, 8);
		
		// ball
		g.setColor(Color.yellow);
		g.fillOval(ballposX, ballposY, 20, 20);
		
		g.dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		timer.start();
		
		if(play) {
			if(new Rectangle(ballposX, ballposY,20,20).intersects(new Rectangle(playerX,550,100,8))) {
				balldirY = -balldirY; 
			}
			
			ballposX+= balldirX;
			ballposY+= balldirY;
			if(ballposX<0) {
				balldirX = -balldirX;
			}
			if(ballposY<0) {
				balldirY = -balldirY;
			}
			if(ballposX>670) {
				balldirX = -balldirX;
			}
		}
		repaint();
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(playerX>=600) {
				playerX=600;
			}
			else {
				moveright();
			}
			
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(playerX<10) {
				playerX=10;
			}
			else {
				moveleft();
			}
		}
	}
	
	public void moveright() {
		play=true;
		playerX+=20;
		
	}
	public void moveleft() {
		play=true;
		playerX-=20;
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}

	

}
