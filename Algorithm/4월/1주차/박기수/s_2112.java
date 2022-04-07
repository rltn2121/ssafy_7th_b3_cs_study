package ps_java;


import java.io.*;
import java.util.*;

public class s_2112 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N,M,K,T,ans;
	static int arr[][];
	static int sequence[];
	public static void main(String[] args) throws Exception{
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T; tc++) {
			sb.append("#").append(tc).append(" ");
			input();
			perm(0,0);
			sb.append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void perm(int cnt, int changeCnt) {
		if(cnt == N) {
			// 최소 갯수 이상이면 탐색할 필요 없음
			if(changeCnt >= ans) return;
			if(func())
				ans = Math.min(ans, changeCnt);
			return;
		}
		
		// 중복 순열
		for(int i = 0; i<3; i++) {
			sequence[cnt] = i;
			perm(cnt+1, changeCnt + ((i==0)?0:1) );
		}
	}

	static boolean func() {
		for(int col = 0; col<M; col++) {
			int cnt = 1;
			int current = arr[0][col];
			if(sequence[0] != 0)
				current = sequence[0] - 1;
			for(int row = 1; row<N; row++) {
				int comp = arr[row][col];
				if(sequence[row] != 0)
					comp = sequence[row] - 1;
				
				if(comp == current)
					cnt++;
				else {
					cnt = 1;
					current = comp;
				}
				if(cnt==K)
					break;
				
			}
			if(cnt<K)
				return false;
		}
		return true;
	}

	static void input() throws Exception {
		ans = Integer.MAX_VALUE;
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		sequence = new int[N];
		arr = new int[N][M];
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
}	
