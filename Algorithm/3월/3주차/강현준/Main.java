package 삼성A형기출문제;

import java.io.*;
import java.util.*;

public class Main {
	static int N, M, D;
	static int[][] map;
	static int[][] copyMap;
	static boolean[] depenser;
	static int ans = 0;

	static void solve(int idx, int cnt) {
		if (cnt == 3) {
			copy();
			int temp = play();
			if (ans < temp) {
				ans = temp;
			}
			return;
		}

		for (int i = idx; i < M; i++) {
			depenser[i] = true;
			solve(idx + 1, cnt + 1);
			depenser[i] = false;
		}
	}

	static int play() {
		int cnt = 0;

		Stack<int[]> st = new Stack<>();
		for (int turn = N - 1; turn >= 0; turn--) {
			// 거리가 가까운 적 탐색
			for (int i = 0; i < M; i++) {
				if (!depenser[i])
					continue;
				int dis = N + M;
				int x = -1;
				int y = -1;

				for (int cx = turn; cx >= 0; cx--) {
					for (int cy = M-1; cy >= 0; cy--) {
						if (copyMap[cx][cy] == 0)
							continue;

						int curDis = getDis(turn + 1, i, cx, cy);
						// 적과의 거리가 범위 내에 들고 거리가 제일 짧은 것들중 왼쪾
						if (curDis <= D && dis >= curDis) {
							dis = curDis;
							x = cx;
							y = cy;
						}
					} // end for cy
				} // end for cx
					// 적이 있으면
				if (x != -1) {
					st.add(new int[] { x, y });

				}
			} // end for i
			while (!st.isEmpty()) {
				int[] p = st.pop();
				if (copyMap[p[0]][p[1]] == 0)
					continue;
				copyMap[p[0]][p[1]] = 0;
				cnt++;
			}
		} // end for turn
		return cnt;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		copyMap = new int[N][M];
		depenser = new boolean[M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		solve(0, 0);
		System.out.println(ans);

	}

	static int getDis(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}

	static void copy() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
	}
}
