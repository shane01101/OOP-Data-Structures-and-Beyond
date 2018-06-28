package roadgraph;

import java.util.*;

import geography.GeographicPoint;

public class MapNode 
{
	GeographicPoint location;
	List<MapEdge> edges;
	
	public MapNode(GeographicPoint a)
	{
		edges = new ArrayList<>();
		location = a;
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
		//return new ArrayList<MapEdge>(edges);
		return edges;
	}
}
