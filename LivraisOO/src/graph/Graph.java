package graph;

import model.TypicalDay;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import model.Delivery;
import model.CityMap;
import model.Node;
import model.Path;
import model.Section;
import model.TimeWindow;

public class Graph {
	private static final double INF = 1e12;
	private CityMap cityMap;
	private TypicalDay typicalDay;
	private int nbNodesDelivery, nbNodes;
	private ArrayList<Delivery> nodes = new ArrayList<Delivery>();
	private double[][] cost;
	private Path[][] paths;
	private int[] hashArray;
	private double[] dist;
	private Section[] parent;
	private int[][] edges;
	private int[] rank;

	/**
	 * Graph depends on a city map and on a typicalDay
	 * 
	 * @param map
	 * @param typicalDay
	 */
	public Graph(CityMap cityMap, TypicalDay typicalDay) {
		this.cityMap = cityMap;
		this.typicalDay = typicalDay;
		initGraph();
	}

	/**
	 * Initiate graph
	 */
	private void initGraph() {
		nbNodes = cityMap.getNodes().size();
		System.out.println(nbNodes);
		hashArray = new int[nbNodes];
		dist = new double[nbNodes];
		parent = new Section[nbNodes];
		ArrayList<TimeWindow> timeWindows = typicalDay.getTimeWindows();
		int count = 0;
		Delivery start = new Delivery(0, 0, typicalDay.getWareHouse());
		nodes.add(start);
		hashArray[typicalDay.getWareHouse()] = count++;
		for (int i = 0; i < timeWindows.size(); ++i) {
			ArrayList<Delivery> deliveries = timeWindows.get(i).getDeliveries();
			for (int j = 0; j < deliveries.size(); ++j) {
				nodes.add(deliveries.get(j));
				hashArray[deliveries.get(j).getAddress()] = count++;
			}
		}
		nbNodesDelivery = count;
		cost = new double[count][count];
		edges = new int[count][count];
		paths = new Path[count][count];
		rank = new int[count];
		generateRanks(timeWindows);
		initPartialGraph(count, timeWindows);
	}

	/**
	 * Generate ranks for each node used in the partial graph - the ranks are
	 * used in computing TSP faster
	 * 
	 * @param timeWindows
	 */
	private void generateRanks(ArrayList<TimeWindow> timeWindows) {
		int prev = 0;
		for (int i = 0; i < timeWindows.size(); ++i) {
			ArrayList<Delivery> deliveries = timeWindows.get(i).getDeliveries();
			for (int j = 0; j < deliveries.size(); ++j) {
				rank[hashArray[deliveries.get(j).getAddress()]] = prev;
			}
			prev += deliveries.size();
		}
	}

	/**
	 * Generate the partial graph that is going to be used to compute the TSP
	 * solution. The existence of edges between the nodes are stored in the
	 * matrix edges[][]
	 * 
	 * @param length
	 * @param timeWindows
	 */
	private void initPartialGraph(int length, ArrayList<TimeWindow> timeWindows) {
		for (int i = 0; i < length; ++i) {
			for (int j = 0; j < length; ++j) {
				edges[i][j] = 0;
			}
		}
		for (int i = 0; i < timeWindows.size(); ++i) {
			ArrayList<Delivery> deliveries = timeWindows.get(i).getDeliveries();
			if (i == 0) {
				for (int j = 0; j < deliveries.size(); ++j) {
					int out = hashArray[deliveries.get(j).getAddress()];
					edges[i][out] = 1;
				}
			}
			if (i < timeWindows.size() - 1) {
				ArrayList<Delivery> deliveriesOut = timeWindows.get(i + 1).getDeliveries();
				for (int j = 0; j < deliveries.size(); ++j) {
					int in = hashArray[deliveries.get(j).getAddress()];
					for (int k = 0; k < deliveriesOut.size(); ++k) {
						int out = hashArray[deliveriesOut.get(k).getAddress()];
						edges[in][out] = 1;
					}
				}
			} else {
				for (int j = 0; j < deliveries.size(); ++j) {
					int in = hashArray[deliveries.get(j).getAddress()];
					edges[in][0] = 1;
				}
			}
			for (int k = 0; k < deliveries.size(); ++k) {
				for (int j = 0; j < deliveries.size(); ++j) {
					if (k != j) {
						int in = hashArray[deliveries.get(k).getAddress()];
						int out = hashArray[deliveries.get(j).getAddress()];
						edges[in][out] = 1;
					}
				}
			}
		}
	}

	/**
	 * Computes the shortest paths from the delivery points/warehouse to the
	 * other delivery points
	 */
	public void computeShortestPaths() {
		for (int i = 0; i < nodes.size(); ++i) {
			int source = nodes.get(i).getAddress();
			dijkstra(source);
			computePaths(source);
		}
	}

	/**
	 * Implementation of Dijkstra algorithm used to compute the shortest paths
	 * from a source node to every other node of the graph
	 * 
	 * @param source
	 */
	private void dijkstra(int source) {
		for (int i = 0; i < nbNodes; ++i) {
			dist[i] = INF;
			parent[i] = null;
		}
		Comparator<Integer> comparator = new NodesComparator();
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>(nbNodes * nbNodes, comparator);
		dist[source] = 0;
		queue.add(source);
		while (queue.size() != 0) {
			int s = queue.poll();
			Node newNode = cityMap.getNodeById(s);
			ArrayList<Section> sections = newNode.getOutgoing();
			for (int i = 0; i < sections.size(); ++i) {
				int out = sections.get(i).getArrival();
				double time = sections.get(i).getTime();
				if (dist[out] > dist[s] + time) {
					dist[out] = dist[s] + time;
					parent[out] = sections.get(i);
					queue.add(out);
				}
			}
		}
	}

	/**
	 * Comparator class used in the priority queue of dijkstra
	 */
	private class NodesComparator implements Comparator<Integer> {

		@Override
		public int compare(Integer a, Integer b) {
			if (dist[a] < dist[b])
				return -1;
			if (dist[a] > dist[b])
				return 1;
			return 0;
		}
	}

	/**
	 * Computes all the matrix of paths from a source node to the delivery
	 * points
	 * 
	 * @param source
	 */
	private void computePaths(int source) {
		int pos = hashArray[source];
		for (int k = 0; k < nbNodesDelivery; ++k) {
			if (k != pos) {
				int out = nodes.get(k).getAddress();
				cost[pos][k] = dist[out];
				ArrayList<Section> path = new ArrayList<Section>();
				while (out != source) {
					path.add(parent[out]);
					out = parent[out].getDeparture();
				}
				path = reversePath(path);
				paths[pos][k] = new Path(nodes.get(pos), nodes.get(k), path, cost[pos][k]);
			} else {
				paths[pos][k] = null;
				cost[pos][k] = -1;
			}
		}
	}

	/**
	 * Reverses an array of sections, used to compute the reverse path
	 * 
	 * @param path
	 * @return
	 */
	private ArrayList<Section> reversePath(ArrayList<Section> path) {
		ArrayList<Section> ret = new ArrayList<Section>();
		int length = path.size();
		for (int i = 0; i < length; ++i) {
			ret.add(path.get(length - i - 1));
		}
		return ret;
	}

	/**
	 * @param in
	 * @param out
	 * @return the shortest path from the delivery in to delivery out
	 */
	public Path getPath(int in, int out) {
		return paths[in][out];
	}

	/**
	 * @return the number of delivery points
	 */
	public int getNbNodesDelivery() {
		return nbNodesDelivery;
	}

	/**
	 * @param i
	 * @param j
	 * @return the cost from the delivery i to the delivery j;
	 */
	public double getCost(int i, int j) {
		return cost[i][j];
	}

	/**
	 * @param i
	 * @return the rank for the ith delivery
	 */
	public int getRank(int i) {
		return rank[i];
	}

	/**
	 * Checks if there is an edge between the delivery i and the delivery j;
	 * 
	 * @param i
	 * @param j
	 * @return true if it is the case
	 */
	public boolean isEdge(int i, int j) {
		int s = hashArray[i];
		if (i < 0 || i >= nbNodesDelivery || j < 0 || j >= nbNodesDelivery)
			return false;
		return ((edges[i][j] == 1) ? true : false);
	}

	/**
	 * Computes the path between a starting delivery point and an ending
	 * delivery point
	 * 
	 * @param start
	 * @param end
	 * @return path
	 */
	public Path generatePath(Delivery start, Delivery end) {
		int in = start.getAddress();
		int out = end.getAddress();
		dijkstra(in);
		ArrayList<Section> path = new ArrayList<Section>();
		while (out != in && !parent[out].equals(null)) {
			path.add(parent[out]);
			out = parent[out].getDeparture();
		}
		reversePath(path);
		return new Path(start, end, path, dist[end.getAddress()]);
	}
}
