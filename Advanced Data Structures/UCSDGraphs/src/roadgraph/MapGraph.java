/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which reprsents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
package roadgraph;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.function.Consumer;

import geography.GeographicPoint;
import util.GraphLoader;

/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which represents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
public class MapGraph {
	//TODO: Add your member variables here in WEEK 3
	int numEdges;
	int numVertices;
	HashMap<GeographicPoint, MapNode> vertices;
	
	/** 
	 * Create a new empty MapGraph 
	 */
	public MapGraph()
	{
		// TODO: Implement in this constructor in WEEK 3
		numEdges = 0;
		numVertices = 0;
		vertices = new HashMap<>();
	}
	
	/**
	 * Get the number of vertices (road intersections) in the graph
	 * @return The number of vertices in the graph.
	 */
	public int getNumVertices()
	{
		//TODO: Implement this method in WEEK 3
		return numVertices;
	}
	
	/**
	 * Return the intersections, which are the vertices in this graph.
	 * @return The vertices in this graph as GeographicPoints
	 */
	public Set<GeographicPoint> getVertices()
	{
		//TODO: Implement this method in WEEK 3
		return vertices.keySet();
	}
	
	/**
	 * Get the number of road segments in the graph
	 * @return The number of edges in the graph.
	 */
	public int getNumEdges()
	{
		//TODO: Implement this method in WEEK 3
		return numEdges;
	}

	
	
	/** Add a node corresponding to an intersection at a Geographic Point
	 * If the location is already in the graph or null, this method does 
	 * not change the graph.
	 * @param location  The location of the intersection
	 * @return true if a node was added, false if it was not (the node
	 * was already in the graph, or the parameter is null).
	 */
	public boolean addVertex(GeographicPoint location)
	{
		// TODO: Implement this method in WEEK 3
		vertices.put(location, new MapNode(location));
		numVertices++;
		return true;
	}
	
	/**
	 * Adds a directed edge to the graph from pt1 to pt2.  
	 * Precondition: Both GeographicPoints have already been added to the graph
	 * @param from The starting point of the edge
	 * @param to The ending point of the edge
	 * @param roadName The name of the road
	 * @param roadType The type of the road
	 * @param length The length of the road, in km
	 * @throws IllegalArgumentException If the points have not already been
	 *   added as nodes to the graph, if any of the arguments is null,
	 *   or if the length is less than 0.
	 */
	public void addEdge(GeographicPoint from, GeographicPoint to, String roadName,
			String roadType, double length) throws IllegalArgumentException {

		//TODO: Implement this method in WEEK 3
		MapEdge m = new MapEdge(from, to, roadType, length);
		
		MapNode n = vertices.get(from);
		n.addEdge(m);
		numEdges++;
	}
	
	public void printGraph()
	{
		for (Map.Entry<GeographicPoint, MapNode> entry : vertices.entrySet()) 
		{
		    //System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		    System.out.println(entry.getValue().toString());
		}
	}

	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return bfs(start, goal, temp);
	}
	
	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, 
			 					     GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 3
		
		Queue<GeographicPoint> theQueue = new LinkedList<>();
		HashMap<GeographicPoint, GeographicPoint> parent = new HashMap<>();
		HashSet<GeographicPoint> visited = new HashSet<>();
		boolean found = false;
		
		theQueue.add(start);
		visited.add(start);
		
		while(!theQueue.isEmpty())
		{
			GeographicPoint cur = theQueue.remove();
			//System.out.println(cur);
			nodeSearched.accept(cur);
			
			if(cur.equals(goal))
			{
				found = true;
				break;
			}
			MapNode n = vertices.get(cur);
			
			for(MapEdge m :  n.getEdges())
			{
				if (!visited.contains(m.getEnd()))
				{
					visited.add(m.getEnd());
					theQueue.add(m.getEnd());
					parent.put(m.getEnd(), m.getStart());
				}
			}
		}
		
		if(!found)
			return null;

		return calculatePath(start, goal, parent);
	}	
	
	public List<GeographicPoint> calculatePath(GeographicPoint start, GeographicPoint goal, HashMap<GeographicPoint, GeographicPoint> parent)
	{
//		for (Map.Entry<GeographicPoint, GeographicPoint> entry : parent.entrySet()) 
//		{
//		    System.out.println("Key: " + entry.getKey() + " Value: " + entry.getValue().toString());
//		}
		
		List<GeographicPoint> path = new LinkedList<>();
		GeographicPoint cur = goal;
		
		while(!cur.equals(start))
		{
			path.add(0, cur);
			cur = parent.get(cur);
		}
		path.add(0, start);
		return path;
	}
	

	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
		// You do not need to change this method.
        Consumer<GeographicPoint> temp = (x) -> {};
        return dijkstra(start, goal, temp);
	}
	
	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, 
			  GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 4
		Queue<MapNode> theQueue = new PriorityQueue<>(new NodeComparator());
		HashMap<GeographicPoint, GeographicPoint> parent = new HashMap<>();
		HashSet<GeographicPoint> visited = new HashSet<>();
		
		MapNode theStart = vertices.get(start);
		theStart.setDistance(0.0);
		theQueue.add(theStart);
		
		while(!theQueue.isEmpty())
		{
			MapNode cur = theQueue.remove();
			
			if(!visited.contains(cur))
			{
				visited.add(cur.getLocation());
				
				if(cur.getLocation().equals(goal))
					return calculatePath(start, goal, parent);
				
				MapNode n = vertices.get(cur.getLocation());
				
				for(MapEdge m : n.getEdges())
				{
					MapNode next = vertices.get(m.getEnd());
					
					if (!visited.contains(next.getLocation()))
					{
						if(cur.getDistance() +  m.getLength() < next.getDistance())
						{
							nodeSearched.accept(next.getLocation());
							next.setDistance(cur.getDistance() +  m.getLength());
							theQueue.add(next);
							parent.put(m.getEnd(), m.getStart());
						}
					}
				}
			}
		}
		return null;
	}
	

	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return aStarSearch(start, goal, temp);
	}
	
	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, 
											 GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 4
		Queue<MapNode> theQueue = new PriorityQueue<>(new NodeComparatorWithHeuristic());
		HashMap<GeographicPoint, GeographicPoint> parent = new HashMap<>();
		HashSet<GeographicPoint> visited = new HashSet<>();
		
		MapNode theStart = vertices.get(start);
		theStart.setDistance(0.0);
		theStart.setDistanceEstimate(0.0);
		theQueue.add(theStart);
		
		while(!theQueue.isEmpty())
		{
			MapNode cur = theQueue.remove();
			
			if(!visited.contains(cur))
			{
				visited.add(cur.getLocation());
				
				if(cur.getLocation().equals(goal))
					return calculatePath(start, goal, parent);
				
				MapNode n = vertices.get(cur.getLocation());
				
				for(MapEdge m : n.getEdges())
				{
					MapNode next = vertices.get(m.getEnd());
					
					if (!visited.contains(next.getLocation()))
					{
						if(cur.getDistance() + cur.getDistanceEstimate() +  m.getLength() < next.getDistance() + next.getDistanceEstimate())
						{
							nodeSearched.accept(next.getLocation());
							next.setDistance(cur.getDistance() +  m.getLength());
							next.setDistanceEstimate(next.getLocation().distance(goal));
							double newDist = next.getLocation().distance(goal);
							theQueue.add(next);
							parent.put(m.getEnd(), m.getStart());
						}
					}
				}
			}
		}
		return null;
	}

	
	
	public static void main(String[] args)
	{
		System.out.print("Making a new map...");
		MapGraph firstMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", firstMap);
		System.out.println("DONE.");
		
		GeographicPoint testStart = new GeographicPoint(1.0, 1.0);
		GeographicPoint testEnd = new GeographicPoint(8.0, -1.0);
		System.out.println("Dijkstra");
		System.out.println(firstMap.dijkstra(testStart, testEnd));
		System.out.println("A*");
		System.out.println(firstMap.aStarSearch(testStart, testEnd));
//		
//		// You can use this method for testing.  
//		
//		//firstMap.printGraph();
//		GeographicPoint testStart = new GeographicPoint(1.0, 1.0);
//		GeographicPoint testEnd = new GeographicPoint(8.0, -1.0);
//		System.out.println(firstMap.bfs(testStart, testEnd));
//		
//		System.out.print("Making a new map...");
//		MapGraph testMap = new MapGraph();
//		System.out.print("DONE. \nLoading the map...");
//		GraphLoader.loadRoadMap("data/graders/mod2/map3.txt", testMap);
//		System.out.println("DONE.");
//
//		GeographicPoint testStart2 = new GeographicPoint(0.0, 0.0);
//		GeographicPoint testEnd2 = new GeographicPoint(1.0, 2.0);
//		System.out.println(testMap.bfs(testEnd2, testStart2));
		
		/* Here are some test cases you should try before you attempt 
		 * the Week 3 End of Week Quiz, EVEN IF you score 100% on the 
		 * programming assignment.
		 */
		
//		MapGraph simpleTestMap = new MapGraph();
//		GraphLoader.loadRoadMap("data/testdata/simpletest.map", simpleTestMap);
//		
//		GeographicPoint testStart = new GeographicPoint(1.0, 1.0);
//		GeographicPoint testEnd = new GeographicPoint(8.0, -1.0);
//		
//		System.out.println("Test 1 using simpletest: Dijkstra should be 9 and AStar should be 5");
//		List<GeographicPoint> testroute = simpleTestMap.dijkstra(testStart,testEnd);
//		List<GeographicPoint> testroute2 = simpleTestMap.aStarSearch(testStart,testEnd);
//		
//		
//		MapGraph testMap = new MapGraph();
//		GraphLoader.loadRoadMap("data/maps/utc.map", testMap);
//		
//		// A very simple test using real data
//		testStart = new GeographicPoint(32.869423, -117.220917);
//		testEnd = new GeographicPoint(32.869255, -117.216927);
//		System.out.println("Test 2 using utc: Dijkstra should be 13 and AStar should be 5");
//		testroute = testMap.dijkstra(testStart,testEnd);
//		testroute2 = testMap.aStarSearch(testStart,testEnd);
//		
//		
//		// A slightly more complex test using real data
//		testStart = new GeographicPoint(32.8674388, -117.2190213);
//		testEnd = new GeographicPoint(32.8697828, -117.2244506);
//		System.out.println("Test 3 using utc: Dijkstra should be 37 and AStar should be 10");
//		testroute = testMap.dijkstra(testStart,testEnd);
//		testroute2 = testMap.aStarSearch(testStart,testEnd);
		
		
		
		/* Use this code in Week 3 End of Week Quiz */
		/*MapGraph theMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/maps/utc.map", theMap);
		System.out.println("DONE.");

		GeographicPoint start = new GeographicPoint(32.8648772, -117.2254046);
		GeographicPoint end = new GeographicPoint(32.8660691, -117.217393);
		
		
		List<GeographicPoint> route = theMap.dijkstra(start,end);
		List<GeographicPoint> route2 = theMap.aStarSearch(start,end);

		*/
		
	}
	
}

class NodeComparator implements Comparator<MapNode>
{
	@Override
	public int compare(MapNode m1, MapNode m2) 
	{
		if (m1.getDistance() < m2.getDistance())
			return -1;
		else if (m1.getDistance() > m2.getDistance())
			return 1;
		else 
			return 0;
	}
}

class NodeComparatorWithHeuristic implements Comparator<MapNode>
{
	@Override
	public int compare(MapNode m1, MapNode m2) 
	{
		if (m1.getDistance() + m1.getDistanceEstimate() < m2.getDistance() + m2.getDistanceEstimate())
			return -1;
		else if (m1.getDistance() + m1.getDistanceEstimate() > m2.getDistance() + m2.getDistanceEstimate())
			return 1;
		else 
			return 0;
	}
}
