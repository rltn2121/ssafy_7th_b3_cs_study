package com.ssafy.study;

import java.util.Scanner;

public class bj10799_쇠막대기 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		char[] arr = sc.next().toCharArray();
		
		for(int i = 0; i < arr.length-1; i++) {
			if(arr[i] == '(' && arr[i+1] == ')') {
				arr[i] = 'A';
				arr[i+1] = 'B';
			}
		}
		int rst = 0;
		int stickNum = 0;
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == 'A') {
				rst += stickNum;
			}
			else if(arr[i] == '(') {
				stickNum += 1;
			}
			else if(arr[i] == ')') {
				stickNum -= 1;
				rst += 1;
			}
			
		}
		System.out.println(rst);
	}

}
