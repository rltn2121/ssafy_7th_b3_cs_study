package study0304;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj2812크게만들기2 {

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
		
		
		char[] rst = new char[N];
		
		int rstIdx = 0;
		int removeNum = 0;
		boolean pass = false;
		for(int i = 0; i < N; i++) {
			boolean flag = true;
			while(!pass && rstIdx > 0 && arr[i] > rst[rstIdx-1] && removeNum < K) {
				removeNum++;
				rstIdx--;
				flag = false;
			}
			if(flag) rst[rstIdx++] = arr[i];
			else {
				rst[rstIdx++] = arr[i];
			}

			if(removeNum+(N-i-1) == K && i != N-1 && arr[i+1] < arr[i]) break;
			
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < rstIdx; i++) {
			sb.append(rst[i]);
		}
		System.out.println(sb.toString());
	}

}
