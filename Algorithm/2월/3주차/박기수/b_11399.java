package ps_java;


import java.io.*;
import java.util.*;

public class b_11399 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N,M,T,arr[], sum;
	public static void main(String[] args) throws Exception{
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) 
			arr[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(arr);
		
		int cnt = 1;
		for(int i = N-1; i>=0; i--)
			sum+=arr[i]*cnt++;
		System.out.println(sum);
		br.close();
		bw.close();
	}
}	
