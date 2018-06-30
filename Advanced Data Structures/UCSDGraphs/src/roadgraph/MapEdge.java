package roadgraph;

import geography.GeographicPoint;

public class MapEdge 
{
	GeographicPoint start;
	GeographicPoint end;
	String streetName;
	double length;
	
	public MapEdge(GeographicPoint a, GeographicPoint b, String s, double l)
	{
		start = a;
		end = b;
		streetName = s;
		length = l;
	}
	
	public double getLength()
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
		return start + " " + end + " " + streetName + " " + length;
	}
}
