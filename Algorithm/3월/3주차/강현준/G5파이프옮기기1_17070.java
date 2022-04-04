package 삼성A형기출문제;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class G5파이프옮기기1_17070 {

	static int N;
	static int[][] map;

	static int[] dy = { 0, 1, 1 };
	static int[] dx = { 1, 1, 0 };
	static Pipe p = new Pipe(1, 2, 0);

	static int ans;

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
		bfs();
		System.out.println(ans);
	}

	static void bfs() {
		Queue<Pipe> q = new ArrayDeque<>();
		q.offer(p);
		while (!q.isEmpty()) {
			//System.out.println("zz");
			Pipe cur = q.poll();
			if (cur.y == N && cur.x == N) {
				//System.out.println("zz");
				ans++;
			} else {
				for (int i = -1; i <= 1; i++) {
					int nd = cur.d + i;
					if (nd < 0 || nd > 2)
						continue;
					int ny = cur.y + dy[nd];
					int nx = cur.x + dx[nd];
					if (ny < 1 || nx < 1 || ny > N || nx > N)
						continue;

					if (map[ny][nx] == 1)
						continue;
					if (nd == 1 && (map[ny - 1][nx] == 1 || map[ny][nx - 1] == 1))
						continue;
					//System.out.println(ny + " " + nx + " " + nd);
					q.offer(new Pipe(ny, nx, nd));
				}
			}

		}
	}

	static class Pipe {
		int y, x, d;

		public Pipe(int y, int x, int d) {
			super();
			this.y = y;
			this.x = x;
			this.d = d;
		}

	}
}
