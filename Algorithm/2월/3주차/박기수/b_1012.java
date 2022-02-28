package ps_java;


import java.io.*;
import java.util.*;

public class b_1012 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static Queue<Point> q;
	static StringTokenizer st;
	static int T,M,N,K,ans,arr[][];
	static int dx[] = {-1,0,1,0};
	static int dy[] = {0,1,0,-1};
	static boolean visited[][];
	public static void main(String[] args) throws Exception{
		T = Integer.parseInt(br.readLine());
		while(T-->0) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			arr = new int[N][M];
			visited = new boolean[N][M];
			q  = new ArrayDeque<>();
			ans = 0;
			for(int i = 0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int b = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				arr[a][b] = 1;
			}
			
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<M; j++) {
					if(visited[i][j] || arr[i][j]==0) continue;
					ans++;
					bfs(i,j);
				}
			}
			
			bw.write(ans+"\n");
		}
		br.close();
		bw.close();
	}
	
	private static void bfs(int i, int j) {
		q.offer(new Point(i,j));
		
		while(!q.isEmpty()) {
			int size = q.size();
			while(size-- > 0) {
				Point now = q.poll();
				for(int k = 0; k<4; k++) {
					int nx = now.x+dx[k];
					int ny = now.y+dy[k];
					if(nx<0||nx>=N||ny<0||ny>=M||visited[nx][ny]||arr[nx][ny]==0) continue;
					visited[nx][ny] = true;
					q.offer(new Point(nx, ny));
				}
			}
		}
	}

	static class Point{
		int x;
		int y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
}	
