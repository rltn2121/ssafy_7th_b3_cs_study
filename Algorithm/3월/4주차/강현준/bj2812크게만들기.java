package study0304;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj2812크게만들기 {

	static int N, K;
	static char[] arr;
	static int[] remove;

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = br.readLine().toCharArray();
		remove = new int[K];
		
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((e1, e2)->e2[0]-e1[0]);
		
		
		
		for(int i = 0; i < N; i++) {
			
			if(pq.size() < K) {
				pq.add(new int[] {arr[i]-'0' , i});
			}
			else if(pq.size() == K && pq.peek()[0] > arr[i] - '0') {
				pq.poll();
				pq.add(new int[] {arr[i]-'0' , i});
			}
			else {
				break;
			}
		}
		while(!pq.isEmpty()) {
			int temp[] = pq.poll();
			arr[temp[1]] = '*';
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			if(arr[i] != '*') sb.append(arr[i]);
		}
		System.out.println(sb.toString());
	}

}
