package ps_java;


import java.io.*;
import java.util.*;

public class b_2156 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N,M,T,arr[], dp[], ans;
	public static void main(String[] args) throws Exception{
		N = Integer.parseInt(br.readLine());
		arr=new int[10001];
		dp=new int[10001];
		for(int i = 0; i<N; i++) 
			arr[i] = Integer.parseInt(br.readLine());
		dp[0] = arr[0];
		dp[1] = arr[0]+arr[1];
		dp[2] = Math.max(arr[0]+arr[1], Math.max(arr[0]+arr[2], arr[1]+arr[2]));
		ans = Math.max(dp[0], Math.max(dp[1], dp[2]));
		
		for(int i = 3; i<N; i++) {
			dp[i] =  Math.max(arr[i] +arr[i-1]+dp[i-3], Math.max(dp[i-1], arr[i] +dp[i-2]));
			ans = Math.max(ans, dp[i]);
		}
		
		System.out.println(ans);
		br.close();
		bw.close();
	}
}	
