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
import model.Section;
import model.TimeWindow;

public class GraphComplete implements Graph {
	private static final double INF = 1e12;
	private CityMap map;
	private TypicalDay typicalDay;
	private int nbNodesDelivery;
	private int nbNodes;
	private ArrayList<Integer> nodes = new ArrayList<Integer>();
	private double[][] cost;
	private HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
	private double[] dist;
	
	public GraphComplete(CityMap map, TypicalDay typicalDay) {
		this.map = map;
		this.typicalDay = typicalDay;
		initGraph();
	}
	
	private void initGraph(){
	    nbNodes = map.getNodes().size();
	    dist = new double[nbNodes];
		System.out.println(nbNodes + " " + nbNodesDelivery);
		ArrayList<TimeWindow> timeWindows = typicalDay.getTimeWindows();
		int count=0;
		nodes.add(typicalDay.getWareHouse());
		hashMap.put(typicalDay.getWareHouse(), count++);
		for (int i = 0; i < timeWindows.size(); ++i) {
			ArrayList<Delivery> deliveries = timeWindows.get(i).getDeliveries();
			for (int j = 0; j < deliveries.size(); ++j) {
				nodes.add(deliveries.get(j).getAddress());
				hashMap.put(deliveries.get(j).getAddress(),count++);
			}
		}
		nbNodesDelivery=count;
		cost = new double[count][count];
	}

	@Override
	public int getNbNodes() {
		return nbNodes;
	}

	@Override
	public double getCost(int i, int j) {
		if (i < 0 || i >= nbNodesDelivery || j < 0 || j >= nbNodesDelivery)
			return -1;
		return cost[i][j];
	}

	@Override
	public boolean isEdge(int i, int j) {
		if (i < 0 || i >= nbNodesDelivery || j < 0 || j >= nbNodesDelivery)
			return false;
		return i != j;
	}

	public void compute() {
		Dijkstra(typicalDay.getWareHouse());
		ArrayList<TimeWindow> timeWindows = typicalDay.getTimeWindows();
		for (int i = 0; i < timeWindows.size(); ++i) {
			ArrayList<Delivery> deliveries = timeWindows.get(i).getDeliveries();
			for (int j = 0; j < deliveries.size(); ++j) {
				Dijkstra(deliveries.get(j).getAddress());
			}
		}
		for(int i=0;i<nbNodesDelivery;++i)
		{
			for(int j=0;j<nbNodesDelivery;++j)
			{
				System.out.print(cost[i][j]+" ");
			}
			System.out.println();
		}
	}

	private void Dijkstra(int source) {
		for (int i = 0; i < nbNodes; ++i) {
			dist[i] = INF;
		}
		Comparator<Integer> comparator = new NodesComparator();
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>(nbNodes*nbNodes, comparator);
		//System.out.println(sections.size());
		dist[source] = 0;
		queue.add(source);
		//System.out.println(source.getId());
		while (queue.size() != 0) {
			int s = queue.poll();
			Node newNode = map.getNodeById(s);
			ArrayList<Section> sections = newNode.getOutgoing();
			for (int i = 0; i < sections.size(); ++i) {
				int out = sections.get(i).getArrival();
				double time = sections.get(i).getTime();
				if (dist[out] > dist[s] + time) {
					dist[out] = dist[s] + time;
					queue.add(out);
				}
			}
		}
		Set set = hashMap.entrySet();
		Iterator iterator = set.iterator();
		int pos=hashMap.get(source);
		for(int i=0;i<nbNodesDelivery;++i){
			cost[pos][i]= dist[nodes.get(i)];
		}
	}

	private class NodesComparator implements Comparator<Integer> {
		
		@Override
		public int compare(Integer a, Integer b) {
			if (dist[a] <  dist[b])
				return -1;
			if (dist[a] >  dist[b])
				return 1;
			return 0;
		}
	}
}
