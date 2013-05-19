package com.jtdev.thegame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class World
{
    private Player player;
    private Terrain terrain;
    private Camera camera;
    private int width, height;
    private Image background;
 
    public World(Camera camera, int width, int height) throws SlickException
    {
	this.camera = camera;
	this.width = width;
	this.height = height;
	player = new Player();
	terrain = new Terrain(this);
	background = new Image("res/background-night.jpg");
    }
    
    public void render(GameContainer gc, Graphics g)
    {
	float bx = (float) camera.getX();
	float by = (float) camera.getY();
	
	background.draw(-bx, -by, width / background.getHeight());
	player.render(camera, gc);
    }
    
    public void update(int delta, Input input)
    {
	player.update(this, delta, input);
	camera.update(player);
    }
    
    public int getHeight()
    {
	return height;
    }
    
    public int getWidth()
    {
	return width;
    }
    
    public void setHeight(int height)
    {
	this.height = height;
    }
    
    public void setWidth(int width)
    {
	this.width = width;
    }
}