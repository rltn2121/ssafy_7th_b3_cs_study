package study0304;

import java.util.Scanner;

public class bj1256사전2 {
	
	static int N, M, K;
	static int length;
	static long[][] dp;
	static StringBuilder sb = new StringBuilder();
	static boolean flag = false;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		length = N + M;
		
		dp = new long[N+M+1][N+M+1];
		makeDp();
		for(int i = 0; i< N+M+1; i++) {
			for(int j = 0; j < N+M+1; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
		
		if(dp[N+M][M] < K) {
			System.out.println(-1);
			return;
		}
		func(N, M, K);
		System.out.println(sb.toString());
		
	}
	
	public static void makeDp() {
		
		for(int i = 0; i <= N+M; i++) {
			for(int j = 0; j <= i; j++) {
				if(i==j || j ==0) dp[i][j] = 1;
				else dp[i][j] = dp[i-1][j] + dp[i-1][j-1];
				if(dp[i][j] > 1000000000) dp[i][j] = 1000000000;
			}
		}
	}
	public static void func(int n, int m, long k) {
		if(n==0 && m == 0) return;
		
		if(n==0) {
			while(m!=0) {
				sb.append("z");
				m--;
			}
			return;
		}
		if(m==0) {
			while(n!=0) {
				sb.append("a");
				n--;
			}
			return;
		}
		if(k <= dp[n+m-1][m]) {
			sb.append("a");
			func(n-1, m, k);
		} else {
			sb.append("z");
			func(n, m-1, k - dp[n+m-1][m]);
		}
		
	}
}
