package ps_java;


import java.io.*;
import java.util.*;

public class b_3055 {
	static Queue<Node> water = new ArrayDeque<>();
	static boolean visited[][];
	static int dx[] = {-1,0,1,0};
	static int dy[] = {0,1,0,-1};
	static int N,M,x,y;
	static char arr[][];
	public static void main(String[] args) throws Exception{
		input();
		int ans = bfs();
		System.out.println((ans==-1)?"KAKTUS":ans);
	}
	
	static int bfs() {
		int cnt = 0;
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(x,y));
		visited[x][y] = true;
		while(!q.isEmpty()) {
		
			// 고슴도치 움직이기
			int size = q.size();
			while(size-- > 0) {
				Node now = q.poll();
				if(arr[now.x][now.y]=='*')
					continue;
				if(arr[now.x][now.y]=='D')
					return cnt;
				for(int i = 0; i<4; i++) {
					int nx = now.x+dx[i];
					int ny = now.y+dy[i];
					if(nx<0||nx>=N||ny<0||ny>=M||arr[nx][ny] == '*'||arr[nx][ny]=='X'||visited[nx][ny]) continue;
					visited[nx][ny] = true;
					q.add(new Node(nx,ny));
				}
				
			}
			
			// 물 퍼뜨리기
			size = water.size();
			while(size-->0) {
				Node now = water.poll();
				for(int i = 0; i<4; i++) {
					int nx = now.x+dx[i];
					int ny = now.y+dy[i];
					if(nx<0||nx>=N||ny<0||ny>=M||arr[nx][ny] != '.') continue;
					arr[nx][ny] = '*';
					water.add(new Node(nx,ny));
				}
			}
			cnt++;
		}
		return -1;
	}

	static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new char[N][M];
		visited = new boolean[N][M];
		for(int i = 0; i<N; i++) {
			arr[i] = br.readLine().toCharArray();
			for(int j = 0; j<M; j++) {
				if(arr[i][j] == 'S') {
					x=i;
					y=j;
				}
				else if(arr[i][j] == '*') 
					water.add(new Node(i,j));
			}
		}
	}
	static class Node{
		int x;
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}	
