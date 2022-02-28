
package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj20304_비밀번호제작 {
	static int N, M;
	static int[] arr;
	
	static Queue<Integer> q = new ArrayDeque<Integer>();
	
	static int ans;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 숫자 최대 경우 만큼 배열 생성
		arr = new int[1000001];
		// -1 로 배열 채워주기
		Arrays.fill(arr, -1);
		
		// 해킹범 암호 큐에 넣어주기 & 해킹범 암호는 보안성 무조건 0 이므로 배열값에도 0 넣어주기
		for(int i = 0; i < M; i ++) {
			int x = Integer.parseInt(st.nextToken());
			arr[x] = 0;
			q.offer(x);
		}
		
		// 큐가 안비어있을 때까지 체크해주기
		// BFS
		while(!q.isEmpty()) {
			
			// 체크해볼 암호 저장
			int temp = q.poll();
			
			// 2^20 = 1,000,000 이므로 20까지 체크
			for(int i = 0; i <= 20; i++) {
				
				// 새 비밀번호는 일단 원래 비밀번호랑 보안성 1차이 나게 잡아주기
				// 따라서 temp^(1<<i)로 하면 보안성 1차이나는 모든 경우를 다 체크 가능
				int tempNew = temp^(1<<i);
				
				// 새 비밀번호가 최대 비밀번호보다 초과하거나
				// 이미 탐색한 비밀번호면 넘어가기 (왜냐하면 보안성이 이미 체크 됐기 때문)
				if(tempNew > N || arr[tempNew] != -1) continue;
				
				// 새비밀번호의 보안성은 1차이나므로 원래 비밀번호 보안성에 +1
				arr[tempNew] = arr[temp] + 1;
				
				// 새비밀번호 큐에 넣어주기
				q.offer(tempNew);
				
				// 새비밀번호의 보안성 미리 체크 해주기 (한번 탐색 안해도 되게)
				ans = Math.max(ans, arr[tempNew]);
			}
		}
		
		System.out.println(ans);
	}

}
