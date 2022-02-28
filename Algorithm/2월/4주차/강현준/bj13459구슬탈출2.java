package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj13459구슬탈출2 {

	static int N, M;
	static char[][] map;
	static Ball red, blue, hall;

	static boolean isDone, isRed;
	
	static boolean[][][][] Visted;
	
	//static boolean[][] isVistedRed;
	//static boolean[][] isVistedBlue;
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
//		isVistedRed = new boolean[N][M];
//		isVistedBlue = new boolean[N][M];
		Visted = new boolean[N][M][N][M];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		for (int i = 0; i < N; i++) {

			for (int j = 0; j < M; j++) {

				if (map[i][j] == 'R') {
					red = new Ball(i, j, 0);
					map[i][j] = '.';
				}
				if (map[i][j] == 'B') {
					blue = new Ball(i, j, 0);
					map[i][j] = '.';
				}
				

			}

		}
		
		System.out.println(bfs());

	}
	
	static int bfs() {
		Queue<Now> q = new ArrayDeque<>();
		int count = 1;
		q.add(new Now(red, blue));
		Visted[red.y][red.x][blue.y][blue.x] = true;
		
		
		Ball nRed = null;
		Ball nBlue = null;
		
		while(!q.isEmpty()) {
			
			
			int size = q.size();
			while(size > 0) {
				//카운팅하기 위해 한 스텝마다 큐 사이즈 저장
				size--;
				
				Now temp = q.poll();
				Ball tempRed = temp.red;
				Ball tempBlue = temp.blue;
				//사방탐색
				for(int d = 0; d < 4; d++) {
					
					nRed = move(tempRed, d);
					nBlue = move(tempBlue, d);
					
					//파란 구슬 빠지면 실패, 컨티뉴로 아래조건 무시하기
					if(map[nBlue.y][nBlue.x] == 'O') {
						continue;
					}
					//빨간 구슬 빠지면 성공
					if(map[nRed.y][nRed.x] == 'O') {
						return 1;
					}
					
					// 빨강 파랑 같은 위치일 경우
					if(nBlue.y == nRed.y && nBlue.x == nRed.x) {
						// 더많이 이동한 놈 한칸 전으로
						if(nBlue.distance > nRed.distance) {
							nBlue.y -= dy[d];
							nBlue.x -= dx[d];
						}
						else {
							nRed.y -= dy[d];
							nRed.x -= dx[d];
						}
					}
					
					// 이미 방문했다면 컨티뉴
					if(Visted[nRed.y][nRed.x][nBlue.y][nBlue.x]) continue;
					
					Visted[nRed.y][nRed.x][nBlue.y][nBlue.x] = true;
					
					q.offer(new Now(nRed, nBlue));
					
				}
				
				
				
			}
			if(++count > 10) return 0;
		}
		return 0;
		
	}
	
	static Ball move(Ball ball, int d) {
		int ny = ball.y;
		int nx = ball.x;
		int distance = 0;
		while(map[ny + dy[d]][nx + dx[d]] != '#' && map[ny][nx] != 'O') {
			ny += dy[d];
			nx += dx[d];
			distance++;
		}
		return new Ball(ny, nx, distance);
	}
	
	static class Now {
		Ball red, blue;

		public Now(Ball red, Ball blue) {
			super();
			this.red = red;
			this.blue = blue;
		}
		
	}
	
	static class Ball {
		int y;
		int x;
		int distance;
		Ball(int y, int x, int distance){
			this.y = y;
			this.x = x;
			this.distance = distance;
		}
	}

}
