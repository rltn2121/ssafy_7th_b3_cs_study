package ps_java;


import java.io.*;
import java.util.*;

public class b_10819 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N, input[], arr[], ans;
	static boolean visited[];
	public static void main(String[] args) throws Exception{
		N = Integer.parseInt(br.readLine());
		input = new int[N];
		arr = new int[N];
		visited = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) 
			input[i] = Integer.parseInt(st.nextToken());
		
		func(0);
		bw.write(ans+"\n");
		bw.flush();
		br.close();
		bw.close();
	}
	private static void func(int cnt) {
		if(cnt == N) {
			int sum = 0;
			for(int i = 0; i<N-1; i++) 
				sum+=Math.abs(arr[i]-arr[i+1]);
			ans = Integer.max(ans,  sum);
			return;
		}
		for(int i = 0; i<N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			arr[cnt] = input[i];
			func(cnt+1);
			visited[i] = false;
		}
	}
}	
