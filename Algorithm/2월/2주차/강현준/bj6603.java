package algo.study.day0208;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj6603 {
	static int N;
	static int[] src, tgt;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			String s = br.readLine();
			if(s.charAt(0) == '0') break;
			sb = new StringBuilder();
			StringTokenizer st = new StringTokenizer(s);
			N = Integer.parseInt(st.nextToken());
			src = new int[N];
			tgt = new int[6];
			for(int i = 0; i < N; i++) {
				src[i] = Integer.parseInt(st.nextToken());
			}
			comb(0, 0);
			System.out.println(sb);
			System.out.println();
		}
		
	}
	public static void comb(int cnt, int start) {
		// 기저조건
		if(cnt == 6) {
			for(int i = 0; i < 6; i++)
				sb.append(tgt[i]).append(" ");
			sb.append("\n");
			return;
		}
		
		for(int i = start; i < N; i++) {
			tgt[cnt] = src[i];
			comb(cnt+1, i+1);
		}
	}

}
