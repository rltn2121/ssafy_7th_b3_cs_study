package ps_java;


import java.io.*;
import java.util.*;

public class b_14499 {
	static int N,M,x,y,K,arr[][],cmd[],dice[] = new int[6];
	static int dx[] = {0,0,-1,1};
	static int dy[] = {1,-1,0,0};
	public static void main(String[] args) throws Exception{
		input();
		for(int dir : cmd) 
			func(dir);
	}
	
	static void func(int dir) {
		int nx = x+dx[dir];
		int ny = y+dy[dir];
		if(nx<0||nx>=N||ny<0||ny>=M) return;
		
		// 주사위 돌리기
		roll(dir);
		
		// 값 계산
		if(arr[nx][ny] == 0) {
			arr[nx][ny] = dice[5];
		}
		else {
			dice[5] = arr[nx][ny];
			arr[nx][ny] = 0;
		}
		System.out.println(dice[2]);
		x = nx;
		y = ny;
	}

	static void roll(int dir) {
		// 동쪽
		if(dir == 0) {
			int temp = dice[2];
			dice[2] = dice[1];
			dice[1] = dice[5];
			dice[5] = dice[3];
			dice[3] = temp;
		}
		// 서쪽
		else if(dir == 1) {
			int temp = dice[2];
			dice[2] = dice[3];
			dice[3] = dice[5];
			dice[5] = dice[1];
			dice[1] = temp;
		}
		// 북쪽
		else if(dir == 2) {
			int temp = dice[2];
			dice[2] = dice[4];
			dice[4] = dice[5];
			dice[5] = dice[0];
			dice[0] = temp;
		}
		// 남쪽
		else if(dir == 3) {
			int temp = dice[2];
			dice[2] = dice[0];
			dice[0] = dice[5];
			dice[5] = dice[4];
			dice[4] = temp;
		}
	}

	static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		cmd = new int[K];
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<M; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<K; i++)
			cmd[i] = Integer.parseInt(st.nextToken()) - 1;
	}
}	
