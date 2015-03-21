package org.usfirst.frc.team2526.robot.commands.vision;

import java.util.Comparator;

public class ParticleReport implements Comparator<ParticleReport>, Comparable<ParticleReport>{
	double percentAreaToImageArea;
	double area;
	double convexHullArea;
	double boundingRectLeft;
	double boundingRectTop;
	double boundingRectRight;
	double boundingRectBottom;
	
	public int compareTo(ParticleReport r)
	{
		return (int)(r.area - this.area);
	}
	
	public int compare(ParticleReport r1, ParticleReport r2)
	{
		return (int)(r1.area - r2.area);
	}
}
