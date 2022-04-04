package study0304;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj15961 {
	
	static int N, d, k, c;
	static int[] arr;
	static int[] isVisited;
	static int max, rst;
	static boolean flag = false;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		arr = new int[N];
		isVisited = new int[d+1];
		boolean[] temp = new boolean[d+1];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		max = 1;
		isVisited[arr[0]]++;
		twoPointer(0, 0);
		
		System.out.println(rst);
		
		
	}
	
	static void twoPointer(int start, int end) {
		rst = Math.max(rst, max);
		if(end - start == k-1) {
			if(isVisited[c] == 0) {
				int temp = max + 1;
				rst = Math.max(rst, temp);
			}
		}
		
		if(start == N) return;
		
		
		if(isVisited[arr[(end+1)%N]]==0) max++;
		isVisited[arr[(end+1)%N]]++;
		if(end - start == k-1) {
			isVisited[arr[start]]--;
			if(isVisited[arr[start]] == 0) max--;
			twoPointer(start+1, end+1);
			return;
		}
		twoPointer(start, end+1);
	}

}
