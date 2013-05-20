package com.jtdev.thegame;

import org.newdawn.slick.GameContainer;

public class Camera
{
    private double x, y;
    private int width, height;

    public Camera(int width, int height)
    {
	this.width = width;
	this.height = height;
	x = (Constants.WORLD_WIDTH - width) / 2;
	y = (Constants.WORLD_HEIGHT - height) / 2;
    }
    
    public void set(double setx, double sety)
    {
	x = setx;
	y = sety;

	checkPos();
    }

    public void resize(double ratio)
    {
	double cx = x + width / 2;
	double cy = y + height / 2;

	width = (int) (width * ratio);
	height = (int) (height * ratio);

	x = cx - width / 2;
	y = cy - height / 2;
	checkPos();
    }

    public void update(Player player)
    {
	set(player.getX() - width / 2, player.getY() - height / 2);
    }

    private void checkPos()
    {
	if (x < 0) x = 0;
	if (y < 0) y = 0;
	if (x + width > Constants.WORLD_WIDTH) x = Constants.WORLD_WIDTH - width;
	if (y + height > Constants.WORLD_HEIGHT) y = Constants.WORLD_HEIGHT - height;
    }

    public int getHeight()
    {
	return height;
    }

    public int getWidth()
    {
	return width;
    }

    public double getX()
    {
	return x;
    }

    public double getY()
    {
	return y;
    }

    public double toCameraX(double x, GameContainer gc)
    {
	return ((x - this.x) * gc.getWidth() / this.width);
    }

    public double toCameraY(double y, GameContainer gc)
    {
	return ((y - this.y) * gc.getHeight() / this.height);
    }
}
