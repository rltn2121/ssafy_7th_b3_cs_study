package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class bj2156_포도주시식 {
	static int N;
	static int[] src;
	
	static int[][] ans;
	static int rst;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		ans = new int[N][2];
		src = new int[N];
		for(int i = 0; i < N; i++) {
			src[i] = Integer.parseInt(br.readLine());
		}
		dp(N);
		System.out.println(rst);
	}
	
	static void dp(int n) {
		if (n==1) {
			rst = src[0];
			return;
		}
		if (n==2) {
			rst = src[0] + src[1];
			return;
		}
		ans[0][0] = src[0];
		ans[0][1] = src[0];
		ans[1][0] = Math.max(src[0], src[1]);
		ans[1][1] = src[0] + src[1];
		
		for(int i = 2; i < N; i++) {
			ans[i][0] = Math.max(Math.max(ans[i-2][0], ans[i-2][1]) + src[i], ans[i-1][1]);
			ans[i][1] = Math.max(ans[i-1][0] + src[i], ans[i][0]);
			
			int max = Math.max(ans[i][0], ans[i][1]);
			rst = Math.max(rst, max);
		}
		
	}
}
