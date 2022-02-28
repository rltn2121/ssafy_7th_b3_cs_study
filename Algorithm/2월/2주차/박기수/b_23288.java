package ps_java;

import java.io.*;
import java.util.*;

public class b_23288 {
	static int N,M,K;
	static int cnt;
	static int score = 0;
	static boolean visited[][];
	static int arr[][];
	static int dp[][];
	static int dx[] = {-1, 0, 1, 0};	// 0: 북, 1: 동, 2: 남, 3: 서
	static int dy[] = {0, 1, 0, -1};
	static Queue<Point> q = new LinkedList<>();
	public static void main(String[] args) throws Exception{
		Dice d = new Dice();
		input();
		initDP();
	
		for(int k = 0; k<K; k++) 
			Roll(d);
		
		System.out.println(score);
	}

	
	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		dp = new int[N][M];
		visited = new boolean[N][M];
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j<M; j++) 
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		br.close();
	}
	
	static void initDP() {
		
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				cnt = 0;
				if(!visited[i][j]) {
					int val = getScore(i,j);
					while(!q.isEmpty()) {
						int x = q.peek().x;
						int y = q.peek().y;
						q.poll();
						
						dp[x][y] = val;
					}
				}
			}
		}
	}
	static class Point{
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int getScore(int x, int y) {
		q.add(new Point(x, y));
		cnt++;
		visited[x][y] = true;
		int val = arr[x][y];
		if(x-1>=0 && arr[x-1][y]==val && !visited[x-1][y])	getScore(x-1, y);
		if(y+1<M && arr[x][y+1]== val && !visited[x][y+1])	getScore(x, y+1);
		if(x+1<N && arr[x+1][y]==val && !visited[x+1][y])	getScore(x+1, y);
		if(y-1>=0 && arr[x][y-1]==val && !visited[x][y-1])	getScore(x, y-1);
		
		return dp[x][y] = val*cnt;
	}

	static void Roll(Dice d) {
		int nx = d.x + dx[d.direction];
		int ny = d.y + dy[d.direction];
		
		// 만약, 이동 방향에 칸이 없다면, 이동 방향을 반대로 한 다음 한 칸 굴러간다.
		if (nx<0||nx>=N||ny<0||ny>=M)	{
			d.direction = (d.direction + 2) % 4;
			nx = d.x + dx[d.direction];
			ny = d.y + dy[d.direction];
		}
		
		// 주사위 상태 갱신
		switch(d.direction) {
			case 0:	d.moveTop();	break;
			case 1: d.moveRight();	break;
			case 2: d.moveBottom();	break;
			case 3: d.moveLeft();	break;
		}
		
		// 주사위 좌표 갱신
		d.x = nx;
		d.y = ny;
		
		// 2. 주사위가 도착한 칸 (x,y)에 대한 점수를 획득한다.
		score+=dp[d.x][d.y];
		
		// 3. 주사위의 아랫면에 있는 정수 A와 주사위가 있는 칸 (x, y)에 잇는 정수 B를 비교해 이동 방향을 결정한다.
		int A = d.getBottom();
		int B = arr[d.x][d.y];
		
		if(A>B)			d.direction = (d.direction + 1) % 4;
		else if(A<B)	d.direction = (d.direction - 1 + 4) % 4;
	}
	
	static class Dice{
/*
		  0
		1 2 3
		  4
		  5
 */
		
		int arr[] = {2,4,1,3,5,6};
		int direction = 1;	// 0: 북, 1: 동, 2: 남, 3: 서
		int x = 0;
		int y = 0;
		
		public void moveTop() {
			int temp = arr[0];
			arr[0] = arr[2];
			arr[2] = arr[4];
			arr[4] = arr[5];
			arr[5] = temp;
		}
		public void moveLeft() {
			int temp = arr[2];
			arr[2] = arr[3];
			arr[3] = arr[5];
			arr[5] = arr[1];
			arr[1] = temp;
		}
		public void moveRight() {
			int temp = arr[1];
			arr[1] = arr[5];
			arr[5] = arr[3];
			arr[3] = arr[2];
			arr[2] = temp;
		}
		public void moveBottom() {
			int temp = arr[5];
			arr[5] = arr[4];
			arr[4] = arr[2];
			arr[2] = arr[0];
			arr[0] = temp;
		}
		public int getBottom() {
			return arr[5];
		}
	}
}	
