package ps_java;


import java.io.*;
import java.util.*;

public class b_16236 {
	static Point shark;
	static int N, ans, cnt;
	static int dx[] = {-1,0,0,1};
	static int dy[] = {0,-1,1,0};
	static int map[][];
	static int toLevelUp = 2;
	static int level = 2;
	static boolean visited[][];
	static Queue<Point> q = new ArrayDeque<>();
	public static void main(String[] args) throws Exception{
		input();
		q.add(new Point(shark.x, shark.y));
		
		while(!q.isEmpty()) {
			PriorityQueue<Point> candidate = new PriorityQueue<>((a,b) -> (a.x==b.x ? a.y-b.y : a.x-b.x));
			int size = q.size();
			while(size-- > 0) {
				
				Point now = q.poll();
				
				if(visited[now.x][now.y]) continue;
				visited[now.x][now.y] = true;
				
				// 먹이를 먹을 수 있으면 후보에 넣기
				if(map[now.x][now.y]>0&&map[now.x][now.y] < level) {
					candidate.add(new Point(now.x,now.y));
					continue;
				}
				
				// 탐색
				for(int i = 0; i<4; i++){
					int nx = now.x+dx[i];
					int ny = now.y+dy[i];
					
					if(nx<0||nx>=N||ny<0||ny>=N||visited[nx][ny]||map[nx][ny]>level) continue;
					q.add(new Point(nx,ny));
				}
			}
			
			// 가장 우선순위 높은 먹이 먹기
			if(!candidate.isEmpty()) {
				Point now = candidate.poll();
				if(--toLevelUp == 0) 
					toLevelUp = ++level;
				
				map[now.x][now.y] = 0;
				
				q.clear();
				visited = new boolean[N][N];
				q.add(new Point(now.x, now.y));
				ans+=cnt;
				cnt = -1;
			}
			cnt++;
		}
		System.out.println(ans);
	}

	static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		visited = new boolean[N][N];
		
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					shark = new Point(i,j);
					map[i][j] = 0;
				}
			}
		}
		br.close();
	}
	
	static class Point{
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}	
