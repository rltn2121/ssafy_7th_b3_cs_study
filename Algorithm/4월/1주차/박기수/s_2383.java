package ps_java;


import java.io.*;
import java.util.*;

public class s_2383 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static Node steps[] = new Node[2];
	static int arr[][] = new int[11][11];
	static int N,T,ans;
	static List<Node> list;
	
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
		PriorityQueue<Integer> pq[] = new PriorityQueue[2];
		pq[0] = new PriorityQueue<>();
		pq[1] = new PriorityQueue<>();

		// 모든 경우의 수
		int MAX = (1 << list.size());
		int mask = 0;
		
		// 모든 경우 탐색
		while(mask < MAX) {
			for(int person = 0; person<list.size(); person++) {
				int step_idx;
				
				// person번 사람 0번 계단
				if((mask & (1<<person) ) == 0) 
					step_idx = 0;
				
				// person번 사람 1번 계단
				else 
					step_idx = 1;
				
				// 계단 입구에 도착하는 시간 계산
				Node p = list.get(person);
				Node s = steps[step_idx];
				int x = Math.abs(p.x-s.x);
				int y = Math.abs(p.y-s.y);
				pq[step_idx].add(x+y);
			}
			
			// 계단 배정 완료
			Queue<Integer> q1 = new ArrayDeque<>();
			Queue<Integer> q2 = new ArrayDeque<>();
			int t = 1;
			int finished = 0;
			while(true) {
				// 1번 계단 다 내려옴
				while(!q1.isEmpty() && q1.peek() == t) {
					q1.poll();
					finished++;
				}
				// 2번 계단 다 내려옴
				while(!q2.isEmpty() && q2.peek() == t) {
					q2.poll();
					finished++;
				}
				
				// 1번 계단 내려가기 시작
				while(!pq[0].isEmpty() && pq[0].peek() <= t && q1.size() < 3) {
					Integer now = pq[0].poll();
					// 계단에 올라가면 1분 뒤에 내려감
					if(now == t)
						q1.add(now+1 + arr[steps[0].x][steps[0].y]);
					
					// 계단 3명 다 사용 중일 때 계단에 올라갔으므로, 1분 안 기다리고 바로 내려감 
					else
						q1.add(t + arr[steps[0].x][steps[0].y]);
						
				}
				// 2번 계단 내려가기 시작
				while(!pq[1].isEmpty() && pq[1].peek() <= t && q2.size() < 3) {
					Integer now = pq[1].poll();
					// 계단에 올라가면 1분 뒤에 내려감
					if(now == t)
						q2.add(now + 1 + arr[steps[1].x][steps[1].y]);
					
					// 계단 3명 다 사용 중일 때 계단에 올라갔으므로, 1분 안 기다리고 바로 내려감
					else
						q2.add(t + arr[steps[1].x][steps[1].y]);
				}
				
				// 모두 내려옴
				if(finished == list.size())
					break;
				t++;
			}
			ans = Math.min(ans, t);
			mask++;
		}
	}

	static void input() throws Exception {
		ans = Integer.MAX_VALUE;
		list = new ArrayList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		int step_idx = 0;
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());	
			for(int j = 0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				
				if(arr[i][j] == 1) 
					list.add(new Node(i,j));
				
				else if(arr[i][j] > 1) 
					steps[step_idx++] = new Node(i,j);
			}
		}
	}
	static class Node{
		int x;
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}	
