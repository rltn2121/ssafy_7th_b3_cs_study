package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj1976여행가자 {
	static int N, M;
	static int[][] map;
	static int[] arr;
	static int[] parent;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		map = new int[N+1][N+1];
		arr = new int[M];
		parent = new int[N+1];
		
		makeSet();
		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1)
					Union(i, j);
			}
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i < M; i++) {
			if(findSet(arr[i-1]) != findSet(arr[i])) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
		
	}
	static void makeSet() {
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
		}
	}
	static int findSet(int a) {
		if(parent[a] == a) return a;
		return parent[a] = findSet(parent[a]);
	}
	static boolean Union(int a, int b) {
		int pa = findSet(a);
		int pb = findSet(b);
		if(pa == pb) return false;
		parent[pb] = parent[pa];
		return true;
	}
}
