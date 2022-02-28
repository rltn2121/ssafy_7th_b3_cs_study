package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class bj16236아기상어 {

	static int N, ans;
	static int[][] map;

	static Shark baby;
	static boolean[][] isVisted;

	static ArrayList<int[]> pq = new ArrayList<int[]>();
	
	// 위 왼 오 아
	static int[] dy = { -1, 0, 0, 1 };
	static int[] dx = { 0, -1, 1, 0 };

	// 맵에 있는 물고기 크기 검색
	static int[] fishSize = new int[7];

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		isVisted = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9)
					baby = new Shark(i, j);
				else {
					fishSize[map[i][j]]++;
				}
			}
		}
		func();
		System.out.println(ans);
	}

	static void func() {

		Queue<int[]> q = new ArrayDeque<int[]>();

		// 큐에 넣는 건, 현재 y 좌표, x 좌표, 걸린 시간을 넣는다.
		q.offer(new int[] { baby.y, baby.x, 0, 0 });
		isVisted[baby.y][baby.x] = true;
		// bfs로 구해야한다.
		while (!q.isEmpty() || !pq.isEmpty()) {
			if (!q.isEmpty()) {
				int[] temp = q.poll();
				int y = temp[0];
				int x = temp[1];
				int time = temp[2];
				int tempTime = temp[3];
				// System.out.println(y + " " + x + " " + time );

				// 큐에 있는 좌표가 9면 곧 자리를 비울 예정이니 0으로 바꿔준다.
				if (map[y][x] == 9) {
					map[y][x] = 0;
					fishSize[0]++;
				}

				// 큐에 있는 좌표가 0이 아니고 자기보다 크기가 작으면 잡아 먹는다.
				else if (map[y][x] != 0 && map[y][x] < baby.size) {
					// 잡아 먹으면
					// 해당 자리는 0으로 비워주고
					// 경험치는 1 증가, 시간 반환
					baby.exp++;
					map[y][x] = 0;
					ans = time;
					fishSize[0]++;
					// 경험치 만약 사이즈 만큼이면 사이즈 1 증가
					if (baby.exp == baby.size) {
						baby.exp = 0;
						baby.size++;
					}

					// 큐 초기화 하고 다시 새로운 큐로 시작
					q = new ArrayDeque<int[]>();
					pq = new ArrayList<int[]>();
					isVisted = new boolean[N][N];
					isVisted[y][x] = true;
					//q.offer(new int[] { y, x, time, 0 });
					tempTime = 0;
				}

				// 4방 탐색
				for (int i = 0; i < 4; i++) {

					int ny = y + dy[i];
					int nx = x + dx[i];

					// 인덱스 범위 벗어나면 컨티뉴
					if (ny < 0 || nx < 0 || ny >= N || nx >= N)
						continue;

					// 주변이 자기 사이즈보다 크면 컨티뉴
					if (map[ny][nx] > baby.size || isVisted[ny][nx])
						continue;

					// 그게 아니면 큐에 새로운 위치 넣어주기, 시간은 1 증가
					pq.add(new int[] { ny, nx, time + 1, tempTime + 1 });
					isVisted[ny][nx] = true;

				}
			}
			
			// pq에 넣어서 각 레벨이 끝나면 그때 넣어주기
			if (q.isEmpty()) {
				Collections.sort(pq, (arr1, arr2) -> (arr1[0] == arr2[0]) ? arr1[1] - arr2[1] : arr1[0] - arr2[0]);
				while (!pq.isEmpty()) {
					int arr[] = pq.remove(0);

					q.offer(arr);

				}
			}

		}

	}

	static class Shark {
		int size;
		int exp;
		int y;
		int x;

		public Shark(int y, int x) {
			this.exp = 0;
			this.size = 2;
			this.y = y;
			this.x = x;
		}
	}

}
