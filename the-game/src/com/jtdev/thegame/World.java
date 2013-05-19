package com.jtdev.thegame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class World
{
    private Player player;
    
    public World() throws SlickException
    {
	player = new Player();
    }
    
    public void draw(GameContainer gc, Graphics g)
    {
	player.draw();
    }
    
    public void update(GameContainer gc, int delta, Input input)
    {
	player.update(gc, delta, input);
    }
}
