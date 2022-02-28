package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class bj17837_새로운게임2 {
	
	static int N, K;
	static mapInfo[][] map;
	
	static int[] dy = { 100, 0, 0, -1, 1 };
	static int[] dx = { 100, 1, -1, 0, 0 };
	
	static Chess[] chesses;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		
		map = new mapInfo[N+1][N+1];
		chesses = new Chess[K+1];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				int color = Integer.parseInt(st.nextToken());
				map[i][j] = new mapInfo(color);
			}
		}
		
		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			Chess temp = new Chess(y, x, d);
			chesses[i] = temp;
			map[y][x].arr.add(i);
			
		}

		int count = 0;
		while(count <= 1000) {
			count++;
			
			for(int i = 1; i <= K; i++) {
				Chess temp = chesses[i];
				int y = temp.y;
				int x = temp.x;
				int d = temp.d;
				int ny = y + dy[d];
				int nx = x + dx[d];
				// 파란색이거나 범위 벗어나면 방향 반대로
				if( ny < 1 || nx < 1 || ny >= N+1 || nx >= N+1 || map[ny][nx].color == 2) {
					if(d == 1) temp.d = 2;
					else if(d == 2) temp.d = 1;
					else if(d == 3) temp.d = 4;
					else if(d == 4) temp.d = 3;
					ny = y + dy[temp.d];
					nx = x + dx[temp.d];
					// 그래도 파란색이면 컨티뉴 해서 정지 시켜줌
					if(ny < 1 || nx < 1 || ny >= N+1 || nx >= N+1 ||map[ny][nx].color == 2) continue;
				}

				// 무빙무빙
				ArrayList<Integer> movingArr = move(temp, i);
				// 흰색이면 고대로 붙여주기
				if(map[ny][nx].color == 0) {
					map[ny][nx].arr.addAll(movingArr);
				}
				// 뻘건색이면 뒤집어서 붙여주기
				else if(map[ny][nx].color == 1) {
					for(int j = movingArr.size()-1; j >= 0; j--) {
						map[ny][nx].arr.add(movingArr.get(j));
					}
				}
				// 뉴 지점의 어레이 사이즈가 4이상이면 종료
				if(map[ny][nx].arr.size()>=4) {
					System.out.println(count);
					return;
				}
			}
			
		}
		System.out.println(-1);
	}
	
	static ArrayList<Integer> move(Chess temp, int num) {
		ArrayList<Integer> rst = new ArrayList<Integer>();
		
		int y = temp.y;
		int x = temp.x;
		int originSize = map[y][x].arr.size();
		int start = map[y][x].arr.indexOf(num);
		for(int i = start; i < originSize; i++) {
			rst.add(map[y][x].arr.remove(start));
		}

		int ny = y + dy[temp.d];
		int nx = x + dx[temp.d];
		
		if(!rst.isEmpty()) {
			for(int i = 0; i < rst.size(); i++) {
				chesses[rst.get(i)].y = ny;
				chesses[rst.get(i)].x = nx;
			}
		}
		return rst;
	}
	
	static class Chess{
		int y, x, d;
		public Chess(int y, int x, int d) {
			this.y = y;
			this.x = x;
			this.d = d;
		}
	}

	static class mapInfo{
		int color;
		ArrayList<Integer> arr;
		public mapInfo(int color) {
			this.color = color;
			this.arr = new ArrayList<Integer>();
		}
	}
}
