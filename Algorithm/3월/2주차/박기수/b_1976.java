package ps_java;

import java.io.*;
import java.util.*;

public class b_1976 {
	static int N,M,parent[],list[];
	public static void main(String[] args) throws Exception{
		input();
		int start = list[0];
		for(int i : list) {
			// 도시가 연결되어 있는가?
			if(find(start) != find(i)) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
	}
	
	static void makeSet() {
		parent = new int[N+1];
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
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		makeSet();
		
		// 모든 도로로 도시 연결하기
		for(int i = 1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j<=N; j++) {
				if(st.nextToken().equals("1")) 
					union(i,j);
			}
		}

		list = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<M; i++)
			list[i] = Integer.parseInt(st.nextToken());
	}
}	
