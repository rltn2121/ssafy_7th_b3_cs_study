package study0304;

import java.util.Scanner;

public class bj1256사전 {
	
	static int N, M, K;
	static int length;
	static int[] arr;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		length = N + M;
		arr = new int[length];
		for(int i = 0; i < N; i++) {
			arr[i] = -1;
		}
		int count = 1;
		StringBuilder sb = new StringBuilder();
		while(true) {
			
			if(count == K) {
				
				for(int x : arr) {
					if( x == -1) sb.append("a");
					else sb.append("z");
				}
				System.out.println(sb.toString());
				return;
			}
			
			count++;
			if(!np()) break;
		}
		System.out.println(-1);
	}
	
	static boolean np() {
		
		
		int i = length - 1;
		
		while(i > 0 && arr[i-1] >= arr[i]) i--;
		
		if(i==0) return false;
		
		int j = length - 1;
		
		while(arr[i-1] >= arr[j]) j--;
		
		swap(i-1, j);
		
		int k = length - 1;
		
		while(i < k) swap(i++, k--);
		
		return true;
	}
	
	
	static void swap(int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
