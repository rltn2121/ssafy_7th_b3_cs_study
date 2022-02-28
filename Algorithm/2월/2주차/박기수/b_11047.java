package ps_java;

import java.io.*;
import java.util.*;

public class b_11047 {
	static int N, K;
	static int[] arr;
	public static void main(String[] args) throws Exception{
		input();
		
		int remain = K;
		int cnt = 0;
		for(int i = N-1; i>=0; i--) {
			// 사용 불가
			if(arr[i] > remain) continue;
			
			// 사용 가능
			// 잔돈 계산
			cnt += remain/arr[i];
			remain %= arr[i];
			
			// 딱 맞으면
			if(remain == 0) {
				System.out.println(cnt);
				break;
			} 
			
		}
	}
	
	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		for(int i = 0; i<N; i++)
			arr[i] = Integer.parseInt(br.readLine());
		br.close();
	}
}
