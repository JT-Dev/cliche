package com.jtdev.thegame;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Main extends BasicGame
{
    private Input input;
    private World world;
    
    public Main()
    {
	super(Constants.GAME_NAME);
    }

    @Override
    public void init(GameContainer gc) throws SlickException
    {
	input = gc.getInput();
	world = new World();
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException
    {
	world.update(gc, delta, input);
    }

    public void render(GameContainer gc, Graphics g) throws SlickException
    {
	world.draw(gc, g);
    }
    
    public static void main(String[] args) throws SlickException
    {
	AppGameContainer app = new AppGameContainer(new Main());
	app.setTargetFrameRate(60);
	app.setVSync(false);
	app.setSmoothDeltas(true);
	app.setDisplayMode(800, 600, false);
	app.start();
    }
}