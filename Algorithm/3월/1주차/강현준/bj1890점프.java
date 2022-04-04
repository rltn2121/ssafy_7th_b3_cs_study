package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj1890점프 {

	static int N;
	static int[][] map;
	static long[][] rst;

	static int count;

	static int[] dy = { 1, 0 };
	static int[] dx = { 0, 1 };


	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		rst = new long[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp2();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(rst[i][j] + " ");
			}
			System.out.println();
		}

		
		System.out.println(rst[N-1][N-1]);

	}

	static void dp() {

		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(new int[] {0, 0});
		rst[0][0] = 1;
		while(!q.isEmpty()) {
			int temp[] = q.poll();
			int y = temp[0];
			int x = temp[1];
			int add = map[y][x];
			
			if(add==0) continue;
			
			for(int d = 0; d < 2; d++) {
				int ny = y + dy[d] * add;
				int nx = x + dx[d] * add;
				if(ny >= N || nx >= N) continue;
				rst[ny][nx] += rst[y][x];
				q.offer(new int[] {ny, nx});
			}
			
		}

	}
	
	static void dp2() {
		rst[0][0] = 1;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(rst[i][j] == 0) continue;
				int num = map[i][j];
				if(num==0) continue;
				for(int d = 0; d < 2; d++) {
					int ny = i + dy[d] * num;
					int nx = j + dx[d] * num;
					if(ny >= N || nx >= N) continue;
					rst[ny][nx] += rst[i][j];
				}
				
				
				
			}
		}
	}

}
