package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj14503로봇청소기 {

	static int N, M;

	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	static Robot r;
	static int[][] map;
	static int count;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		st = new StringTokenizer(br.readLine());
		int yy = Integer.parseInt(st.nextToken());
		int xx = Integer.parseInt(st.nextToken());
		int dd = Integer.parseInt(st.nextToken());
		r = new Robot(yy, xx, dd);
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		func();
		System.out.println(count);
//		for (int i = 0; i < N; i++) {
//			System.out.println();
//			for (int j = 0; j < M; j++) {
//				System.out.print(map[i][j] + "\t");
//			}
//		}

	}

	static void func() {
		// 청소하기
		if (map[r.y][r.x] == 0)
			count++;
		map[r.y][r.x] = -1;

		// 탐색하기

		// 2.a, 2.b
		for (int i = 1; i <= 4; i++) {
			int d = r.d - i;
			if (d < 0)
				d += 4;
			int ny = r.y + dy[d];
			int nx = r.x + dx[d];

			if (map[ny][nx] == 0) {
				r.y = ny;
				r.x = nx;
				r.d = d;
				func();
				return;
			}
		}
		// 2.c
		int d = r.d - 2;
		if (d < 0)
			d += 4;
		int ny = r.y + dy[d];
		int nx = r.x + dx[d];
		if (map[ny][nx] == 1)
			return;
		r.y = ny;
		r.x = nx;
		func();

	}

	static class Robot {
		int y, x, d;

		public Robot(int y, int x, int d) {
			super();
			this.y = y;
			this.x = x;
			this.d = d;
		}

	}
}
