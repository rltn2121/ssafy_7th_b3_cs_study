package ps_java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class b_2960 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static String input;
	static int N;
	static int M;
	static int cnt = 0;
	static boolean notPrime[] = new boolean[1001];	
	public static void main(String[] args) throws Exception{
		input = br.readLine();
		st = new StringTokenizer(input, " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		
		for(int i = 2; i<=N; i++) {
			if(notPrime[i] == true) continue;
			if(++cnt == M) {
				bw.write(i + "\n");
				break;
			}
			
			for(int j = i*2; j<=N; j+=i) {
				if(notPrime[j] == true) continue;
				notPrime[j] = true;
				if(++cnt==M) {
					bw.write(j + "\n");
					break;
				}
			}
		}
		
		
		br.close();
		bw.close();
	}
}
