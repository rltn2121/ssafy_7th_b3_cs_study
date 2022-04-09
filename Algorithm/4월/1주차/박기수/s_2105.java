package ps_java;


import java.io.*;
import java.util.*;

public class s_2105 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N,T,ans;
	static int arr[][];
	static int dx[] = {1,1,-1,-1};
	static int dy[] = {1,-1,-1,1};
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
		// 시작점 정하기
		for(int i = 0; i<=N-3; i++) {
			for(int j = 1; j<=N-2; j++) {
				// 길이 정하기
				for(int a = 1; a <=N-2; a++) {
					for(int b = 1; b <= N-2; b++) {
						// 탐색
						int temp = find(i,j,a,b);
						ans = Math.max(ans, temp);
					}
				}
				
			}
		}
	}

	static int find(int x, int y, int a, int b) {
		boolean visited[] = new boolean[101];
		
		// 시작점 처리
		int cnt = 1;
		visited[arr[x][y]] = true;
		
		int start_x = x;
		int start_y = y;
		
		// 대각선 4방향 탐색
		for(int dir = 0; dir<4; dir++) {
			int idx = 0;
			
			// dir 짝수 -> 좌상우하 대각선
			// dir 홀수 -> 우상좌하 대각선
			int len = (dir%2==0?a:b);
			while(idx < len) {
				x+=dx[dir];
				y+=dy[dir];
				if(x<0||x>=N||y<0||y>=N)
					return -1;
				
				if(x==start_x && y==start_y)
					return cnt;
				
				if(visited[arr[x][y]]) return -1;
				
				visited[arr[x][y]] = true;
				cnt++;
				idx++;
			}
		}
		return -1;
	}

	static void input() throws Exception {
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		ans = -1;
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) 
				arr[i][j] = Integer.parseInt(st.nextToken());
			
		}
	}
}	
