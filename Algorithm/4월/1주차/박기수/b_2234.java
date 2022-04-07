package ps_java;


import java.io.*;
import java.util.*;

public class b_2234 {
	static StringBuilder sb = new StringBuilder();
	static boolean visited[][];
	static int dx[]= {0,-1,0,1};
	static int dy[] = {-1,0,1,0};
	static int N,M,arr[][];
	static int cnt, area, temp, area2;
	public static void main(String[] args) throws Exception{
		input();
		find();
		removeAndFind();
		System.out.println(sb.append(cnt).append("\n").append(area).append("\n").append(area2));
	}
	
	private static void find() {
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				if(visited[i][j]) continue;
				cnt++;
				temp = 0;
				dfs(i,j);
				area = Math.max(area, temp);
			}
		}
	}
	
	private static void removeAndFind() {
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				// 벽 하나씩 다 없애보기
				for(int d = 0; d<4; d++) {
					// 원래 벽 없으면 패스
					if((arr[i][j] & (1<<d)) == 0) continue;
					
					visited = new boolean[N][M];
					temp = 0;
					// 벽 없애기
					arr[i][j] -= (1 << d);
					dfs(i,j);
					area2 = Math.max(area2, temp);
					// 벽 원상복구
					arr[i][j] += (1<<d);
				}
			}
		}
	}

	private static void dfs(int x, int y) {
		if(visited[x][y]) return;
		visited[x][y] = true;
		temp++;
		for(int i = 0; i<4; i++) {
			// 벽 있으면 못 감
			if((arr[x][y] & (1<<i)) != 0) continue;
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(nx<0||nx>=N||ny<0||ny>=M) continue;
			dfs(nx,ny);
		}
	}

	static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		visited = new boolean[N][M];
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<M; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
	}
}	
