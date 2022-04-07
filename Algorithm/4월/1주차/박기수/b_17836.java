package ps_java;


import java.io.*;
import java.util.*;

public class b_17836 {
	static int N,M,T;
	static int dx[] = {-1,0,1,0};
	static int dy[] = {0,1,0,-1};
	static int arr[][];
	static boolean visited[][][];
	static boolean flag;
	public static void main(String[] args) throws Exception{
		input();
		int ret = bfs();
		System.out.println(ret == -1 ? "Fail" : ret);
	}
	
	static int bfs() {
		int cnt = 0;
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(0,0,0));
		visited[0][0][0] = true;
		
		while(!q.isEmpty()) {
			int size = q.size();
			while(size-->0) {
				Node now = q.poll();
				if(now.x==N-1&&now.y==M-1) return cnt;
				for(int i = 0; i<4; i++) {
					int nx = now.x+dx[i];
					int ny = now.y+dy[i];
					int flag = now.flag;
					
					if(nx<0||nx>=N||ny<0||ny>=M||visited[flag][nx][ny]) continue;
					
					if(arr[nx][ny] == 2)
						flag = 1;
					if(flag == 0 && arr[nx][ny] == 1) continue;
					
					visited[flag][nx][ny] = true;
					q.add(new Node(nx,ny,flag));
				}
			}
			if(++cnt > T)
				break;
		}
		return -1;
	}

	static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		visited = new boolean[2][N][M];
		arr = new int[N][M];
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	static class Node{
		int x;
		int y;
		int flag;
		public Node(int x, int y, int flag) {
			super();
			this.x = x;
			this.y = y;
			this.flag = flag;
		}
	}
}	
