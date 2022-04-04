package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj1238파티 {

	static int N, M, X;
	static boolean[] isVisited;
	static int[] rst;
	static int max;
	static ArrayList<ArrayList<Edge>> list = new ArrayList<ArrayList<Edge>>();
	static ArrayList<ArrayList<Edge>> list2 = new ArrayList<ArrayList<Edge>>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		isVisited = new boolean[N + 1];
		rst = new int[N + 1];

		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<Edge>());
			list2.add(new ArrayList<Edge>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			list.get(from).add(new Edge(to, time));
			list2.get(to).add(new Edge(from, time));
		}

		PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.w - e2.w);
		pq.addAll(list.get(X));
		rst[X] = 0;
		isVisited[X] = true;
		while (!pq.isEmpty()) {

			Edge e = pq.poll();
			if (isVisited[e.v])
				continue;
			isVisited[e.v] = true;
			rst[e.v] = e.w;
			max = Math.max(max, e.w);
			for(Edge ee : list.get(e.v)) {
				pq.offer(new Edge(ee.v, ee.w + e.w));
			}
		}
		
		isVisited = new boolean[N + 1];
		PriorityQueue<Edge> pq2 = new PriorityQueue<>((e1, e2) -> e1.w - e2.w);
		pq2.addAll(list2.get(X));
		isVisited[X] = true;
		while (!pq2.isEmpty()) {

			Edge e = pq2.poll();
			if (isVisited[e.v])
				continue;
			isVisited[e.v] = true;
			rst[e.v] += e.w;
			max = Math.max(max, rst[e.v]);
			for(Edge ee : list2.get(e.v)) {
				pq2.offer(new Edge(ee.v, ee.w + e.w));
			}
		}
		
		
		
		
		
		System.out.println(max);
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
