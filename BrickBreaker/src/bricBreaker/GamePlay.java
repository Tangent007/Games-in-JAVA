package bricBreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
	
	private int ballposX=300;
	private int ballposY=370;
	private int balldirX = 1;
	private int balldirY = 2;
	
	private MapGenrator map;
	
	public GamePlay() {
		map = new MapGenrator(3,7);
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
		
		// drawing map
		map.draw((Graphics2D)g);
		
		
		// scores
		g.setColor(Color.white);
		g.setFont(new Font("serif",Font.BOLD,25));
		g.drawString(""+score, 590, 30);
		
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
		
		if(totalbricks<=0) {
			play = false;
			balldirX=0;
			balldirY=0;
			g.setColor(Color.red);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("You Won", 190, 300);
			
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("Press enter to restart", 230, 350);
		}
		
		if(ballposY>570) {
			play = false;
			balldirX=0;
			balldirY=0;
			g.setColor(Color.red);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("Game Over", 190, 300);
			
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("Press enter to restart", 230, 350);
			
		}
		
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
			
			A:for(int i=0;i<map.map.length;i++) {
				for(int j=0;j<map.map[0].length;j++) {
					if(map.map[i][j]>0) {
						int brickx=j*map.brickwidth+80;
						int bricky=i*map.brickheight+50;
						int brickwidth = map.brickwidth;
						int brickheight = map.brickheight;
						
						Rectangle rect = new Rectangle(brickx,bricky,brickwidth,brickheight);
						Rectangle ballrect = new Rectangle(ballposX,ballposY,20,20);
						Rectangle brickrect = rect;
						if(ballrect.intersects(brickrect)) {
							map.setbrickvalue(0, i, j);
							totalbricks --;
							score+=5;
							
							if(ballposX+19<=ballrect.x || ballposY+1 >= brickrect.x + brickrect.width) {
								balldirX = -balldirX;
							}else {
								balldirY = -balldirY;
							}
							
							break A;
						}
					}
				}
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
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(playerX<10) {
				playerX=10;
			}
			else {
				moveleft();
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			if(!play) {
				play=true;
				ballposX = 300;
				ballposY = 350;
				balldirX = 1;
				balldirY = 2;
				playerX = 310;
				totalbricks = 21;
				map = new MapGenrator(3,7);
				
				repaint();
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
