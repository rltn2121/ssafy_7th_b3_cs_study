package ps_java;


import java.io.*;

public class b_11726 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, arr[];
	public static void main(String[] args) throws Exception{
		N = Integer.parseInt(br.readLine());
		arr = new int[1001];
		arr[1] = 1;
		arr[2] = 2;
		for(int i = 3; i<=N; i++)
			arr[i] = (arr[i-1]+arr[i-2])%10007;
		System.out.println(arr[N]);
		br.close();
	}
}	
