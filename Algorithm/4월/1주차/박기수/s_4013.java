package ps_java;


import java.io.*;
import java.util.*;

public class s_4013 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static Queue<Node> q = new ArrayDeque<>();
	static int T, K, ans, arr[][] = new int[5][8];
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
	
	private static void func() throws Exception {
		for(int i = 0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			
			int rotate[] = new int[5];
			rotate[idx] = dir;
				
			// 왼쪽: arr[idx-1][2]
			int now = idx;
			while(now-1 >= 1) {
				if(arr[now-1][2] == arr[now][6]) break;
				rotate[now-1] = (rotate[now] == 1 ? -1 : 1);
				now--;
			}
			
			// 오른쪽: arr[idx+1][6]
			now = idx;
			while(now+1 <= 4) {
				if(arr[now][2] == arr[now+1][6]) break;
				rotate[now+1] = (rotate[now] == 1 ? -1 : 1);
				now++;
			}
			
			for(int j = 1; j<=4; j++)
				rotate(j, rotate[j]);
				
		}
		ans = arr[1][0] + arr[2][0]*2 + arr[3][0]*4 + arr[4][0]*8;
	}

	static void rotate(int idx, int dir) {
		if(dir == 1) {
			int temp = arr[idx][7];
			for(int i = 7; i>0; i--) {
				arr[idx][i] = arr[idx][i-1];
			}
			arr[idx][0] = temp;
		}
		else if(dir == -1) {
			int temp = arr[idx][0];
			for(int i = 0; i<7; i++) {
				arr[idx][i] = arr[idx][i+1];
			}
			arr[idx][7] = temp;
		}
		
	}
	
	static void input() throws Exception {
		K = Integer.parseInt(br.readLine());
		for(int i =1; i<=4; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<8; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
	}
	
	static class Node{
		int idx;
		int dir;
		public Node(int idx, int dir) {
			this.idx = idx;
			this.dir = dir;
		}
	}
}	
