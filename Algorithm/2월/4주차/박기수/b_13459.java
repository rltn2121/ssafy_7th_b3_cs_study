package ps_java;


import java.io.*;
import java.util.*;

public class b_13459 {
	static int N,M,cnt;
	static char[][] arr;
	static boolean end = false;
	static Point red, blue, hole;
	static Queue<char[][]> q = new ArrayDeque<>();
	public static void main(String[] args) throws Exception{
		input();
		
		q.add(arr);
		int cnt = 1;
		while(cnt<=10) {
			int size = q.size();
			while(size-->0) {
				char[][] map = q.poll();
				if(moveTop(map)) {
					end = true;
					break;
				}
				if(moveRight(map)) {
					end = true;
					break;
				}
				if(moveDown(map)) {
					end = true;
					break;
				}if(moveLeft(map)) {
					end = true;
					break;
				}
			}
			if(end) break;
			cnt++;
		}
		System.out.println(end?1:0);
	}
	static boolean moveTop(char[][] input) {
		char[][] map = getNewMap(input);
		int move = 0;
		
		boolean success = false;
		// 빨간색이 위에 있어서 빨간색 먼저 옮기고 파란색 옮기기
		if(red.y == blue.y && red.x < blue.x) {
			while(red.x>0) {
				// 빈 칸이면
				if(map[red.x-1][red.y]=='.') {
					map[red.x][red.y] = '.';
					map[red.x-1][red.y]='R';
					red.x--;
					move++;
				}
				
				// 구멍이면
				else if(map[red.x-1][red.y]=='O') {
					map[red.x][red.y] = '.';
					success = true;
					break;
				}
				else break;
			}
			
			while(blue.x>0) {
				// 빈 칸이면
				if(map[blue.x-1][blue.y]=='.') {
					map[blue.x][blue.y] = '.';
					map[blue.x-1][blue.y]='B';
					blue.x--;
					move++;
				}
				
				// 구멍이면
				else if(map[blue.x-1][blue.y]=='O') {
					map[blue.x][blue.y] = '.';
					return false;
				}
				else break;
			}
		}
		
		else {
			while(blue.x>0) {
				// 빈 칸이면
				if(map[blue.x-1][blue.y]=='.') {
					map[blue.x][blue.y] = '.';
					map[blue.x-1][blue.y]='B';
					blue.x--;
					move++;
				}
				
				// 구멍이면
				else if(map[blue.x-1][blue.y]=='O') {
					map[blue.x][blue.y] = '.';
					return false;
				}
				else break;
			}
			
			while(red.x>0) {
				// 빈 칸이면
				if(map[red.x-1][red.y]=='.') {
					map[red.x][red.y] = '.';
					map[red.x-1][red.y]='R';
					red.x--;
					move++;
				}
				
				// 구멍이면
				else if(map[red.x-1][red.y]=='O') {
					map[red.x][red.y] = '.';
					success = true;
					break;
				}
				else break;
			}
		}
		if(move>0)
			q.add(map);
		return success;
	}
	
	static boolean moveRight(char[][] input) {
		char[][] map = getNewMap(input);
		int move = 0;
		boolean success = false;
		
		// 빨간색이 오른쪽에 있어서 빨간색 먼저 옮기고 파란색 옮기기
		if(red.x == blue.x && red.y > blue.y) {
			while(red.y<M-1) {
				// 빈 칸이면
				if(map[red.x][red.y+1]=='.') {
					map[red.x][red.y] = '.';
					map[red.x][red.y+1]='R';
					red.y++;
					move++;
				}
				
				// 구멍이면
				else if(map[red.x][red.y+1]=='O') {
					map[red.x][red.y] = '.';
					success = true;
					break;
				}
				else break;
			}
			
			while(blue.y<M-1) {
				// 빈 칸이면
				if(map[blue.x][blue.y+1]=='.') {
					map[blue.x][blue.y] = '.';
					map[blue.x][blue.y+1]='B';
					blue.y++;
					move++;
				}
				
				// 구멍이면
				else if(map[blue.x][blue.y+1]=='O') {
					map[blue.x][blue.y] = '.';
					return false;
				}
				else break;
			}
		}
		else {
			while(blue.y<M-1) {
				// 빈 칸이면
				if(map[blue.x][blue.y+1]=='.') {
					map[blue.x][blue.y] = '.';
					map[blue.x][blue.y+1]='B';
					blue.y++;
					move++;
				}
				
				// 구멍이면
				else if(map[blue.x][blue.y+1]=='O') {
					map[blue.x][blue.y] = '.';
					return false;
				}
				else break;
			}
			while(red.y<M-1) {
				// 빈 칸이면
				if(map[red.x][red.y+1]=='.') {
					map[red.x][red.y] = '.';
					map[red.x][red.y+1]='R';
					red.y++;
					move++;
				}
				
				// 구멍이면
				else if(map[red.x][red.y+1]=='O') {
					map[red.x][red.y] = '.';
					success = true;
					break;
				}
				else break;
			}
		}
		if(move>0)
			q.add(map);
		return success;
	}
	
	static boolean moveDown(char[][] input) {
		char[][] map = getNewMap(input);
		int move = 0;
		
		boolean success = false;
		// 빨간색이 밑에 있어서 빨간색 먼저 옮기고 파란색 옮기기
		if(red.y == blue.y && red.x > blue.x) {
			while(red.x<N-1) {
				// 빈 칸이면
				if(map[red.x+1][red.y]=='.') {
					map[red.x][red.y] = '.';
					map[red.x+1][red.y]='R';
					red.x++;
					move++;
				}
				
				// 구멍이면
				else if(map[red.x+1][red.y]=='O') {
					map[red.x][red.y] = '.';
					success = true;
					break;
				}
				else break;
			}
			
			while(blue.x<N-1) {
				// 빈 칸이면
				if(map[blue.x+1][blue.y]=='.') {
					map[blue.x][blue.y] = '.';
					map[blue.x+1][blue.y]='B';
					blue.x++;
					move++;
				}
				
				// 구멍이면
				else if(map[blue.x+1][blue.y]=='O') {
					map[blue.x][blue.y] = '.';
					return false;
				}
				else break;
			}
		}
		
		else {
			while(blue.x<N-1) {
				// 빈 칸이면
				if(map[blue.x+1][blue.y]=='.') {
					map[blue.x][blue.y] = '.';
					map[blue.x+1][blue.y]='B';
					blue.x++;
					move++;
				}
				
				// 구멍이면
				else if(map[blue.x+1][blue.y]=='O') {
					map[blue.x][blue.y] = '.';
					return false;
				}
				else break;
			}
			
			while(red.x<N-1) {
				// 빈 칸이면
				if(map[red.x+1][red.y]=='.') {
					map[red.x][red.y] = '.';
					map[red.x+1][red.y]='R';
					red.x++;
					move++;
				}
				
				// 구멍이면
				else if(map[red.x+1][red.y]=='O') {
					map[red.x][red.y] = '.';
					success = true;
					break;
				}
				else break;
			}
		}
		if(move>0)
			q.add(map);
		return success;
	}
	
	static boolean moveLeft(char[][] input) {
		char[][] map = getNewMap(input);
		int move = 0;
		boolean success = false;
		
		// 빨간색이 왼쪽에 있어서 빨간색 먼저 옮기고 파란색 옮기기
		if(red.x == blue.x && red.y < blue.y) {
			while(red.y>0) {
				// 빈 칸이면
				if(map[red.x][red.y-1]=='.') {
					map[red.x][red.y] = '.';
					map[red.x][red.y-1]='R';
					red.y--;
					move++;
				}
				
				// 구멍이면
				else if(map[red.x][red.y-1]=='O') {
					map[red.x][red.y] = '.';
					success = true;
					break;
				}
				else break;
			}
			
			while(blue.y>0) {
				// 빈 칸이면
				if(map[blue.x][blue.y-1]=='.') {
					map[blue.x][blue.y] = '.';
					map[blue.x][blue.y-1]='B';
					blue.y--;
					move++;
				}
				
				// 구멍이면
				else if(map[blue.x][blue.y-1]=='O') {
					map[blue.x][blue.y] = '.';
					return false;
				}
				else break;
			}
		}
		else {
			while(blue.y>0) {
				// 빈 칸이면
				if(map[blue.x][blue.y-1]=='.') {
					map[blue.x][blue.y] = '.';
					map[blue.x][blue.y-1]='B';
					blue.y--;
					move++;
				}
				
				// 구멍이면
				else if(map[blue.x][blue.y-1]=='O') {
					map[blue.x][blue.y] = '.';
					return false;
				}
				else break;
			}
			while(red.y>0) {
				// 빈 칸이면
				if(map[red.x][red.y-1]=='.') {
					map[red.x][red.y] = '.';
					map[red.x][red.y-1]='R';
					red.y--;
					move++;
				}
				
				// 구멍이면
				else if(map[red.x][red.y-1]=='O') {
					map[red.x][red.y] = '.';
					success = true;
					break;
				}
				else break;
			}
		}
		if(move>0)
			q.add(map);
		return success;
	}
	
	static char[][] getNewMap(char[][] input){
		char[][] map = new char[N][M];
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				map[i][j] = input[i][j];
				if(map[i][j] == 'R')
					red = new Point(i,j);
				else if(map[i][j]=='B')
					blue = new Point(i,j);
				else if(map[i][j]=='O')
					hole = new Point(i,j);
			}
		}
		return map;
	}
	static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new char[N][M];
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = st.nextToken().toCharArray();
		}
		br.close();
	}
	static class Point{
		int x;
		int y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}	
