package com.ssafy.study;

import java.util.Scanner;

public class bj5904Moo게임 {

	static int N;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		//sb.append("moo");
		
		//makeMoo(N, 0);
		//System.out.println(sb);
		//System.out.println(findK(N));
		int idx = 0;
		int k = 0;
		
		while(true) {
			if(N > findK(idx++)) {
				k = idx;
				continue;
			}
			break;
		}
		//System.out.println(k);
		func(N, k);
	}
	
	static void func(int N, int k) {
		
		if(k==0) {
			if(N==1) {
				System.out.println("m");
			}
			else {
				System.out.println("o");
			}
			return;
		}
		int temp = findK(k-1);
		if(N == temp + 1 ) {
			System.out.println("m");
			return;
		}
		else if(temp + 1 < N && N <= temp + k + 3) {
			System.out.println("o");
			return;
		}
		else if (N < temp + 1) {
			func(N, k - 1);
		}
		else {
			func(N-temp-k-3, k-1);
		}
		
	}
	
	static int findK (int k) {
		if(k==0) return 3;
		
		return findK(k-1) + (k+3) + findK(k-1);
	}
	
	static void makeMoo(int n, int k) {
		
		
		
		if(n <= sb.length()) {
			System.out.println(sb.charAt(n-1));
			return;
		}
		String s = sb.toString();
		sb.append("m");
		for(int i = 0; i <= k+2; i++) {
			sb.append("o");
		}
		sb.append(s);
		
		makeMoo(n, k+1);
		
	}

}
