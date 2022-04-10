package ps_java;


import java.io.*;
import java.util.*;

public class s_4008 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N,T,ans;
	static int max, min;
	static int nums[];
	static int op_cnt[];
	static int sequence[];
	static int TOTAL;
	public static void main(String[] args) throws Exception{
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T; tc++) {
			sb.append("#").append(tc).append(" ");
			input();
			perm(0);
			sb.append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void perm(int idx) {
		if(idx==N-1) {
			int temp = nums[0];
			for(int i = 1; i<N; i++) {
				int op = sequence[i-1];
				if(op==0)	temp += nums[i];
				else if(op==1) temp-=nums[i];
				else if(op==2) temp*=nums[i];
				else if(op==3) temp/=nums[i];
			}
			max = Math.max(max, temp);
			min = Math.min(min, temp);
			ans = Math.max(ans, max-min);
			return;
		}
		
		// 연산자 사용하기
		for(int i = 0; i<4; i++) {
			if(op_cnt[i] == 0) continue;
			
			op_cnt[i]--;
			sequence[idx] = i;
			perm(idx+1);
			op_cnt[i]++;
		}
	}

	static void input() throws Exception {
		N = Integer.parseInt(br.readLine());
		op_cnt = new int[4];
		sequence = new int[N-1];
		nums = new int[N];
		ans = 0;
		max = -99999999;
		min = 99999999;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<4; i++) {
			op_cnt[i] = Integer.parseInt(st.nextToken());
			TOTAL+=op_cnt[i];
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
	}
}	

