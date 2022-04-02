package ps_java;


import java.io.*;
import java.util.*;

public class b_17144 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int dx[] = {-1,0,1,0};
	static int dy[] = {0,1,0,-1};
	static int N,M,T,arr[][];
	static int cleaner1, cleaner2;
	public static void main(String[] args) throws Exception{
		input();
		while(T-- > 0) {
			spread();
			rotateRev();
			rotate();
		}
		System.out.println(getAnswer());
		br.close();
	}
	
	static int getAnswer() {
		int ret = 0;
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				if(arr[i][j] == -1) continue;
				ret+=arr[i][j];
			}
		}
		return ret;
	}
	static void rotateRev() {
		// 좌
		for(int i = cleaner1-2; i>=0; i--) 
			arr[i+1][0] = arr[i][0];
		
		// 상
		for(int j = 1; j<M; j++) 
			arr[0][j-1] = arr[0][j];
		
		// 우
		for(int i = 1; i<=cleaner1; i++) 
			arr[i-1][M-1] = arr[i][M-1];
		
		// 하
		for(int j = M-2; j>=1; j--) 
			arr[cleaner1][j+1] = arr[cleaner1][j];
		
		arr[cleaner1][1] = 0;
	}
	static void rotate() {
		// 좌
		for(int i = cleaner2+2; i<N; i++) 
			arr[i-1][0] = arr[i][0];
		
		// 하
		for(int j = 1; j<M; j++) 
			arr[N-1][j-1] = arr[N-1][j];
		
		// 우
		for(int i = N-2; i>=cleaner2; i--) 
			arr[i+1][M-1] = arr[i][M-1];
		
		// 상
		for(int j = M-2; j>=1; j--) 
			arr[cleaner2][j+1] = arr[cleaner2][j];
		
		arr[cleaner2][1] = 0;
	}
	static void spread() {
		// 일괄 갱신
		Queue<Node> q = new ArrayDeque<>();
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				if(arr[i][j] <=0) continue;
				for(int d = 0; d<4; d++) {
					int nx = i+dx[d];
					int ny = j+dy[d];
					if(nx<0||nx>=N||ny<0||ny>=M||arr[nx][ny] == -1) continue;
					
					// 더할 값 추가
					q.add(new Node(nx,ny,arr[i][j]/5));
					
					// 뺄 값 추가
					q.add(new Node(i,j,-arr[i][j]/5));
				}
			}
		}
		
		// 일괄 갱신
		while(!q.isEmpty()) {
			Node now = q.poll();
			arr[now.x][now.y] += now.num;
		}
	}
	
	static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == -1) {
					if (cleaner1 == 0)	cleaner1 = i;
					else 				cleaner2 = i;
				}
			}
		}
	}
	static class Node{
		int x;
		int y;
		int num;
		public Node(int x, int y, int num) {
			this.x = x;
			this.y = y;
			this.num = num;
		}
	}
}	
