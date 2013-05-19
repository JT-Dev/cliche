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
    private Camera camera;
    
    public Main()
    {
	super(Constants.GAME_NAME);
    }

    @Override
    public void init(GameContainer gc) throws SlickException
    {
	input = gc.getInput();
	camera = new Camera(gc.getWidth(), gc.getHeight());
	world = new World(camera, Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException
    {
	world.update(delta, input);
    }

    public void render(GameContainer gc, Graphics g) throws SlickException
    {
	world.render(gc, g);
    }
    
    public static void main(String[] args) throws SlickException
    {
	AppGameContainer app = new AppGameContainer(new Main());
	app.setTargetFrameRate(60);
	app.setVSync(true);
	app.setSmoothDeltas(true);
	app.setDisplayMode(Constants.START_SCREEN_WIDTH, Constants.START_SCREEN_HEIGHT, false);
	app.start();
    }
}