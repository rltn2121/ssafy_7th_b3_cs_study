package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj13459구슬탈출 {

	static int N, M;
	static char[][] map;
	static Ball red = new Ball(), blue = new Ball() , hall = new Ball();

	static boolean isDone, isRed;

	static Queue<int[]> q = new ArrayDeque<int[]>();

	// 위 왼 오 아
	static int[] dy = { -1, 0, 0, 1 };
	static int[] dx = { 0, -1, 1, 0 };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];

		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		for (int i = 0; i < N; i++) {

			for (int j = 0; j < M; j++) {

				if (map[i][j] == 'R') {
					red.y = i;
					red.x = j;
					map[i][j] = '.';
				}
				if (map[i][j] == 'B') {
					blue.y = i;
					blue.x = j;
					map[i][j] = '.';
				}
				if (map[i][j] == 'O') {
					hall.y = i;
					hall.x = j;
					map[i][j] = '.';
				}

			}

		}
		//System.out.println(blue.y);
		bfs();

	}

	static void bfs() {
		//System.out.println(blue.y);
		int yRed = red.y;
		int yBlue = blue.y;
		int xRed = red.x;
		int xBlue = blue.x;
		
		for(int i = 0; i < 4; i++) {
			int nyRed = yRed;
			int nyBlue = yBlue;
			int nxRed = xRed;
			int nxBlue = xBlue;
			
			
			
		}

	}

	static class Ball {
		int y;
		int x;
	}

}
