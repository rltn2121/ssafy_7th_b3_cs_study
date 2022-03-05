package ps_java;


import java.io.*;
import java.util.*;

public class b_14891 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static Queue<Node> q = new ArrayDeque<>();
	static int N, arr[][] = new int[5][8];
	public static void main(String[] args) throws Exception{
		input();
		N = Integer.parseInt(br.readLine());
		for(int i = 0; i<N; i++) {
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
		
		System.out.println(arr[1][0] + arr[2][0]*2 + arr[3][0]*4 + arr[4][0]*8);
		br.close();
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
		for(int i =1; i<=4; i++) {
			String str = br.readLine();
			for(int j = 0; j<8; j++)
				arr[i][j] = str.charAt(j)-'0';
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
