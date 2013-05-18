package com.jtdev.thegame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player extends Image
{
    private double x, x_vel, y, y_vel;
    private boolean in_air; 
    
    public Player() throws SlickException
    {
	super("res/player.png");
	x = y = 20.0;
	x_vel = y_vel = 0.0;
	in_air = false;
    }

    public void update(GameContainer gc, int delta, Input input)
    {
	double d = delta * (Constants.PLAYER_SPEED / 100.0);
	if((input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP) || input.isKeyDown(Input.KEY_SPACE)) && !in_air) y_vel -= d;
	if(input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_DOWN)) y_vel += d;
	if(input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_LEFT)) x -= d;
	if(input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT)) x += d;
	
	if(in_air) y_vel += delta * Constants.GRAVITY / 100.0;
	
	if(y_vel > Constants.TERMINAL_VELOCITY) y_vel = Constants.TERMINAL_VELOCITY;
	if(x_vel > Constants.TERMINAL_VELOCITY) x_vel = Constants.TERMINAL_VELOCITY;
	
	y += y_vel;
	x += x_vel;
	
	in_air = y < (gc.getHeight() - height);
	
	if(y < 0 || y > (gc.getHeight() - height)) {
	    y = y > 0 ? gc.getHeight() - height : 0;
	    y_vel = 0;
	}
	
	if(x < 0 || x > (gc.getWidth() - width)) {
	    x = x > 0 ? gc.getWidth() - width : 0;
	    x_vel = 0;
	}
    }
    
    @Override
    public void draw()
    {
        super.draw((float) x, (float) y);
    }
    
    public double getX()
    {
	return x;
    }
    
    public double getY()
    {
	return y;
    }
    
    public void setX(int x)
    {
	this.x = x;
    }
    
    public void setY(int y)
    {
	this.y = y;
    }
}
