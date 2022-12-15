package gamePong;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{

	static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
	static final int BALL_DIAMETER = 20;
	static final int PADDLE_WIDTH = 25;
	static final int PADDLE_HEIGHT = 100;
	static int SleepTime = 10;
	Thread gameThread;
	Image gambar;
	Graphics grafik;
	Random random;
	Paddle paddle1;
	Paddle paddle2;
	Bola bola;
	Skor skor;
	
	public GamePanel(){
		newPaddles();
		newBall();
		skor = new Skor(GAME_WIDTH,GAME_HEIGHT);
		this.setFocusable(true);
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void newBall() {
		random = new Random();
		bola = new Bola((GAME_WIDTH/2)-(BALL_DIAMETER/2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER);
	}
	public void newPaddles() {
		paddle1 = new Paddle(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
		paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);
	}
	public void paint(Graphics g) {
		gambar = createImage(getWidth(),getHeight());
		grafik = gambar.getGraphics();
		draw(grafik);
		g.drawImage(gambar,0,0,this);
	}
	public void draw(Graphics g) {
		paddle1.draw(g);
		paddle2.draw(g);
		bola.draw(g);
		skor.draw(g);
Toolkit.getDefaultToolkit().sync(); // it helps with the animation

	}
	public void move() {
		paddle1.move();
		paddle2.move();
		bola.move();
	}
	public void checkCollision() {
		
		//bounce bola off top & bottom window edges
		if(bola.y <=0) {
			bola.setYDirection(-bola.kecepatanY);
		}
		if(bola.y >= GAME_HEIGHT-BALL_DIAMETER) {
			bola.setYDirection(-bola.kecepatanY);
		}
		//bounce bola off paddles
		if(bola.intersects(paddle1)) {
			bola.kecepatanX = Math.abs(bola.kecepatanX);
			bola.kecepatanX++; //optional for more difficulty
			if(bola.kecepatanY>0)
				bola.kecepatanY++; //optional for more difficulty
			else
				bola.kecepatanY--;
			bola.setXDirection(bola.kecepatanX);
			bola.setYDirection(bola.kecepatanY);
		}
		if(bola.intersects(paddle2)) {
			bola.kecepatanX = Math.abs(bola.kecepatanX);
			bola.kecepatanX++; //optional for more difficulty
			if(bola.kecepatanY>0)
				bola.kecepatanY++; //optional for more difficulty
			else
				bola.kecepatanY--;
			bola.setXDirection(-bola.kecepatanX);
			bola.setYDirection(bola.kecepatanY);
		}
		//stops paddles at window edges
		if(paddle1.y<=0)
			paddle1.y=0;
		if(paddle1.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
			paddle1.y = GAME_HEIGHT-PADDLE_HEIGHT;
		if(paddle2.y<=0)
			paddle2.y=0;
		if(paddle2.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
			paddle2.y = GAME_HEIGHT-PADDLE_HEIGHT;
		//give a player 1 point and creates new paddles & bola
		if(bola.x <=0) {
			skor.pemain2++;
			newPaddles();
			newBall();
			System.out.println("Player 2: "+skor.pemain2);
		}
		if(bola.x >= GAME_WIDTH-BALL_DIAMETER) {
			skor.pemain1++;
			newPaddles();
			newBall();
			System.out.println("Player 1: "+skor.pemain1);
		}
	}
	public void run() {
		//game loop
		long lastTime = System.nanoTime();
		double amountOfTicks =60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		while(true) {
			long now = System.nanoTime();
			delta += (now -lastTime)/ns;
			lastTime = now;
			if(delta >=1) {
				move();
				checkCollision();
				repaint();
				delta--;
			}
			try {
    			Thread.sleep(SleepTime);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
		}
	}
	public class AL extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
		}
		public void keyReleased(KeyEvent e) {
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);
		}
	}
}