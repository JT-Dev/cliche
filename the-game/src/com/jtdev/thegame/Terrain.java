package com.jtdev.thegame;

import org.newdawn.slick.geom.Polygon;

public class Terrain
{
    Polygon base;
    public Terrain(World world)
    {
	base = generate_contour(Constants.NOISE_LEVEL);
    }
    
    private Polygon generate_contour(int level)
    {
	base = new Polygon();
	return base;
    }
}
