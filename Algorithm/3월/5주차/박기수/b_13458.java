package ps_java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b_13458 {
	static int N,B,C,arr[];
	static long ans;
	public static void main(String[] args) throws IOException {
		input();
		
		for(int i : arr) {
			ans++;
			i-=B;
			
			if(i<=0) continue;
			ans+=i/C;
			if(i%C != 0)
				ans++;
		}
		System.out.println(ans);
	}
	
	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		B = Integer.parseInt(st.nextToken()); 
		C = Integer.parseInt(st.nextToken());
	}
}
