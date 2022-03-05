package ps_java;


import java.io.*;
import java.util.*;

public class b_1916 {
	static int N,M,start,end,dist[];
	static ArrayList<Node>[] graph;
	static boolean visited[];
	static PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b)->a.w-b.w);
	public static void main(String[] args) throws Exception{
		input();
		pq.add(new Node(start,0));
		dist[start] = 0;
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			if(visited[now.v]) continue;
			visited[now.v] = true;
			
//			 특정 정점에 도착하면 멈추기
//			if(now.v == end)
//				break;
			
			for(Node next : graph[now.v]) {
				int next_v = next.v;
				int next_w = now.w+next.w;
				
				if(visited[next_v]) continue;
				
				if(next_w < dist[next_v]) {
					dist[next_v] = next_w;
					pq.add(new Node(next_v, next_w));
				}
					
			}
		}
		System.out.println(dist[end]);
	}
	
	static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		visited = new boolean[N+1];
		dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		graph = new ArrayList[N+1];
		for(int i = 1; i<=N; i++)
			graph[i] = new ArrayList<Node>();
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[a].add(new Node(b,w));
		}
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		br.close();
	}
	static class Node{
		int v;
		int w;
		public Node(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}
	}
}	

