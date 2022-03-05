package ps_java;


import java.io.*;
import java.util.*;

public class b_17779 {
	static int N,arr[][];
	static int sum[];
	static int ans = Integer.MAX_VALUE;
	static int total;
	static Point top;
	static Point bottom;
	static Point left;
	static Point right;
	public static void main(String[] args) throws Exception{
		input();
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				if(j==0||j>=N||i>=N) continue;
				for(int d1 = 1; d1<N; d1++) {
					if(j-d1<0) break; 
					for(int d2 = 1; d2<N; d2++) {
						int[][] ret = div(i,j,d1,d2);
						if(ret == null) continue;
						calc(ret);
					}
				}
			}
		}
		System.out.println(ans);
	}
	static boolean check(int nx, int ny) {
		return (nx<0||nx>=N||ny<0||ny>=N);
	}
	
	static int[][] div(int x, int y, int d1, int d2){
		int map[][] = new int[N][N];
		
		
		top = new Point(x,y);
		int cnt = 1;
		map[x][y] = 5;
		while(cnt<=d1) {
			int nx = ++x;
			int ny = --y;
			 
			if(check(nx,ny)) return null;
			map[nx][ny] = 5;
			cnt++;
		}
//		--x;
//		++y;
		left = new Point(x,y);
		cnt = 1;
		map[x][y] = 5;
		while(cnt<=d2) {
			int nx = ++x;
			int ny = ++y;
			if(check(nx,ny)) return null;
			map[nx][ny] = 5;
			cnt++;
		}
//		--x;
//		--y;
		bottom = new Point(x,y);
		cnt = 1;
		map[x][y] = 5;
		while(cnt<=d1) {
			int nx = --x;
			int ny = ++y;
			if(check(nx,ny)) return null;
			map[nx][ny] = 5;
			cnt++;
		}
//		++x;
//		--y;
		right = new Point(x,y);
		cnt = 1;
		map[x][y] = 5;
		while(cnt<=d2) {
			int nx = --x;
			int ny = --y;
			if(check(nx,ny)) return null;
			map[nx][ny] = 5;
			cnt++;
		}
		return map;
	}
	
	static void calc(int[][] map) {
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		sum = new int[6];
		for(int i = 0; i<left.x; i++) {
			for(int j = 0; j<=top.y; j++) {
				if(map[i][j] == 5) break;
				sum[1]+=arr[i][j];
			}
		}
		
		for(int i = 0; i<= right.x; i++) {
			for(int j = N-1; j>top.y; j--) {
				if(map[i][j] == 5) break;
				sum[2]+= arr[i][j];
			}
		}
		for(int i = left.x; i<N; i++) {
			for(int j = 0; j<bottom.y; j++) {
				if(map[i][j] == 5) break;
				sum[3]+= arr[i][j];
			}
		}
		for(int i = right.x+1; i<N; i++) {
			for(int j = N-1; j>=bottom.y; j--) {
				if(map[i][j] == 5) break;
				sum[4]+= arr[i][j];
			}
		}
		
		sum[5] = total;
		for(int i = 1; i<5; i++)
			sum[5]-=sum[i];
		
		for(int i = 1; i<=5; i++) {
			max = Math.max(max, sum[i]);
			min = Math.min(min, sum[i]);
		}
		ans = Math.min(ans, max-min);
	}
	static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				total += arr[i][j];
			}
		}
		br.close();
	}
	
	static class Point{
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}	
