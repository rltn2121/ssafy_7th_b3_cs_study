package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class bj1202_보석도둑2 {
	
	static int N, K;
	
	static int[][] jewelry;
	static int[] bag;
	
	static int[] inbag;
	
	static PriorityQueue<Integer> Q = new PriorityQueue<>((a1, a2)-> a2-a1);
	
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
			//0번 인덱스 무게 , 1번 인덱스 값
			jewelry[i][0] = weight;
			jewelry[i][1] = value;
		}
		
		// 무게 내림차순으로 정렬, 같은 무게일때는 벨류 오름차순으로 정렬
		Arrays.sort(jewelry, (arr1, arr2)-> (arr1[0]==arr2[0])? arr2[1]-arr1[1] : arr1[0]-arr2[0]);
		
		for(int i = 0; i < K; i++) {
			bag[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(bag);
		func();
		
	}
	static void func() {
		long ans = 0;
		int idx = 0;
		// 가방 갯수만큼 탐색
		for(int i = 0; i < K; i++) {
			// 현재 가방 무게보다 작거나 같으면 보석 집어넣기 (벨류)
			// 무게순으로 정렬 되있기 때문에 신경안쓰고 다 집어넣으면 된다
			while(idx < N && jewelry[idx][0] <= bag[i]) {
				Q.offer(jewelry[idx][1]);
				idx++; // 다음보석으로
			}
			// 큐가 차있다면 큐에 있는 값 넣어주기
			// 해당 가방이 가질 수 있는 최대값이기 때문에 (우선순위큐 내림차순 정렬)피크 값 넣어준다.
			// 그리고 폴을 해주면 보석이 사라져서 중복으로 체크도 안된다..! (초기화 해주면 절대안됨!)
			if(!Q.isEmpty())
				ans += Q.poll();
		}
		System.out.println(ans);
	}
}
