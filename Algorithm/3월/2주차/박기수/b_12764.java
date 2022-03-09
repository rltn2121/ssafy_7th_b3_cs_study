package ps_java;


import java.io.*;
import java.util.*;

public class b_12764 {
	static int N,time[],cnt[],ans;
	static PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> a.start-b.start);
	public static void main(String[] args) throws Exception {
		input();
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			// 컴퓨터 낮은 번호부터 모두 탐색
			for(int i = 1; i<=N; i++) {
				// 현재 컴퓨터 사용 가능?
				if(now.start >= time[i]) {
					time[i] = now.end;
					cnt[i]++;
					ans = Math.max(ans, i);
					break;
				}
			}
		}
		System.out.println(ans);
		for(int i = 1; i<=ans; i++)
			System.out.print(cnt[i] + " ");
	}
	
	static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		time = new int[N+1];
		cnt = new int[N+1];
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			pq.add(new Node(a,b));
		}
	}
	
	static class Node{
		int start;
		int end;
		public Node(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
}	
