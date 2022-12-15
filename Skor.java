package gamePong;

import java.awt.*;

public class Skor extends Rectangle{

	static int GAME_WIDTH;
	static int GAME_HEIGHT;
	int pemain1;
	int pemain2;
	
	Skor(int GAME_WIDTH, int GAME_HEIGHT){
		Skor.GAME_WIDTH = GAME_WIDTH;
		Skor.GAME_HEIGHT = GAME_HEIGHT;
	}
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.setFont(new Font("Comic Sans MS",Font.PLAIN,60)); // Consolas
		
		g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
		
		g.drawString(String.valueOf(pemain1/10)+String.valueOf(pemain1%10), (GAME_WIDTH/2)-85, 50);
		g.drawString(String.valueOf(pemain2/10)+String.valueOf(pemain2%10), (GAME_WIDTH/2)+20, 50);
	}
}
