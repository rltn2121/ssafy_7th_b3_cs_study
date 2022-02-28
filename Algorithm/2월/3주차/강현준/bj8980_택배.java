package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj8980_택배 {
	static int N, C, M;
	static int[][] boxes;
	static int[] removeBoxes;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(br.readLine());
		
		boxes = new int[N+1][N+1];
		removeBoxes = new int[N+1];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			boxes[start][end] = num;
		}
		
		func();
	}
	
	static void func() {
		
		int tempNum = 0;
		int sumNum = 0;
		
		for(int i = 1; i <= N; i++) {
			// 현재 위치에 있는거 내림
			tempNum -= removeBoxes[i];
			sumNum += removeBoxes[i];
			System.out.println(sumNum + "  i번쨰");
			for(int j = i+1; j <= N; j++) {
				// 배송할 위치에 있는거 넣음
				
				// 만약 용량이 꽉차면 다음번호로 가야댐
				if(tempNum == C) break;
				if(tempNum+boxes[i][j] > C) {
					removeBoxes[j] += C - tempNum;
					tempNum = C;
				}
				else {
					tempNum += boxes[i][j];
					removeBoxes[j] += boxes[i][j];
				}	
			}
			
		}
		
		System.out.println(sumNum);
	}
	
}
