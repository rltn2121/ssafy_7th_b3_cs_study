package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj1937욕심쟁이판다 {
	static int N;
	static int[][] map;
	static int[][] dp;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	
	static int max = 1;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		dp = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 0; i < N; i++) {
			for(int j =0; j < N; j++) {
				if(dp[i][j] == 0) dfs(i,j);
			}
		}
//		for (int i = 0; i < N; i++) {
//			for(int j = 0; j < N; j++) {
//				System.out.print(dp[i][j]);
//			}
//			System.out.println();
//		}
		
		System.out.println(max);
	}

	static int dfs(int y, int x) {
		if (dp[y][x] != 0) {
			return dp[y][x];
		}
		dp[y][x] = 1;
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			if (ny < 0 || nx < 0 || ny >= N || nx >= N)
				continue;
			if(map[y][x] < map[ny][nx]) {
				int temp = 1;
				temp += dfs(ny, nx);
				dp[y][x] = Math.max(temp, dp[y][x]);
				max = Math.max(max, dp[y][x]);
			}
		}
		return dp[y][x];
		
	}

}
