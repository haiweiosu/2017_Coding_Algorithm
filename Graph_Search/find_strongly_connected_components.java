//given a graph, find all its strongly connected components

import java.io.*;
import java.util.*;
import java.util.LinkedList;

class Graph {
	private int V; //number of vertices
	private LinkedList<Integer>[] adj; //adjacency list

	Graph(int v) {
		this.V = v;
		this.adj = new LinkedList[v];
		for (int i = 0; i < v; i++) {
			this.adj[i] = new LinkedList();
		}
	}

	//add an edge to graph
	public void addEdge(int v, int w) {
		adj[v].add(w);
	}

	//print DFS starting from v
	public void getStronglyComponent(int v, boolean[] visited, List<Integer> component) {
		visited[v] = true;
		component.add(v);

		int n = 0;

		Iterator<Integer> i = adj[v].iterator();

		while (i.hasNext()) {
			n = i.next();
			if (!visited[n]) {
				dfsPrint(n, visited);
			}
		}
	}

	//returns a reverse of this graph
	Graph getReverse() {
		Graph g = new Graph(V);
		for (int v = 0; v < V; i++) {
			Iterator<Integer> i = adj[i].iterator();
			while (i.hasNext()) {
				g.adj[i.next()].add(v);
			}
		}
		return g;
	}

	public void fillOrder(int v, boolean[] visited, Stack stack) {

		visited[v] = true;

		//dfs all vertices adjacent to this vertex
		Iterator<Integer> i = adj[v].iterator();
		while (i.hasNext()) {
			int n = i.next();
			if (!visited[n]) {
				fillOrder(n, visited, stack);
			}
		}

		//All adjacent vertices has been processed, push v to stack
		stack.push(new Integer(v));
	}

	public List<List<Integer>> getAllScc() {
		Stack stack = new Stack();
		List<List<Integer>> res = new ArrayList<>();

		//initilize all vertices as non-visited
		boolean[] visited = new boolean[V];
		for (int v = 0; v < V; v++) {
			visited[v] = false;
		}

		Graph g = getReverse();

		for (int i = 0; i < V; i++) {
			visited[i] = false;
		}

		while (!stack.isEmpty()) {
			int cur = stack.pop();

			if (visited[cur] == false) {
				List<Integer> component = new ArrayList<>();
				g.getStronglyComponent(cur, visited, component);
				res.add(component);
			}
		}

		return res;
	}
}
