package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class bj2580스도쿠 {

	static int[][] map = new int[9][9];
	static ArrayList<int[]> arr = new ArrayList<int[]>();
	
	static boolean isDone;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0)
					arr.add(new int[] { i, j });
			}
		}
		func(0);

	}

	static void func(int num) {
		if(isDone) return;
		
		if (num >= 1 && !isOK(num-1)) {
			return;
		}

		if (num == arr.size()) {
			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < 9; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			
			
			isDone = true;
			return;
		}

		for (int i = 1; i <= 9; i++) {

			int y = arr.get(num)[0];
			int x = arr.get(num)[1];
			map[y][x] = i;
			func(num + 1);
			map[y][x] = 0;

		}

	}

	static boolean isOK(int num) {
		int y = arr.get(num)[0];
		int x = arr.get(num)[1];

		int addNum = map[y][x];

		for (int i = 0; i < 9; i++) {

			if (i != x && addNum == map[y][i])
				return false;
			if (i != y && addNum == map[i][x])
				return false;
		}

		int startX = -1;
		int endX = -1;

		if (x % 3 == 0) {
			startX = x;
			endX = x + 2;
		}
		if (x % 3 == 1) {
			startX = x - 1;
			endX = x + 1;
		}
		if (x % 3 == 2) {
			startX = x - 2;
			endX = x;
		}

		int startY = -1;
		int endY = -1;

		if (y % 3 == 0) {
			startY = y;
			endY = y + 2;
		}
		if (y % 3 == 1) {
			startY = y - 1;
			endY = y + 1;
		}
		if (y % 3 == 2) {
			startY = y - 2;
			endY = y;
		}

		for (int i = startY; i <= endY; i++) {
			for (int j = startX; j <= endX; j++) {
				if (i == y && j == x)
					continue;
				if (map[i][j] == addNum)
					return false;
			}
		}

		return true;
	}
}
