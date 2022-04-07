package ps_java;


import java.io.*;
import java.util.*;

public class b_16137 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static List<Node> list = new ArrayList<>();
	static int N,M,T,ans = Integer.MAX_VALUE;
	static int dx[] = {-1,0,1,0};
	static int dy[] = {0,1,0,-1};
	static int arr[][];
	static boolean visited[][];
	public static void main(String[] args) throws Exception{
		input();
		ans = func();
		for(Node n : list) {
			if(isCross(n.x,n.y)) continue;
			arr[n.x][n.y] = M;
			int ret = func();
			ans = Math.min(ans,ret);
			arr[n.x][n.y] = 0;
		}
		System.out.println(ans);
	}
	
	static int func() {
		int t = 0;
		visited = new boolean[N][N];
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(0,0,false));
		visited[0][0] = true;
		while(!q.isEmpty()) {
			int size = q.size();
			while(size-- > 0) {
				Node now = q.poll();
				if(now.x == N-1 && now.y == N-1) 
					return t;
				for(int i = 0; i<4; i++) {
					int nx = now.x+dx[i];
					int ny = now.y+dy[i];
					if(nx<0||nx>=N||ny<0||ny>=N || visited[nx][ny]) continue;
					// 그냥 갈 수 있음
					if(arr[nx][ny] == 1) {
						q.add(new Node(nx,ny,false));
						visited[nx][ny] = true;
					}

					// 오작교 주기 확인
					else if(!now.flag && arr[nx][ny] >= 2) {
						if((t+1) % arr[nx][ny] == 0) {
							q.add(new Node(nx,ny,true));
							visited[nx][ny] = true;
						}
						else {
							q.add(new Node(now.x,now.y,false));
						}
					}
				}
			}
			t++;
		}
		return Integer.MAX_VALUE;
	}

	static boolean isCross(int nx, int ny) {
		boolean hor = false;
		boolean ver = false;
		
		if(nx-1>=0 && arr[nx-1][ny] == 0)
			ver = true;
		if(nx+1<N && arr[nx+1][ny] == 0)
			ver = true;
		if(ny-1>=0 && arr[nx][ny-1] == 0)
			hor = true;
		if(ny+1<N && arr[nx][ny+1] == 0)
			hor = true;
		
		return ver && hor;
	}
	static void input() throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 0) {
					list.add(new Node(i,j, false));
				}
			}
		}
	}
	static class Node{
		int x;
		int y;
		boolean flag;
		public Node(int x, int y, boolean flag) {
			this.x = x;
			this.y = y;
			this.flag = flag;
		}
	}
}	
