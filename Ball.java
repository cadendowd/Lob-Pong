/*
 *  Name: Caden Dowd 
 *  NetID: 31320610
 *  Assignment No.: Project 4
 *  Lab MW 2-3:15PM
 *  I did not collaborate with anyone on this assignment.
 */

//creates a Ball object
public class Ball {
	double xPos, yPos;
	double xVel, yVel;
	int height, width;
	
	public Ball() {
	}
	
	public Ball(double xPos, double yPos, double velocity, int size, double angle) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.xVel = velocity * Math.cos(Math.toRadians(angle));
		this.yVel = velocity * Math.sin(Math.toRadians(angle));
		height = size;
		width = size;
	}
		
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public double getxPos() {
		return xPos;
	}
	public void setxPos(double xPos) {
		this.xPos = xPos;
	}
	public double getyPos() {
		return yPos;
	}
	public void setyPos(double yPos) {
		this.yPos = yPos;
	}
	public double getxVel() {
		return xVel;
	}
	public void setxVel(double xVel) {
		this.xVel = xVel;
	}
	public double getyVel() {
		return yVel;
	}
	public void setyVel(double yVel) {
		this.yVel = yVel;
	}
	
}
