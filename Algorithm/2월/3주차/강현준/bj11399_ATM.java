package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj11399_ATM {
	
	static int N;
	static int[] time;
	static PriorityQueue<Integer> Q = new PriorityQueue<Integer>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			Q.offer(Integer.parseInt(st.nextToken()));
		}
		
		int rst = 0;
		int temp = 0;
		while(!Q.isEmpty()) {
			temp += Q.poll();
			rst += temp;
		}
		System.out.println(rst);
	}

}
