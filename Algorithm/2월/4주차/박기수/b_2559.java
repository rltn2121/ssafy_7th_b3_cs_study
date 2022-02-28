package ps_java;


import java.io.*;
import java.util.*;

public class b_2559 {
	static int N,K,arr[], sum[], ans = Integer.MIN_VALUE;
	public static void main(String[] args) throws Exception{
		input();
		for(int i = K; i<=N; i++)
			ans = Math.max(ans, sum[i]-sum[i-K]);
		System.out.println(ans);
	}
	
	static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1];
		sum = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i<=N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sum[i]+=(arr[i]+sum[i-1]);
		}
		br.close();
	}
}	

