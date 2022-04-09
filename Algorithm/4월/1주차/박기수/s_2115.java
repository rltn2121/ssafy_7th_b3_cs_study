package ps_java;


import java.io.*;
import java.util.*;

public class s_2115 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N,M,T,C,ans;
	static int arr[][];
	static int dp[][];
	static Node position[] = new Node[2];
	public static void main(String[] args) throws Exception{
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T; tc++) {
			sb.append("#").append(tc).append(" ");
			input();
			initDP();
			perm(0,0,0);
			sb.append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	// 선택한 벌통 ( (i, j) ~ (i, j+M) )에서 얻을 수 있는 최댓값 계산
	static void initDP() {
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<=N-M; j++) 
				subset(i,j);
		}
	}
	
	// 부분집합을 이용하여 모든 경우 다 계산
	static void subset(int x, int y) {
		int MAX = (1<<M);
		for(int mask = 1; mask<MAX; mask++) {
			int temp = 0;
			int cost = 0;
			
			// 범위 내에서 벌통 고르기
			for(int i = 0; i<M; i++) {
				if((mask & (1<<i)) == 0) continue;
				temp += (arr[x][y+i]);
				cost += (arr[x][y+i]*arr[x][y+i]);
				
			}
			
			// 최대 양보다 많이 채취하면 continue
			if(temp > C) continue;
			
			// 최댓값 갱신
			dp[x][y] = Math.max(dp[x][y], cost);
		}
	}

	static void perm(int row, int col, int cnt) {
		if(cnt==2) {
			int temp = 0;
			for(Node n : position) 
				temp += dp[n.x][n.y];
			
			ans = Math.max(ans, temp);
			return;
		}
		
		int j = 0;
		for(int i = row; i<N; i++) {
			for(j = col; j<N; j++) {
				if(j+M-1 >= N) continue;
				position[cnt] = new Node(i,j);
				perm(i, j+M, cnt+1);
			}
			if(j == N)
				col = 0;
		}
	}



	static void input() throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		ans = 0;
		dp = new int[N][N];
		arr = new int[N][N];
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
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
