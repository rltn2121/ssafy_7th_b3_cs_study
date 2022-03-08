package ps_java;


import java.io.*;
import java.util.*;

public class b_1937 {
	static int N,arr[][], dp[][];
	static int dx[] = {-1,0,1,0};
	static int dy[] = {0,1,0,-1};
//	static boolean visited[][];
	static int ans;
	static List<Point> list = new ArrayList<>();
	public static void main(String[] args) throws Exception{
		input();
		
		// 대나무가 많은 곳부터 탐색하기 (거꾸로 탐색)
		Collections.sort(list, (a,b) -> b.val-a.val);
		for(Point p : list) 
			dfs(p.x, p.y, 0);
		
		for(int i = 0; i<N ;i++) {
			for(int j = 0; j<N; j++)
				ans = Math.max(ans,  dp[i][j]);
		}
		System.out.println(ans+1);
	}
	
	static void dfs(int x, int y, int cnt) {
//		if(visited[x][y]) return;
//		visited[x][y] = true;
		for(int i = 0; i<4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(nx<0||nx>=N||ny<0||ny>=N) continue;
			
			// 대나무 많은 곳 -> 적은 곳으로 이동하기 (거꾸로 탐색)
			if(arr[nx][ny] >= arr[x][y]) continue;
			
			// 이미 탐색을 한 곳이면
			if(dp[nx][ny] != 0) {
				dp[nx][ny] = Math.max(dp[nx][ny], dp[x][y]+1);
				continue;
			}
			
			dp[nx][ny] = cnt+1;
			dfs(nx, ny, cnt+1);
		}
	}
	
	static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		dp = new int[N][N];
		
//		visited = new boolean[N][N];
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				list.add(new Point(i,j,arr[i][j]));
			}
		}
		br.close();
	}
	static class Point{
		int x;
		int y;
		int val;
		public Point(int x, int y, int val) {
			this.x = x;
			this.y = y;
			this.val = val;
		}
	}
}	
