package ps_java;


import java.io.*;
import java.util.*;

public class b_17135 {
	static int N,M,D;
	static int origin_arr[][], arr[][];
	static boolean archers[];
	static int enemy_cnt, ans;
	public static void main(String[] args) throws Exception{
		input();
		comb(0,0);
		System.out.println(ans);
	}
	
	static void comb(int idx, int cnt) {
		if(cnt == 3) {
			copyArray();
			ans = Math.max(ans, hunt());
			return;
		}
		
		for(int i = idx; i<M; i++) {
			archers[i] = true;
			comb(i+1, cnt+1);
			archers[i] = false;
		}
	}
	
	
	static int hunt() {
		int cnt = 0;
		Queue<Node> q = new ArrayDeque<>();
		// 적이 아래로 한 칸 이동
		for(int castle_end = N-1; castle_end>=0; castle_end--) {
			int archer_x = castle_end+1;
			
			// 모든 궁수 탐색
			for(int archer_idx = 0; archer_idx<M; archer_idx++) {
				if(!archers[archer_idx]) continue;
				int archer_y = archer_idx;
				
				int min_dist = Integer.MAX_VALUE;
				int target_x = Integer.MAX_VALUE;
				int target_y = Integer.MAX_VALUE;
				
				int dist = -1;
				// 모든 적 탐색
				for(int i = 0; i<=castle_end; i++) {
					for(int j = 0; j<M; j++) {
						if(arr[i][j] == 0) continue;
						dist = getDist(archer_x, archer_y, i, j);
						if(dist > D) continue;
						
						// 가장 가까운 적 공격
						if(dist < min_dist) {
							min_dist = dist;
							target_x = i;
							target_y = j;
						}
						else if(dist == min_dist) {
							if(j < target_y) {
								target_x = i;
								target_y = j;
							}
						}
					}
				}
				// 공격할 적 선택
				if(target_x<Integer.MAX_VALUE && target_y < Integer.MAX_VALUE)
					q.add(new Node(target_x, target_y));
			}
			
			// 적 제거
			while(!q.isEmpty()) {
				Node now = q.poll();
				if(arr[now.x][now.y] == 1) {
					arr[now.x][now.y] = 0;
					cnt++;
				}
			}
		}
		return cnt;
	}
	static void copyArray(){
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++)
				arr[i][j] = origin_arr[i][j];
		}
	}
	
	static int getDist(int x, int y, int a, int b) {
		return Math.abs(x-a) + Math.abs(y-b);
	}
	
	static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		archers = new boolean[M];
		arr = new int[N][M];
		origin_arr = new int[N][M];
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<M; j++) 
				origin_arr[i][j] = Integer.parseInt(st.nextToken());
		}
		br.close();
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
