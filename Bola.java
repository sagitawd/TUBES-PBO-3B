package gamePong;

import java.awt.*;
import java.util.*;

public class Bola extends Rectangle{

	Random random;
	int kecepatanX;
	int kecepatanY;
	int kecepatanAwal = 2;
	
	Bola(int x, int y, int width, int height){
		super(x,y,width,height);
		random = new Random();
		int randomXDirection = random.nextInt(2);
		if(randomXDirection == 0)
			randomXDirection--;
		setXDirection(randomXDirection*kecepatanAwal);
		
		int randomYDirection = random.nextInt(2);
		if(randomYDirection == 0)
			randomYDirection--;
		setYDirection(randomYDirection*kecepatanAwal);
		
	}
	
	public void setXDirection(int randomXDirection) {
		kecepatanX = randomXDirection;
	}
	public void setYDirection(int randomYDirection) {
		kecepatanY = randomYDirection;
	}
	public void move() {
		x += kecepatanX;
		y += kecepatanY;
	}
	public void draw(Graphics g) {
		g.setColor(Color.gray);
		g.fillOval(x, y, height, width);
	}
}
