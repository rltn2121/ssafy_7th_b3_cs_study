package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj8980_택배2 {
	static int N, C, M;
	//static int[][] boxes;
	static int[] removeBoxes;
	
	static Box[] boxes;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(br.readLine());
		
		//boxes = new int[N+1][N+1];
		removeBoxes = new int[N+1];
		boxes = new Box[M];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			//boxes[start][end] = num;
			boxes[i] = new Box(start, end, num);
		}
		
		Arrays.sort(boxes, (box1, box2)-> (box1.end == box2.end)? box1.start-box2.start : box1.end - box2.end);
//		for(Box box : boxes) {
//			System.out.println(box.start + " " + box.end + " "+ box.numOfBox);
//		}
		delivery();

	}
	static void delivery() {
		
		int tempNum = 0;
		int total = 0;
		for(int i = 1; i <= N; i++) {
			// 배달 내려주기
			tempNum -= removeBoxes[i];
			total += removeBoxes[i];
			removeBoxes[i] = 0;

			for(int idx = 0; idx < M; idx++) {
				if(tempNum > C) break;
				if(boxes[idx].numOfBox == 0 || boxes[idx].start < i) {
					continue;
				}
				if(tempNum + boxes[idx].numOfBox <= C) {
					removeBoxes[boxes[idx].end] += boxes[idx].numOfBox;
					tempNum += boxes[idx].numOfBox;
					boxes[idx].numOfBox = 0;
				}
				else {
					removeBoxes[boxes[idx].end] += (C-tempNum);
					tempNum = C;
					boxes[idx].numOfBox -= (C-tempNum);
				}
			}
		}
		System.out.println(total);
	}
	static void func() {
		int ans = 0;
		for(int i = 0; i < M; i++) {
			int start = boxes[i].start;
			int end = boxes[i].end;
			int box = boxes[i].numOfBox;
			
			int max = 0;
			boolean isOk = true;
			for(int j = start; j < end;  j++) {
				if(removeBoxes[j] >= C) {
					isOk = false;
					break;
				}
				max = Math.max(max, removeBoxes[j]);
			}
			if(isOk) {
				int temp = C - max;
				if(temp > box) {
					temp = box;
				}
				ans += temp;
				for(int j = start; j < end; j++) {
					removeBoxes[j] += temp;
				}
			}
		}
		System.out.println(ans);
	}
	
	
	static class Box{
		int start;
		int end;
		int numOfBox;
		public Box(int start, int end, int num) {
			this.start = start;
			this.end = end;
			this.numOfBox = num;
		}
	}
}
