package ps_java;


import java.io.*;
import java.util.*;

public class b_16929 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static boolean alpha[] = new boolean[26];
	static boolean visited[][];
	static char arr[][];
	static int N,M;
	static int dx[] = {-1,0,1,0};
	static int dy[] = {0,1,0,-1};
	public static void main(String[] args) throws Exception{
		input();
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				if(visited[i][j]) continue;
				if(func(i,j)) {
					System.out.println("Yes");
					System.exit(0);
				}
			}
		}
		System.out.println("No");
	}
	
	static boolean func(int i, int j) {
		char c = arr[i][j];
		Queue<Node> q = new ArrayDeque<>();
		alpha[c-'A'] = true;
		
		q.add(new Node(i,j,-1));
		visited[i][j] = true;
		while(!q.isEmpty()) {
			Node now = q.poll();
			
			for(int d = 0; d<4; d++) {
				// 들어온 방향으로 돌아가지 않음
				if(now.dir == (d+2)%4) continue;
				
				int nx = now.x+dx[d];
				int ny = now.y+dy[d];
				if(nx<0||nx>=N||ny<0||ny>=M||arr[nx][ny] != c) continue;
				if(visited[nx][ny]) 
					return true;
				
				q.add(new Node(nx,ny,d));
				visited[nx][ny] = true;
			}
		}
		return false;
	}

	static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new char[N][M];
		visited = new boolean[N][M];
		for(int i = 0; i<N; i++) 
			arr[i] = br.readLine().toCharArray();
	}
	
	static class Node{
		int x;
		int y;
		int dir;
		public Node(int x, int y, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
}	
