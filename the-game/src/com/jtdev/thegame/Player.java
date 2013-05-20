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
	in_air = true;
    }

    public void update(World world, int delta, Input input)
    {
	/* Input Detection */
	in_air = y < (world.getHeight() - height);
	if((input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP) || input.isKeyDown(Input.KEY_SPACE)) && !in_air)
	    y_vel -= Constants.PLAYER_YSPEED;
	//if(input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_DOWN)) y_vel += d; /* Who the F**K has ever wanted a down key? */
	if(input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_LEFT)) x_vel -= Constants.PLAYER_XSPEED;
	if(input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT)) x_vel += Constants.PLAYER_XSPEED;

	/* Physics (thanks to @cyphar) */
	if(in_air) y_vel += Constants.GRAVITY;
	x_vel *= 1.0 - (in_air ? Constants.AIR_RESISTANCE : Constants.GROUND_FRICTION);
	
	if(Math.abs(y_vel) > Constants.TERMINAL_SPEED) y_vel = (y_vel < 0 ? -1 : 1) * Constants.TERMINAL_SPEED;
	if(Math.abs(x_vel) > Constants.TERMINAL_SPEED) x_vel = (x_vel < 0 ? -1 : 1) * Constants.TERMINAL_SPEED;
	
	y += y_vel * delta;
	x += x_vel * delta;
	
	/* Collision Detection */
	if(y < 0 || y > (world.getHeight() - height)) {
	    in_air = false;
	    y = y > 0 ? world.getHeight() - height : 0;
	    y_vel = 0;
	}
	
	if(x < 0 || x > (world.getWidth() - width)) {
	    x = x > 0 ? world.getWidth() - width : 0;
	    x_vel = 0;
	}
    }
    
    public void render(Camera camera, GameContainer gc)
    {
	float xd = (float) camera.toCameraX(x, gc);
	float yd = (float) camera.toCameraY(y, gc);
        draw(xd, yd);
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
