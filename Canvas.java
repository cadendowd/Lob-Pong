/*
 *  Name: Caden Dowd 
 *  NetID: 31320610
 *  Assignment No.: Project 4
 *  Lab MW 2-3:15PM
 *  I did not collaborate with anyone on this assignment.
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JPanel;

//JPanel where all the graphics are drawn
public class Canvas extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private Ball ball;
	private Paddle paddle;
	private int score;
	private int lives;
	private int time;
	int highscore;
	int level;
	Ball ball2;
	
	public Canvas() {
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Font newFont1 = new Font("Gothic", Font.BOLD, 24);
		g.setFont(newFont1);
		if(level == 1) {
			g.drawString("Level 1", getWidth()/2 - 50, getHeight()/3);
		}
		
		if(level == 2) {
			g.drawString("Level 2 \nFaster!!", getWidth()/2 - 50, getHeight()/3);
		}
		
		if(level == 3) {
			g.drawString("Level 3 \nUH OH", getWidth()/2 - 50, getHeight()/3);
			g.drawLine(0, getHeight()/4 * 3 - 60, getWidth(), getHeight()/4 * 3 - 60);
		}
		if(level == 4) {
			g.drawString("Level 4 \nDouble", getWidth()/2 - 50, getHeight()/3);
			g.drawLine(0, getHeight()/4 - 60, getWidth(), getHeight()/4 - 60);
			g.fillOval((int)ball2.getxPos(), (int)ball2.getyPos(), ball2.getWidth(), ball2.getHeight());
		}
		
		Font newFont2 = new Font("Times New Roman", Font.BOLD, 12);
		g.setFont(newFont2);
		
		if(lives >= 0 && level > 4) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.white);
			Font newFont = new Font("Serif", Font.BOLD, 24);
			g.setFont(newFont);
			g.drawString("WINNER!", getWidth()/2 - 50, getHeight()/3);
			g.drawString("Your Score: " + score, getWidth()/2 - 50, getHeight()/3 + 50);
			setHighscore(score);
			g.drawString("Highscore: " + highscore, getWidth()/2- 50, getHeight()/3 + 100);
		} else if(lives > 0) {
			int ballHeight = getHeight() - 25;
			
			g.fillOval((int)ball.getxPos(), (int)ball.getyPos(), ball.getWidth(), ball.getHeight());
			g.setColor(Color.RED);
			g.fillRect((int)paddle.getxPos(), (int)paddle.getyPos(), paddle.getWidth(), paddle.getHeight());
			
			g.setColor(Color.BLUE);
			g.fillRect(0, getHeight() - 50, getWidth(), 50);
			g.setColor(Color.WHITE);
			g.drawString("Lives: ", 25, ballHeight);
			g.setColor(Color.BLACK);
			if(lives == 3) {
				g.fillOval(65, ballHeight - 10, 10, 10);
				g.fillOval(85, ballHeight - 10, 10, 10);
				g.fillOval(105, ballHeight - 10, 10, 10);
			}
			if(lives == 2) {
				g.fillOval(65, ballHeight - 10, 10, 10);
				g.fillOval(85, ballHeight - 10, 10, 10);
			}
			if(lives == 1) {
				g.fillOval(65, ballHeight - 10, 10, 10);
			}
			
			g.setColor(Color.WHITE);
			g.drawString("Score: " + score, getWidth() - 150, ballHeight);
			
			g.drawString("Time: " + time, getWidth()/2, ballHeight);
		}  else {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.white);
			Font newFont = new Font("Serif", Font.BOLD, 24);
			g.setFont(newFont);
			g.drawString("GAME OVER", getWidth()/2 - 50, getHeight()/3);
			g.drawString("Your Score: " + score, getWidth()/2 - 50, getHeight()/3 + 50);
			setHighscore(score);
			g.drawString("Highscore: " + highscore, getWidth()/2 - 50, getHeight()/3 + 100);
		} 
	}
	
	public void update(Ball b, Paddle p, int s, int l, int t, int lev, Ball b2) {
		ball = b;
		paddle = p;
		score = s;
		lives = l;
		time = t;
		level = lev;
		ball2 = b2;
		repaint();
	}
	
	public void setHighscore(int score) {
		FileReader fr;
		Scanner sc;
		try {
			fr = new FileReader("highscore.txt");
			sc = new Scanner(fr);
			highscore = sc.nextInt();
			if (score > highscore) {
				highscore = score;
				PrintWriter out = new PrintWriter("highscore.txt");
				out.println(highscore);
				out.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println("no file!");
		}
	}
}
