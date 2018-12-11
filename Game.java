/*
 *  Name: Caden Dowd 
 *  NetID: 31320610
 *  Assignment No.: Project 4
 *  Lab MW 2-3:15PM
 *  I did not collaborate with anyone on this assignment.
 */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

//run here to run the game
public class Game extends JComponent implements KeyListener {

	private static final long serialVersionUID = 1L;
	JFrame frame;
	Canvas canvas;
	Timer timer;
	private int screenWidth = 800;
	private int screenHeight = 750;
	double dt = .2;
	
	
	Paddle paddle;
	Ball ball;
	Ball ball2;
	int t;
	int time = 30;
	int lives = 3;
	int score = 0;
	int level = 4; 
	
	public Game() {
		addObjects();
		init();
		createTimer();
	}
	
	public void addObjects() {
		ball = new Ball(screenWidth/2, 0, 18, 10, 30);
		ball2 = new Ball(screenWidth/2, 0, 18, 10, 45);
		paddle = new Paddle(screenWidth/2, screenHeight * 3/4 + 50, 5, 100, 50);
	}
	
	public void createTimer() {
		ActionListener gameUpdater = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	t++;
            	run();
            }
        };
		timer = new Timer(40,gameUpdater);
		timer.start();
	}
	
	public void init() {
		frame = new JFrame("Caden's Lob Pong");
		frame.setLayout(new BorderLayout());
		canvas = new Canvas();
		frame.setSize(screenWidth, screenHeight); 
	    frame.add(canvas, BorderLayout.CENTER); 
	    frame.setVisible(true);     
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setResizable(false); 
	    frame.addKeyListener(this);
	    frame.setFocusable(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
			paddle.setxPos(paddle.getxPos() - paddle.getxVel());
		}
		if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			paddle.setxPos(paddle.getxPos() + paddle.getxVel());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
	
	public void countDown() {
		if(t % 25 == 0) {
			time--;
		}
	}
	
	public void checkBound() {
		if(paddle.getxPos() > frame.getWidth()) {
			paddle.setxPos(0);
		}
		
		if(paddle.getxPos() + paddle.getWidth() < 0) {
			paddle.setxPos(frame.getWidth());
		}
	}
	
	public void changeVelocity(Ball ball) {
		ball.setyVel(ball.getyVel() + (int)(9.8 * dt));
		ball.setyPos(ball.getyPos() + (int)(ball.getyVel() * dt));
		ball.setxPos(ball.getxPos() + ball.getxVel() * dt);
		
		if((ball.getxPos() + ball.getWidth() > screenWidth)){
			ball.setxVel(ball.getxVel() * -1);
			System.out.println("right bound");
		}
		if((ball.getxPos() < 0) && (ball.getxVel() < 0)){
			ball.setxVel(ball.getxVel() * -1);
			System.out.println("left Bound");
		}
		if((ball.getyPos() >= screenHeight) && (ball.getyVel() > 0)){
			lives--; 
			ball.setxPos(frame.getWidth()/2);
			ball.setyPos(-100);
			ball.setyVel(18);
			System.out.println("bttom");
		}
		if((ball.getyPos() < 0) && (ball.getyVel() < 0)){
			ball.setyVel(ball.getyVel() * -1);
			System.out.println("ceiling");
		}
	}
	
	public void checkPaddle(Ball ball) {
		if((ball.getxPos() < paddle.getxPos() + paddle.getWidth() && ball.getxPos() > paddle.getxPos()) 
				&& (ball.getyPos() + ball.getHeight() >= paddle.getyPos())
				&& (ball.getyPos() + ball.getHeight() <= paddle.getyPos() + 40)
				&& ball.getyVel() > 0) {
			score++;
			ball.setyVel(ball.getyVel() * -1);
		}
	}
	
	public void run() {
		if(time == 0) {
			level++;
			time = 30;
		}
		if(level == 2 && time == 30) {
			ball.setyVel(ball.getyVel() + 2);
			ball.setxVel(ball.getxVel() + 2);
		}
		if(level == 3 && ball.getyPos() >= frame.getHeight()/4*3 - 80 && ball.getyPos() <= frame.getHeight()/4*3 - 50
				&& ball.getyVel() > 0) {
			ball.setxVel(ball.getxVel() * -1);
			System.out.println("check");
		}
		
		if(level == 4) {
			changeVelocity(ball2);
			checkPaddle(ball2);
		}
		
		if(lives == 0) {
			timer.stop();
		}
		
		changeVelocity(ball);
		checkPaddle(ball);
		
		checkBound();
		countDown();
		
		canvas.update(ball, paddle, score, lives, time, level, ball2);
	}
	
	
	public static void main(String[] args) {
		Game newGame = new Game();
	}
}
