package ps_java;


import java.io.*;
import java.util.*;

public class b_5427 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static Queue<Node> fire;
	static StringTokenizer st;
	static int N,M,T,x,y;
	static int dx[] = {-1,0,1,0};
	static int dy[] = {0,1,0,-1};
	static boolean visited[][];
	static char arr[][];
	public static void main(String[] args) throws Exception{
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T; tc++) {
			input();
			int ret = func();
			System.out.println((ret==-1)?"IMPOSSIBLE":ret);
		}
	}
	
	private static int func() {
		int cnt = 0;
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(x,y));
		visited[x][y] = true;
		while(!q.isEmpty()) {
			cnt++;
			// 불 번지기
			int size = fire.size();
			while(size-->0) {
				Node now = fire.poll();
				for(int i = 0; i<4; i++) {
					int nx = now.x+dx[i];
					int ny = now.y+dy[i];
					if(nx<0||nx>=N||ny<0||ny>=M||arr[nx][ny]!='.') continue;
					fire.add(new Node(nx,ny));
					arr[nx][ny] = '*';
				}
			}
			
			// 이동하기
			size = q.size();
			while(size-->0) {
				Node now = q.poll();
				
				for(int i = 0; i<4; i++) {
					int nx = now.x+dx[i];
					int ny = now.y+dy[i];
					if(nx<0||nx>=N||ny<0||ny>=M)
						return cnt;
					
					if(visited[nx][ny] || arr[nx][ny] == '#' || arr[nx][ny] == '*') continue;
					q.add(new Node(nx,ny));
					visited[nx][ny] = true;
				}
			}
		}
		return -1;
	}

	static void input() throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		fire = new ArrayDeque<>();
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		arr = new char[N][M];
		visited = new boolean[N][M];
		for(int i = 0; i<N; i++) {
			arr[i] = br.readLine().toCharArray();
			for(int j = 0; j<M; j++) {
				if(arr[i][j] == '@') {
					x=i;
					y=j;
				}
				else if(arr[i][j] == '*') {
					fire.add(new Node(i,j));
				}
			}
		}
	}
	static class Node{
		int x;
		int y;
		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}	
