package ps_java;


import java.io.*;
import java.util.*;

public class b_17244 {
	static Queue<Node> q = new ArrayDeque<>();
	static List<Node> list = new ArrayList<>();
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	static int N,M,ans,TOTAL;
	static int x,y;
	static char arr[][];
	static boolean visited[][][];
	public static void main(String[] args) throws Exception{
		input();
		bfs();
		System.out.println(ans);
	}
	
	static void bfs() {
		visited[0][0][0] = true;
		q.add(new Node(x,y,0));
		
		while(true) {
			int size = q.size();
			while(size-- > 0) {
				Node now = q.poll();
				if(arr[now.x][now.y]=='E' && now.status == (1<<TOTAL)-1)
					return;
				
				for(int i = 0; i<4; i++) {
					int nx = now.x+dx[i];
					int ny = now.y+dy[i];
					int status = now.status;
					if(nx<0||nx>=N||ny<0||ny>=M||visited[status][nx][ny]||arr[nx][ny]=='#') continue;
					
					if(arr[nx][ny]=='X') {
						int idx = getIdx(nx,ny);
						status = status | (1<<idx);
						visited[status][nx][ny] = true;
						q.add(new Node(nx,ny,status));
					}
					else {
						visited[status][nx][ny] = true;
						q.add(new Node(nx,ny,status));
					}
				}
			}
			ans++;
		}
	}

	static int getIdx(int nx, int ny) {
		for(int i = 0; i<TOTAL; i++) {
			Node now = list.get(i);
			if(now.x==nx&&now.y==ny)
				return i;
		}
		return 0;
	}

	static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		arr = new char[N][M];
		for(int i = 0; i<N; i++) {
			arr[i] = br.readLine().toCharArray();
			for(int j = 0; j<M; j++) {
				if(arr[i][j]=='S') {
					x=i;
					y=j;
				}
				else if(arr[i][j]=='X') 
					list.add(new Node(i,j,0));
				
			}
		}
		TOTAL = list.size();
		visited = new boolean[1<<TOTAL][N][M];
	}
	
	static class Node{
		int x;
		int y;
		int status;
		public Node(int x, int y, int status) {
			super();
			this.x = x;
			this.y = y;
			this.status = status;
		}
	}
}	
