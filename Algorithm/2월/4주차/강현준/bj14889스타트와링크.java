package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj14889스타트와링크 {
	
	static int N, min = Integer.MAX_VALUE;
	static int[][] map;
	static int Start[], Link[];
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		Start = new int[N/2];
		Link = new int[N/2];
		
		// 입력
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		comb(0, 0);
		System.out.println(min);
	}
	
	static void comb(int cnt, int idx) {
		
		if( cnt == N/2 ) {
			
			makeLink();
			int sumStart = 0;
			int sumLink = 0;
			
			for (int i = 0; i < N/2; i++) {
				for (int j = 0; j < N/2; j++) {
					
					sumStart += map[Start[i]][Start[j]];
					sumLink += map[Link[i]][Link[j]];
					
				}
			}
			
			min = Math.min(min, Math.abs(sumStart-sumLink));
			return;
		}
		if( idx == N) return;
		
		Start[cnt] = idx;
		comb(cnt+1, idx+1);
		comb(cnt, idx+1);
		
		
	}
	
	static void makeLink() {
		boolean[] temp = new boolean[N];
		for(int i = 0; i < N/2; i++)
			temp[Start[i]] = true;
		
		int idx = 0;
		
		for(int i = 0; i < N; i++) {
			
			if (!temp[i]) Link[idx++] = i;
			
		}
		
	}

}
