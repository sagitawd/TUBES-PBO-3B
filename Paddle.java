package gamePong;

import java.awt.*;
import java.awt.event.*;

public class Paddle extends Rectangle{

	int id;
	int kecepatanY;
	int kecepatan = 10;
	
	Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id){
		super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
		this.id=id;
	}
	
	public void keyPressed(KeyEvent e) {
		switch(id) {
		case 1:
			if(e.getKeyCode()==KeyEvent.VK_W) {
				setYDirection(-kecepatan);
			}
			if(e.getKeyCode()==KeyEvent.VK_S) {
				setYDirection(kecepatan);
			}
			break;
		case 2:
			if(e.getKeyCode()==KeyEvent.VK_UP) {
				setYDirection(-kecepatan);
			}
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				setYDirection(kecepatan);
			}
			break;
		}
	}
	public void keyReleased(KeyEvent e) {
		switch(id) {
		case 1:
			if(e.getKeyCode()==KeyEvent.VK_W) {
				setYDirection(0);
			}
			if(e.getKeyCode()==KeyEvent.VK_S) {
				setYDirection(0);
			}
			break;
		case 2:
			if(e.getKeyCode()==KeyEvent.VK_UP) {
				setYDirection(0);
			}
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				setYDirection(0);
			}
			break;
		}
	}
	public void setYDirection(int yDirection) {
		kecepatanY = yDirection;
	}
	public void move() {
		y= y + kecepatanY;
	}
	public void draw(Graphics g) {
		if(id==1)
			g.setColor(Color.black);
		else
			g.setColor(Color.red);
		g.fillRect(x, y, width, height);
	}
}
