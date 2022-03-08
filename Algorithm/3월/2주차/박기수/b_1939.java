package ps_java;


import java.io.*;
import java.util.*;

public class b_1939 {
	static int N,M,start,end,parent[],ans;
	static PriorityQueue<Node> pq = new PriorityQueue<>((a,b)->b.weight-a.weight);
	public static void main(String[] args) throws Exception{
		input();
		makeSet();
		
		while(true) {
			if(find(start) == find(end)) break;
			Node now = pq.poll();
			union(now.src, now.dst);
			ans = now.weight;
		}
		System.out.println(ans);
	}
	
	static void makeSet() {
		for(int i = 1; i<=N; i++)
			parent[i] = i;
	}
	
	static int find(int x) {
		if(x==parent[x]) return x;
		return parent[x] = find(parent[x]);
	}
	
	static void union(int x, int y) {
		int px = find(x);
		int py = find(y);
		if(px==py) return;
		if(px>py)
			parent[px] = py;
		else
			parent[py] = px;
	}
	
	static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parent = new int[N+1];
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			pq.add(new Node(a,b,c));
		}
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		br.close();
	}
	
	static class Node{
		int src;
		int dst;
		int weight;
		public Node(int src, int dst, int weight) {
			this.src = src;
			this.dst = dst;
			this.weight = weight;
		}
	}
}	
