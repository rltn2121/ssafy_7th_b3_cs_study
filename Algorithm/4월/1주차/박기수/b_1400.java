package ps_java;


import java.io.*;
import java.util.*;

public class b_1400 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static List<Node> list;
	static boolean visited[][];
	static char arr[][];
	static int dx[] = {0,0,-1,1};
	static int dy[] = {1,-1,0,0};
	static int N,M,x,y;
	
	public static void main(String[] args) throws Exception{
		while(true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			if(N==0 && M == 0) break;
			input();
			int ans = bfs();
			sb.append(ans == -1 ? "impossible" : ans).append("\n");
			br.readLine();
		}
		System.out.println(sb.toString());
	}
	
	static int bfs() {
		int cnt = 0;
		Queue<Point> q = new ArrayDeque<>();
		q.add(new Point(x,y));
		visited[x][y] = true;
		while(!q.isEmpty()) {
			int size = q.size();
			while(size-- > 0) {
				
				Point now = q.poll();
				if(arr[now.x][now.y]=='B')
					return cnt;
				
				for(int i = 0; i<4; i++) {
					int nx = now.x+dx[i];
					int ny = now.y+dy[i];
					if(nx<0||nx>=N||ny<0||ny>=M||visited[nx][ny]||arr[nx][ny]=='.') continue;
					if(arr[nx][ny] == '#' || arr[nx][ny] == 'B') {
						q.add(new Point(nx,ny));
						visited[nx][ny] = true;
					}
					else if(arr[nx][ny]>='0' && arr[nx][ny] <= '9') {
						// 방향 확인 해야 함
						// 가만히 있을 수도 있음
						// 현재 신호등
						Node n = list.get(arr[nx][ny]-'0');
						
						// 뒷부분 자르기
						int t = cnt % (n.hor + n.ver)+1;
						
						// 신호등 초기 동서
						if(n.dir == 0) {
							// 동서방향 진행 가능
							if(t<=n.hor) {
								// 다음 방향 동서
								if(i<2) {
									visited[nx][ny] = true;
									q.add(new Point(nx,ny));
								}
								// 다음 방향 남북
								else {
									q.add(new Point(now.x,now.y));
								}
							}
							
							
							// 남북방향 진행 가능
							else {
								// 다음 방향 동서
								if(i<2) {
									q.add(new Point(now.x,now.y));
									
								}
								// 다음 방향 남북
								else {
									visited[nx][ny] = true;
									q.add(new Point(nx,ny));
								}
							}
						}
						
						// 신호등 초기 남북
						else {
							// 남북방향 진행 가능
							if(t<=n.ver) {
								// 다음 방향 동서
								if(i<2) {
									q.add(new Point(now.x,now.y));
								}
								// 다음 방향 남북
								else {
									visited[nx][ny] = true;
									q.add(new Point(nx,ny));
								}
							}
							// 동서방향 진행가능
							else {
								// 다음 방향 동서
								if(i<2) {
									visited[nx][ny] = true;
									q.add(new Point(nx,ny));
								}
								// 다음 방향 남북
								else {aa
								}
							}
						}
					}
				}
			}
			cnt++;
		}
		return -1;
	}

	static void input() throws Exception {
		list = new ArrayList<>();
		arr = new char[N][M];
		visited = new boolean[N][M];
		// 도로 정보
		for(int i = 0; i<N; i++) {
			arr[i] = br.readLine().toCharArray();
			for(int j = 0; j<M; j++) {
				if(arr[i][j]=='A') {
					x=i;
					y=j;
				}
				else if(arr[i][j] >= '0' && arr[i][j] <= '9') {
					list.add(new Node(i,j,0,0,0));
				}
			}
		}
		
		// 신호등 정보
		for(Node now : list) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			char dir = st.nextToken().charAt(0);
			int hor = Integer.parseInt(st.nextToken());
			int ver = Integer.parseInt(st.nextToken());
			
			if(dir == '|')
				now.setDir(1);
			else
				now.setDir(0);
			now.setHor(hor);
			now.setVer(ver);
		}
	}
	static class Point{
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static class Node{
		int x;
		int y;
		int dir;
		int hor;
		int ver;
		public Node(int x, int y, int dir, int hor, int ver) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.hor = hor;
			this.ver = ver;
		}
		public void setDir(int dir) {
			this.dir = dir;
		}
		public void setHor(int hor) {
			this.hor = hor;
		}
		public void setVer(int ver) {
			this.ver = ver;
		}
	}
}	
