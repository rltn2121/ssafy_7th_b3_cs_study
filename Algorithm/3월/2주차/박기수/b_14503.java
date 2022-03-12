package ps_java;


import java.io.*;
import java.util.*;

public class b_14503 {
	static Node robot;
	static int N,M,map[][],cnt;
	static int dx[] = {-1,0,1,0};
	static int dy[] = {0,1,0,-1};
	static boolean visited[][];
	public static void main(String[] args) throws Exception{
		input();
		while(true) {
			// 1. 현재 위치를 청소한다.
			if(!visited[robot.x][robot.y]) {
				cnt++;
				visited[robot.x][robot.y] = true;
			}
			
			int i = 0;
			int nx = 0;
			int ny = 0;
			// 2. 현재 위치에서 현재 방향을 기준으로 왼쪽 방향부터 차례대로 인접한 칸을 탐색한다.
			for(i = 0; i<4; i++) {
				int leftDir = (robot.d -1 + 4) % 4;
				nx = robot.x+dx[leftDir];
				ny = robot.y+dy[leftDir];
				
				if(map[nx][ny] == 0 && !visited[nx][ny]) {
					robot.d = leftDir;
					robot.x = nx;
					robot.y = ny;
					break;
				} else {
					robot.d = leftDir;
				}
			}
			
			if(i == 4 || map[nx][ny] == 1) {
				int backDir = (robot.d + 2) % 4;
				nx = robot.x+dx[backDir];
				ny = robot.y+dy[backDir];
				// 후진 못함
				if(map[nx][ny] == 1) break;
				// 한 칸 후진
				robot.x = nx;
				robot.y = ny;
			}
		}
		System.out.println(cnt);
	}
	
	static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		robot = new Node(x,y,d);
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<M; j++) 
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		br.close();
	}
	static class Node{
		int x;
		int y;
		int d;
		public Node(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
}	
