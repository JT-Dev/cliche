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
    private Player player;
    
    public Main()
    {
	super(Constants.GAME_NAME);
    }

    @Override
    public void init(GameContainer gc) throws SlickException
    {
	input = gc.getInput();
	player = new Player();
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException
    {
	player.update(gc, delta, input);
    }

    public void render(GameContainer gc, Graphics g) throws SlickException
    {
	player.draw();
    }
    
    public static void main(String[] args) throws SlickException
    {
	AppGameContainer app = new AppGameContainer(new Main());
	app.setTargetFrameRate(120);
	app.setVSync(true);
	app.setDisplayMode(800, 600, false);
	app.start();
    }
}