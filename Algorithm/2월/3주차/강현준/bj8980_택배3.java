package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj8980_택배3 {
	static int N, C, M;
	static int[] removeBoxes;
	
	static Box[] boxes;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(br.readLine());
		
		removeBoxes = new int[N+1];
		boxes = new Box[M];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			boxes[i] = new Box(start, end, num);
		}
		
		Arrays.sort(boxes, (box1, box2)-> (box1.end == box2.end)? box1.start-box2.start : box1.end - box2.end);

		func();

	}
	

	static void func() {
		int ans = 0;
		// 도착지점이 빠른 박스부터 옮긴다.
		for(int i = 0; i < M; i++) {
			int start = boxes[i].start;
			int end = boxes[i].end;
			int box = boxes[i].numOfBox;
			
			int max = 0;
			boolean isOk = true;
			
			for(int j = start; j < end;  j++) {
				// 그 지점에 들고 있을 박스가 트럭 용량보다 많으면 멈춰...!
				if(removeBoxes[j] >= C) {
					isOk = false;
					break;
				}
				// 최대 박스 용량 찾아주기
				max = Math.max(max, removeBoxes[j]);
			}
			// 내려야할 박스가 트럭 용량보다 안 많으면 다시 넣어주쟈
			if(isOk) {
				// 최대 경우수(남은 용량)로 잡아주기
				int temp = C - max;
				// 남은 용량보다 박스가 작으면 실을 박스의 수 = 박스
				if(temp > box) {
					temp = box;
				}
				// 출력할 값에 더 해준다
				ans += temp;
				// 더해준 값  해당 지점에서 갖고 있을 박스양에 더해줌
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
