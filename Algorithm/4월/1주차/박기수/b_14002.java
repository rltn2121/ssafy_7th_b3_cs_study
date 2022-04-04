package ps_java;


import java.io.*;
import java.util.*;

public class b_14002 {
	static Stack<Integer> s = new Stack<>();
	static StringTokenizer st;
	static int N,arr[], dp[];
	static int ans;
	public static void main(String[] args) throws Exception{
		input();
		lis();
		find();
		System.out.println(ans+1);
		while(!s.isEmpty())
			System.out.print(s.pop() + " ");
	}
	
	static void lis() {
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<i; j++) {
				if(arr[j] < arr[i] && dp[j]+1 > dp[i])
					dp[i] = dp[j]+1;
			}
			ans = Math.max(ans, dp[i]);
		}
	}
	
	static void find() {
		int idx = N-1;
		int target = ans;
		while(idx>=0) {
			if(dp[idx]==target) {
				s.add(arr[idx]);
				target--;
			}
			idx--;
		}
	}

	static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		dp = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
	}
}	
