package ps_java;


import java.io.*;
import java.util.*;

public class s_2117 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N,M,T,ans;
	static int arr[][];
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
		// N이 짝수일 경우, 크기를 N까지만 하면 탐색 안되는 공간 생김
		for(int size = 1; size<=N+1; size++) {
			
			// 중심점 기준 (0,0) ~ (N-1, N-1) 탐색
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<N; j++) {
					int cnt = search(i,j,size);
					int cost = (size*size) + (size-1)*(size-1);
					int gain = (cnt*M);
					if(gain >= cost)
						ans = Math.max(ans, cnt);
				}
			}
		}
	}

	static int search(int x, int y, int size) {
		int cnt = 0;
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				int dist = Math.abs(x-i) + Math.abs(y-j);
				if(dist>=size) continue;
				if(arr[i][j] == 1) cnt++;
			}
		}
		return cnt;
	}

	static void input() throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		ans = 0;
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
}	
