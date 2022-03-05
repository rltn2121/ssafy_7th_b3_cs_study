package ps_java;


import java.io.*;
import java.util.*;

public class b_1890 {
	static int N,arr[][];
	static long dp[][];
	static int[] dx = {0,1};
	static int[] dy = {1,0};
	public static void main(String[] args) throws Exception{
		input();
		dp[0][0] = 1;
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				if(dp[i][j] == 0 || arr[i][j] == 0)
					continue;
				int nx = i+arr[i][j];
				if(nx<N)
					dp[nx][j]+=dp[i][j];
				int ny = j+arr[i][j];
				if(ny<N)
					dp[i][ny]+=dp[i][j];
			}
		}
		System.out.println(dp[N-1][N-1]);
	}
	

	 
	static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		dp = new long[N][N];
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		br.close();
	}
	
	static class Node{
		int x;
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}	
