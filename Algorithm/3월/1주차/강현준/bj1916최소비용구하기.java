package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj1916최소비용구하기 {

	static int N, M;
	static ArrayList<ArrayList<Edge>> list = new ArrayList<ArrayList<Edge>>();
	static boolean[] isVisited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<Edge>());
		}

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			list.get(from).add(new Edge(to, w));
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		isVisited = new boolean[N + 1];
		int[] minEdge = new int[N + 1];
		Arrays.fill(minEdge, Integer.MAX_VALUE);

		minEdge[start] = 0;
		PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.w - e2.w);
		pq.add(new Edge(start, 0));
		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			if(isVisited[e.v]) continue;
			isVisited[e.v] = true;
			if(e.v == end) {
				System.out.println(minEdge[e.v]);
				break;
			}
			for(Edge ne : list.get(e.v)) {
				if(!isVisited[ne.v] && minEdge[ne.v] > minEdge[e.v] + ne.w) {
					minEdge[ne.v] = minEdge[e.v] + ne.w;
					pq.add(new Edge(ne.v, minEdge[ne.v]));
				}
					
			}
			
			
		}

	}

	static class Edge {
		int v, w;

		public Edge(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}
	}

}
