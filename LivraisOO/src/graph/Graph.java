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

public class Graph{
	private static final double INF = 1e12;
	private CityMap map;
	private TypicalDay typicalDay;
	private int nbNodesDelivery,nbNodes;
	private ArrayList<Delivery> nodes = new ArrayList<Delivery>();
	private double[][] cost;
	private Path[][] paths;
	private int[] hashMap;
	private double[] dist;
	private Section[] parent;
	private int[][] edges;
	
	public Graph(CityMap map, TypicalDay typicalDay) {
		this.map = map;
		this.typicalDay = typicalDay;
		initGraph();
	}
	
	private void initGraph(){
	    nbNodes = map.getNodes().size();
	    System.out.println(nbNodes);
	    hashMap = new int[nbNodes];
	    dist = new double[nbNodes];
	    parent = new Section[nbNodes];
		ArrayList<TimeWindow> timeWindows = typicalDay.getTimeWindows();
		int count=0;
		Delivery start = new Delivery(0x7fffffff,0x7fffffff,typicalDay.getWareHouse());
		nodes.add(start);
		hashMap[typicalDay.getWareHouse()]= count++;
		for (int i = 0; i < timeWindows.size(); ++i) {
			ArrayList<Delivery> deliveries = timeWindows.get(i).getDeliveries();
			for (int j = 0; j < deliveries.size(); ++j) {
				nodes.add(deliveries.get(j));
				hashMap[deliveries.get(j).getAddress()] = count++;
			}
		}
		nbNodesDelivery=count;
		cost = new double[count][count];
		edges = new int[count][count];
		paths = new Path[count][count];
		initPartialGraph(count,timeWindows);
	}
	
	private void initPartialGraph(int length, ArrayList<TimeWindow> timeWindows){
		for(int i=0;i<length;++i){
			for(int j=0;j<length;++j){
				edges[i][j]=0;
			}
		}
		for(int i=0;i<timeWindows.size();++i){
			ArrayList<Delivery> deliveries = timeWindows.get(i).getDeliveries();
			if(i==0) {
				for(int j=0;j<deliveries.size();++j){
					int out = hashMap[deliveries.get(j).getAddress()];
					edges[i][out]=1;
				}
			} 
			if(i<timeWindows.size()-1){
				ArrayList<Delivery> deliveriesOut = timeWindows.get(i+1).getDeliveries();
				for(int j=0;j<deliveries.size();++j){
					int in = hashMap[deliveries.get(j).getAddress()];
					for(int k=0;k<deliveriesOut.size();++k){
						int out = hashMap[deliveriesOut.get(k).getAddress()];
						edges[in][out]=1;
					}
				}
			} else {
				for(int j=0;j<deliveries.size();++j){
					int in = hashMap[deliveries.get(j).getAddress()];
					edges[in][0]=1;
				}
			}
			for(int k=0;k<deliveries.size();++k){
				for(int j=0;j<deliveries.size();++j){
					if(k!=j)
					{
						int in = hashMap[deliveries.get(k).getAddress()];
						int out = hashMap[deliveries.get(j).getAddress()];
						edges[in][out]=1;
					}
				}
			}
		}
	}

	public void compute() {
		for(int i=0;i<nodes.size();++i)
		{
			int source  = nodes.get(i).getAddress();
			dijkstra(source);
			computePaths(source);
		}
	}

	private void dijkstra(int source) {
		for (int i = 0; i < nbNodes; ++i) {
			dist[i] = INF;
			parent[i] = null;
		}
		Comparator<Integer> comparator = new NodesComparator();
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>(nbNodes*nbNodes, comparator);
		dist[source] = 0;
		queue.add(source);
		while (queue.size() != 0) {
			int s = queue.poll();
			Node newNode = map.getNodeById(s);
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
	
	private void computePaths(int source){
		int pos=hashMap[source];
		for(int k=0;k<nbNodesDelivery;++k){
			if(k!=pos)
			{
				int out = nodes.get(k).getAddress();
				cost[pos][k]= dist[out];
				ArrayList<Section> path = new ArrayList<Section>();
				while(out!=source && !parent[out].equals(null)){
					path.add(parent[out]);
					out = parent[out].getDeparture();
				}
				path = reversePath(path);
				paths[pos][k] = new Path(nodes.get(pos),nodes.get(k),path);
			} else {
				paths[pos][k]=null;
				cost[pos][k]=-1;
			}
		}
	}
	
	
	private ArrayList<Section> reversePath(ArrayList<Section> path){
		ArrayList<Section> ret = new ArrayList<Section>();
		int length = path.size();
		for(int i=0;i<length;++i){
			ret.add(path.get(length-i-1));
		}
		return ret;
	}
	
	public Path getPath(int in,int out){
		return paths[in][out];
	}
	

	public int getNbNodes() {
		return nbNodesDelivery;
	}
	
	public double getCost(int i, int j) {
		return cost[i][j];
	}

	public boolean isEdge(int i, int j) {
		int s = hashMap[i];
		if (i < 0 || i >= nbNodesDelivery || j < 0 || j >= nbNodesDelivery)
			return false;
		return ((edges[i][j]==1)?true:false);
	}
	
	public Path generatePath(Delivery start,Delivery end){
		int in = start.getAddress();
		int out = end.getAddress();
		dijkstra(in);
		ArrayList<Section> path = new ArrayList<Section>();
		while(out!=in && !parent[out].equals(null)){
			path.add(parent[out]);
			out = parent[out].getDeparture();
		}
		return new Path(start,end,path);
	}
}
