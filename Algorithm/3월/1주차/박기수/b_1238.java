package ps_java;


import java.io.*;
import java.util.*;

public class b_1238 {
	static int N,M,X;
	static ArrayList<Node>[] graph;
	static ArrayList<Node>[] graphReverse;
	static Queue<Node> q1 = new ArrayDeque<>();
	static Queue<Node> q2 = new ArrayDeque<>();
	static int dist1[];
	static int dist2[];
	public static void main(String[] args) throws Exception{
		input();
		
		// x로 가기
		dist1[X] = 0;
		q1.add(new Node(X,0));
		while(!q1.isEmpty()) {
			Node now = q1.poll();
			for(Node next : graphReverse[now.dst]) {
				int next_v = next.dst;
				int next_w = now.weight + next.weight;
				if(dist1[next_v] > next_w) {
					dist1[next_v] = next_w;
					q1.add(new Node(next_v, next_w));
				}
			}
		}
		
		
		// x에서 출발
		dist2[X] = 0;
		q2.add(new Node(X,0));
		while(!q2.isEmpty()) {
			Node now = q2.poll();
			for(Node next : graph[now.dst]) {
				int next_v = next.dst;
				int next_w = now.weight + next.weight;
				if(dist2[next_v] > next_w) {
					dist2[next_v] = next_w;
					q2.add(new Node(next_v, next_w));
				}
			}
		}
		
		int ans = -1;
		for(int i = 1; i<=N; i++) {
			ans = Math.max(ans, dist1[i] + dist2[i]);
		}
		System.out.println(ans);
		
	}
	
	static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		dist1 = new int[N+1];
		dist2 = new int[N+1];
		
		Arrays.fill(dist1, 1000000000);
		Arrays.fill(dist2, 1000000000);
		
		graph = new ArrayList[N+1];
		for(int i = 1; i<=N; i++)
			graph[i] = new ArrayList<Node>();
		
		graphReverse = new ArrayList[N+1];
		for(int i = 1; i<=N; i++)
			graphReverse[i] = new ArrayList<Node>();
		
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[a].add(new Node(b,w));
			graphReverse[b].add(new Node(a,w));
		}
	}
	static class Node{
		int dst;
		int weight;
		public Node(int dst, int weight) {
			this.dst = dst;
			this.weight = weight;
		}
	}
}	
