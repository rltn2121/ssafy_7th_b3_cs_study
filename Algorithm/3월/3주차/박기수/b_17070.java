package ps_java;


import java.io.*;
import java.util.*;

public class b_17070 {
	static Queue<Node> q = new ArrayDeque<>();
	static int N,cnt, arr[][];
	public static void main(String[] args) throws Exception{
		input();
		
		q.add(new Node(0, 1, 0));
		while(!q.isEmpty()) {
			Node now = q.poll();
			
			// 끝에 도착했으면 추가
			if(now.x == N-1 && now.y == N-1) {
				cnt++;
				continue;
			}
			
			// 1. 가로
			if(now.dir == 0) {
				checkHorizontal(now);
				checkDiagonal(now);
			}
			// 2. 세로
			else if(now.dir == 1) {
				checkDiagonal(now);
				checkVertical(now);
			}
			// 3. 대각선
			else if(now.dir == 2) {
				checkHorizontal(now);
				checkDiagonal(now);
				checkVertical(now);
			}
		}
		System.out.println(cnt);
	}
	static void checkHorizontal(Node now) {
		if(now.y + 1 < N && arr[now.x][now.y+1] == 0) 
			q.add(new Node(now.x, now.y+1, 0));
	}
	
	static void checkVertical(Node now) {
		if(now.x + 1 < N && arr[now.x+1][now.y] == 0) 
			q.add(new Node(now.x+1, now.y, 1));
	}
	static void checkDiagonal(Node now) {
		if(now.y + 1 < N && now.x + 1 < N && arr[now.x][now.y+1] == 0 && arr[now.x+1][now.y] == 0 && arr[now.x+1][now.y+1] == 0)
			q.add(new Node(now.x+1, now.y+1, 2));
	}
	
	static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		br.close();
	}
	
	static class Node{
		int x;
		int y;
		int dir;
		public Node(int x, int y, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
}	
