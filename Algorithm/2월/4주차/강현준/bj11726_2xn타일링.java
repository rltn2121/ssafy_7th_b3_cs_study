package com.ssafy.study;

import java.util.Scanner;

public class bj11726_2xn타일링 {
	
	static long[] arr;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		arr = new long[n+1];
		fibo(n);
		System.out.println(arr[n]);
	}
	
	static void fibo(int n) {
		
		arr[0] = 1;
		arr[1] = 1;
		
		for(int i = 2; i <= n; i++) {
			arr[i] = (arr[i-2] + arr[i-1])%10007;
		}
		
	}
	
}