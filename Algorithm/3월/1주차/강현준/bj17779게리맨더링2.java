package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj17779게리맨더링2 {
	static int N;
	static int[][] map;
	static int ans = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {

				for (int d1 = 1; d1 < N; d1++) {
					for (int d2 = 1; d2 < N; d2++) {

						if (i + d1 + d2 > N)
							continue;
						if (j - d1 < 1 || j + d2 > N)
							continue;

						func(i, j, d1, d2);
					}
				}

			}
		}
		System.out.println(ans);

	}

	static void func(int x, int y, int d1, int d2) {

		boolean[][] isVisited = new boolean[N + 1][N + 1];
		int[][] minmax = new int[N + 1][];
		int[] rst = new int[6];
		for (int i = 1; i <= N; i++) {
			minmax[i] = new int[] { Integer.MAX_VALUE, Integer.MIN_VALUE };
		}

		for (int i = 0; i <= d1; i++) {
			isVisited[x + i][y - i] = true;
			minmax[x + i][0] = Math.min(minmax[x + i][0], y - i);
			minmax[x + i][1] = Math.max(minmax[x + i][1], y - i);
			if(x==3 && y==3 && d1 ==1 && d2 ==1) {
				if(x + i==5 && y - i == 4) System.out.println("z1");
			}
		}
		for (int i = 0; i <= d2; i++) {
			isVisited[x + i][y + i] = true;
			minmax[x + i][0] = Math.min(minmax[x + i][0], y + i);
			minmax[x + i][1] = Math.max(minmax[x + i][1], y + i);
			if(x==3 && y==3 && d1 ==1 && d2 ==1) {
				if(x + i==5 && y + i == 4) System.out.println("z2");
			}
		}
		for (int i = 0; i <= d2; i++) {
			//System.out.println((x + d1 + i) + " " + (y - d1 + i));
			isVisited[x + d1 + i][y - d1 + i] = true;
			minmax[x + d1 + i][0] = Math.min(minmax[x + d1 + i][0], y - d1 + i);
			minmax[x + d1 + i][1] = Math.max(minmax[x + d1 + i][1], y - d1 + i);
			if(x==3 && y==3 && d1 ==1 && d2 ==1) {
				if(x + d1 + i==5 && y - d1 + i == 4) System.out.println("z3");
			}
		}
		for (int i = 0; i <= d1; i++) {
			isVisited[x + d2 + i][y + d2 - i] = true;
			minmax[x + d2 + i][0] = Math.min(minmax[x + d2 + i][0], y + d2 - i);
			minmax[x + d2 + i][1] = Math.max(minmax[x + d2 + i][1], y + d2 - i);
			if(x==3 && y==3 && d1 ==1 && d2 ==1) {
				if(x + d2 + i==5 && y + d2 - i == 4) System.out.println("z4");
			}
		}
		// 5번
		for (int i = 1; i <= N; i++) {
			for (int j = minmax[i][0]; j <= minmax[i][1]; j++) {
				if(x==3 && y==3 && d1 ==1 && d2 ==1) {
					if(i==5 && j == 4) System.out.println("zz");
				}
				isVisited[i][j] = true;
				rst[5] += map[i][j];
			}
		}
		// 1번
		for (int i = 1; i < x + d1; i++) {
			for (int j = 1; j <= y; j++) {
				if (isVisited[i][j])
					continue;
				rst[1] += map[i][j];
				
			}
		} // 2번
		for (int i = 1; i <= x + d2; i++) {
			for (int j = y + 1; j <= N; j++) {
				if (isVisited[i][j])
					continue;
				rst[2] += map[i][j];
			}
		} // 3번
		for (int i = x + d1; i <= N; i++) {
			for (int j = 1; j < y - d1 + d2; j++) {
				if (isVisited[i][j])
					continue;
				rst[3] += map[i][j];
			}
		} // 4번
		for (int i = x + d2 + 1; i <= N; i++) {
			for (int j = y - d1 + d2; j <= N; j++) {
				if (isVisited[i][j])
					continue;
				rst[4] += map[i][j];
				
			}
		}
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for(int i = 1; i <= 5; i++) {
			if(max < rst[i]) max = rst[i];
			if(min > rst[i]) min = rst[i];
		}
		ans = Math.min(ans, max-min);
		
	}

}
