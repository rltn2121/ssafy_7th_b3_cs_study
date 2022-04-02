package ps_java;


import java.io.*;
import java.util.*;

public class b_3190 {
	static List<Node> move = new ArrayList<>();
	static int dx[] = {-1,0,1,0};
	static int dy[]	= {0,1,0,-1};
	static int arr[][];
	static int N,K,L;
	public static void main(String[] args) throws Exception{
		input();
		System.out.println(func());
	}
	
	static int func() {
		// 꼬리 저장할 queue
		Queue<Point> q = new ArrayDeque<>();
		q.add(new Point(0,0));
		arr[0][0] = 2;
		
		// 방향 전환 체크용 (move에 사용)
		int idx = 0;
		int t = 0;
		int dir = 1;
		Node turn = move.get(idx);
		
		// 머리 위치
		int head_x = q.peek().x;
		int head_y = q.peek().y;
		
		while(true) {
			t++;
			
			// 다음 칸 체크
			int nx = head_x+dx[dir];
			int ny = head_y+dy[dir];
			if(nx<0||nx>=N||ny<0||ny>=N||arr[nx][ny] == 2) break;
			
			// 다음 칸으로 이동
			q.add(new Point(nx,ny));
			head_x = nx;
			head_y = ny;
			
			// 사과 있으면
			if(arr[nx][ny] == 1) {
				
			}
			// 사과 없으면
			else {
				Point tail = q.poll();
				arr[tail.x][tail.y] = 0;
			}
			arr[nx][ny] = 2;
			
			// 방향 전환 체크
			if(t == turn.t) {
				if(turn.d == 'L')
					dir = (dir + 4 - 1) % 4;
				else
					dir = (dir + 1) % 4;
				if(++idx < move.size())
					turn = move.get(idx);
			}
		}
		return t;
	}

	static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for(int i = 0; i<K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			arr[x][y] = 1;
		}
		
		L = Integer.parseInt(br.readLine());
		for(int i = 0; i<L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			char d = st.nextToken().charAt(0);
			move.add(new Node(n,d));
		}
	}
	static class Point{
		int x;
		int y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	static class Node{
		int t;
		char d;
		public Node(int t, char d) {
			super();
			this.t = t;
			this.d = d;
		}
	}
}	
