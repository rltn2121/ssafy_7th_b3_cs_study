package ps_java;

import java.io.*;
import java.util.*;

public class b_12015 {
	static int N;
	static int arr[];
	static int dp[];
	public static void main(String[] args) throws Exception{
        input();
        int ans = func()+1;
		System.out.println(ans);
	}
	
	static int func() {
		int idx = 0;
		dp[0] = arr[0];
		for(int i = 1; i<N; i++) {
			// 뒤에 붙을 수 있음
			if(arr[i] > dp[idx]) 
				dp[++idx] = arr[i];
			
			// 앞에 들어가야 함
			else {
				int pos = Arrays.binarySearch(dp, 0, idx, arr[i]);
				pos = (pos + 1) * -1;
				if(pos>=0&&pos<N)
					dp[pos] = arr[i];
			}
		}
		return idx;
	}

	static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		dp = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) 
			arr[i] = Integer.parseInt(st.nextToken());
	}
}	
