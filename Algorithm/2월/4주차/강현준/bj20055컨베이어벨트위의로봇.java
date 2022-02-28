package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj20055컨베이어벨트위의로봇 {

	static int N, K;
	static int[] arr;

	// static ArrayList<Belt> belts = new ArrayList<>();
	static LinkedList<Robot> robots = new LinkedList<>();

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[2 * N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2 * N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int zero = 0;
		int zeroIdx = 0;
		int rst = 0;
		while (true) {
			rst++;
			zeroIdx = (zeroIdx - 1 < 0) ? 2 * N - 1 : zeroIdx - 1;
			
//			for (int i = 0; i < robots.size(); i++) {
//				Robot current = robots.get(i);
//				current.position++;
//				if(current.position == N-1) {
//					robots.remove(i);
//					i--;
//				}
//			}

			for (int i = 0; i < robots.size(); i++) {
				Robot current = robots.get(i);
				
				
				
				current.position++;
				if(current.position == N-1) {
					robots.remove(i);
					i--;
					continue;
				}
				
				
				
				
				
				
				int nextIdx = (current.idx + 1) % (2 * N);
				if (i == 0) {
					if (arr[nextIdx] != 0) {
						current.position++;
						current.idx = nextIdx;
						arr[nextIdx]--;
						if(arr[nextIdx] == 0) zero++;
					}
				} else {
					Robot prev = robots.get(i-1);
					if (arr[nextIdx] != 0 && prev.position != current.position+1) {
						current.position++;
						current.idx = nextIdx;
						arr[nextIdx]--;
						if(arr[nextIdx] == 0) zero++;
					}
				}
				if(current.position == N -1) {
					robots.remove(i);
					i--;
				}
			}
			
			if(arr[zeroIdx] != 0) {
				Robot temp = new Robot();
				temp.position = 0;
				temp.idx = zeroIdx;
				robots.add(temp);
				arr[zeroIdx]--;
				if(arr[zeroIdx] == 0) zero++;
			}
			
			if(zero == K) break;
		}
		System.out.println(rst);
	}

	static class Robot {
		int position;
		int idx;
	}

}
