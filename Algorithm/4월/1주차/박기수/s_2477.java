package ps_java;


import java.io.*;
import java.util.*;

public class s_2477 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	/*
	 * N: 접수 창구 개수
	 * M: 정비 창구 개수
	 * K: 고객 수
	 * A: 고객이 이용한 접수 창구번호
	 * B: 고객이 이용한 정비 창구번호
	 * */
	static int T,N,M,K,A,B,ans;
	static int reception_time[], repair_time[];
	static Queue<Integer> visit_time;
	
	public static void main(String[] args) throws Exception{
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T; tc++) {
			sb.append("#").append(tc).append(" ");
			input();
			func();
			sb.append((ans==0)?-1:ans).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void func() {
		Queue<Node> reception_q = new ArrayDeque<>();
		Queue<Node> repair_q = new ArrayDeque<>();
		
		Node reception_desk[] = new Node[N];
		Node repair_desk[] = new Node[M];
		
		int t = 0;
		int cnt = 0;
		int customer_num = 1;
		
		while(cnt<K) {
			// 1. 정비 종료 -> 창구 번호 확인
			for(int i = 0; i<M; i++) {
				Node desk = repair_desk[i];
				if(desk == null) continue;
				if(desk.end_time == t) {
					if(desk.reception_num+1 == A && desk.repair_num+1 == B)
						ans+=desk.num;
					repair_desk[i] = null;
					cnt++;
				}
			}
			
			// 2. 접수 종료 -> 정비 큐
			for(int i = 0; i<N; i++) {
				Node desk = reception_desk[i];
				if(desk == null) continue;
				if(desk.end_time == t) {
					repair_q.add(desk);
					reception_desk[i] = null;
				}
			}			
			
			// 3. 정비 큐 -> 정비 창구
			for(int i = 0; i<M; i++) {
				Node desk = repair_desk[i];
				if(desk != null) continue;
				if(repair_q.isEmpty()) continue;
				repair_desk[i] = repair_q.poll();
				repair_desk[i].repair_num = i;
				repair_desk[i].end_time = t+repair_time[i];
			}
			
			
			
			// 4. 도착 -> 접수 큐로 이동
			while(!visit_time.isEmpty() && visit_time.peek() <= t) {
				reception_q.add(new Node(customer_num++,-1,-1,-1));
				visit_time.poll();
			}
			
			// 5. 접수 큐 -> 접수 창구
			for(int i = 0; i<N; i++) {
				Node desk = reception_desk[i];
				if(desk != null) continue;
				if(reception_q.isEmpty()) continue;
				reception_desk[i] = reception_q.poll();
				reception_desk[i].reception_num = i;
				reception_desk[i].end_time = t+reception_time[i];
			}
			t++;
		}
	}

	static void input() throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		ans = 0;
		reception_time = new int[N];
		repair_time = new int[M];
		visit_time = new ArrayDeque<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++)
			reception_time[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<M; i++)
			repair_time[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<K; i++)
			visit_time.add(Integer.parseInt(st.nextToken()));
	}
	static class Node{
		int num;
		int reception_num;
		int repair_num;
		int end_time;
		public Node(int num, int reception_num, int repair_num, int end_time) {
			super();
			this.num = num;
			this.reception_num = reception_num;
			this.repair_num = repair_num;
			this.end_time = end_time;
		}
	}
}	

