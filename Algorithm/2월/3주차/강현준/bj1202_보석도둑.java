package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class bj1202_보석도둑 {
	
	static int N, K;
	
	static int[][] jewelry;
	static int[] bag;
	
	static int[] inbag;
	
	static PriorityQueue<Integer> Q = new PriorityQueue<>(Comparator.reverseOrder());
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		jewelry = new int[N][2];
		bag = new int[K];
		inbag = new int[K];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			jewelry[i][0] = weight;
			jewelry[i][1] = value;
		}
		Arrays.sort(jewelry, (arr1, arr2)-> (arr1[0]==arr2[0])? arr2[1]-arr1[1] : arr1[0]-arr2[0]);
		
		for(int i = 0; i < K; i++) {
			bag[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(bag);
		func();
		
	}
	static void func() {
		int idx = 0;
		int rst = 0;
		for(int i = 0; i < K; i++) {
			while(idx < N &&jewelry[idx][0] <= bag[i]) {
				Q.offer(jewelry[idx][1]);
				idx++;
				//System.out.println(jewelry[idx][0] +" " +jewelry[idx][1]);
				//System.out.println(idx);
			}
			if(!Q.isEmpty()) {
				rst += Q.poll();
			}
			
			
			
		}
		System.out.println(rst);
	}
}
