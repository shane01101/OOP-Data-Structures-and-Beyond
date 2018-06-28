package roadgraph;

import geography.GeographicPoint;

public class MapEdge 
{
	GeographicPoint start;
	GeographicPoint end;
	String streetName;
	double distance;
	
	public MapEdge(GeographicPoint a, GeographicPoint b, String s, double d)
	{
		start = a;
		end = b;
		streetName = s;
		distance = d;
	}
	
	public double getDistance()
	{
		return start.distance(end);
		
	}
	
	public GeographicPoint getStart()
	{
		return start;
	}
	
	public GeographicPoint getEnd()
	{
		return end;
	}
	
	public String toString()
	{
		return start + " " + end + " " + streetName + " " + distance;
	}
}
