package study0304;

import java.util.Scanner;

public class bj9935문자열폭발 {
	
	static char[] arr, ex, stack = new char[1000001];
	static int sIdx;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		arr = sc.next().toCharArray();
		ex = sc.next().toCharArray();
		for(int i = 0; i < arr.length; i++) {
			stack[sIdx++] = arr[i];
			if(arr[i] == ex[ex.length-1]) {
				func();
			}
		}
		StringBuilder sb = new StringBuilder();
		if(sIdx==0) {
			System.out.println("FRULA");
			return;
		}
		for(int i = 0; i < sIdx; i++) {
			sb.append(stack[i]);
		}
		System.out.println(sb.toString());
	}
	static void func() {
		if(sIdx < ex.length) return;
		int temp = -1;
		for(int i = ex.length-1; i >= 0; i--) {
			if(ex[i] != stack[--sIdx]) {
				temp = i;
				break;
			}
		}
		if(temp != -1) {
			sIdx++;
			for(int i = temp+1; i < ex.length; i++) {
				stack[sIdx++] = ex[i]; 
			}
		}
	}
}
