package study0305;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;
// 비어 있으면 .
// 물이 차있다 *
// 돌은 X

// 비버 굴 D
// 고슴도치 S

// S -> D 로 가는 최소 시간
public class bj3055_탈출 {
	
	static char[][] map;
	static int R, C;
	
	static int dY, dX, sY, sX;
	
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	
	static ArrayList<int[]> waterList = new ArrayList<int[]>();
	
	static int rst = -1;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][];
		for(int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(map[i][j] == 'D') {
					dY = i;
					dX = j;
				}
				if(map[i][j] == 'S') {
					sY = i;
					sX = j;
				}
				if(map[i][j] == '*') {
					waterList.add(new int[] {i, j});
				}
			}
		}
		bfs();
		if(rst == -1) System.out.println("KAKTUS");
		else System.out.println(rst);
	}
	
	static void water() {
		ArrayList<int[]> temp = new ArrayList<int[]>();
		boolean[][] isVisited = new boolean[R][C];
		for(int[] e : waterList) {
			int y = e[0];
			int x = e[1];
			isVisited[y][x] = true;
			for(int d = 0; d < 4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];
				if(ny < 0 || nx < 0 || ny >= R || nx >=C) continue;
				if(map[ny][nx] != '.') continue;
				if(isVisited[ny][nx]) continue;
				isVisited[ny][nx] = true;
				temp.add(new int[] {ny, nx});
			}
		}
		waterList.clear();
		waterList.addAll(temp);
		for(int[] e : waterList) {
			int y = e[0];
			int x = e[1];
			map[y][x] = '*';
		}
	}
	static void bfs() {
		int y = sY;
		int x = sX;
		boolean[][] isVisited = new boolean[R][C];
		isVisited[sY][sX] = true;
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {y, x, 0});
		int idx = -1;
		while(!q.isEmpty()) {
			int temp[] = q.poll();
			
			if(idx !=  temp[2]) {
				water();
				idx = temp[2];
			}
			//printMap();
			
			
			int cy = temp[0];
			int cx = temp[1];
			//System.out.println(cy + " " + cx + " " + temp[2]);
			if(cy == dY && cx == dX) {
				rst = temp[2];
				return;
			}
			int cnt = temp[2];
			for(int d = 0; d < 4; d++) {
				int ny = cy + dy[d];
				int nx = cx + dx[d];
				if(ny < 0 || nx < 0 || ny >= R || nx >=C) continue;
				if(isVisited[ny][nx]) continue;
				if(map[ny][nx] == '*' || map[ny][nx] == 'X') continue;
				isVisited[ny][nx] = true;
				q.add(new int[] {ny, nx, cnt+1});
			}
		}
		
		
	}
	static void printMap() {
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
	
}
