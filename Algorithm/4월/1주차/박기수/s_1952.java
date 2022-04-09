package ps_java;


import java.io.*;
import java.util.*;

public class s_1952 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int T,ans,total_days, total_months;
	static int day, month, three_month, year;
	static int arr[] = new int[13];
	static int cost[] = new int[13];
	public static void main(String[] args) throws Exception{
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T; tc++) {
			sb.append("#").append(tc).append(" ");
			input();
			func();
			sb.append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void func() {
		// 일일권 vs 한 달 이용권 비교
		for(int i = 1; i<=12; i++) 
			cost[i] = Math.min(arr[i]*day, month);
		
		// 세 달 이용권 언제 쓸 지 결정
		comp(1);
	}

	private static void comp(int month) {
		// 일 년치 다 봤으면 비교
		if(month > 12) {
			int temp = 0;
			for(int i : cost)
				temp+=i;
			ans = Math.min(temp, ans);
			return;
		}
		
		int c = 0;
		int n = 0;
		int nn = 0;
		
		// 현재 달 수영장 이용하면
		if(cost[month]>0) {
			
			c = cost[month];
			if(month+1<=12)	n = cost[month+1];
			if(month+2<=12)	nn = cost[month+2];
			
			// 1. 세 달 이용권 사용
			cost[month] = three_month;
			if(month+1<=12)	cost[month+1] = 0;
			if(month+2<=12)	cost[month+2] = 0;
			
			// 다음 달 비교
			comp(month+3);
			
			// 원상 복구
			cost[month] = c;
			if(month+1<=12)	cost[month+1] = n;
			if(month+2<=12)	cost[month+2] = nn;
		}
		
		// 2. 세 달 이용권 미사용
		comp(month+1);
	}

	static void input() throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		day = Integer.parseInt(st.nextToken());
		month = Integer.parseInt(st.nextToken());
		three_month = Integer.parseInt(st.nextToken());
		year = Integer.parseInt(st.nextToken());
		
		ans = year;
		total_days = 0;
		total_months = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i<=12; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if(arr[i] > 0) {
				total_days+=arr[i];
				total_months++;
			}
		}
	}
}	
