package roadgraph;

import java.util.*;

import geography.GeographicPoint;

public class MapNode
{
	GeographicPoint location;
	List<MapEdge> edges;
	double distance;
	double distanceEstimate;
	
	public MapNode(GeographicPoint a)
	{
		edges = new ArrayList<>();
		location = a;
		distance = Double.MAX_VALUE;
		distanceEstimate = Double.MAX_VALUE;
	}
	
	public void addEdge(MapEdge e)
	{
		edges.add(e);
	}
	
	public String toString()
	{
		//String s = location.toString() + "\n";
		
		//String s = "test\n";
		String s = "";
		
		for(MapEdge e : edges)
			s += e.toString() + "\n";
		
		return s; 
	}
	
	public List<MapEdge> getEdges()
	{
		return new ArrayList<MapEdge>(edges);
		//return edges;
	}
	
	public GeographicPoint getLocation()
	{
		return location;
	}
	
	public void setLocation(GeographicPoint g)
	{
		location = g;
	}
	
	public double getDistance()
	{
		return distance;
	}
	
	public void setDistance(double d)
	{
		distance = d;
	}
	
	public double getDistanceEstimate()
	{
		return distanceEstimate;
	}
	
	public void setDistanceEstimate(double d)
	{
		distanceEstimate = d;
	}

}
