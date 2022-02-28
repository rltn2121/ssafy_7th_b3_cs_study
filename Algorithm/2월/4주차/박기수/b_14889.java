package ps_java;


import java.io.*;
import java.util.*;

public class b_14889 {

	static int N,ans=Integer.MAX_VALUE, input[][];
	static boolean visited[];
	public static void main(String[] args) throws Exception{
		input();
		func(0,0);
		System.out.println(ans);
	}
	
	static void func(int idx, int cnt) {
		if(cnt==N/2) {
			int sum1 = 0;
			int sum2 = 0;
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<N; j++) {
					if(i==j) continue;
					if(visited[i] && visited[j])
						sum1+=input[i][j];
					else if(!visited[i] && !visited[j])
						sum2+=input[i][j];
				}
					
			}
			int diff = Math.abs(sum1-sum2);
			ans = Math.min(ans, diff);
			return;
		}
		
		for(int i = idx; i<N; i++) {
			visited[i] = true;
			func(i+1,cnt+1);
			visited[i] = false;
		}
	}
	
	static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		input = new int[N][N];
		visited = new boolean[N];
		
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) 
				input[i][j] = Integer.parseInt(st.nextToken());
			
		}
		br.close();
	}
}	
