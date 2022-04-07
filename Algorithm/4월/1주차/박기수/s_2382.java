package ps_java;


import java.io.*;
import java.util.*;

public class s_2382 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N,M,K,T,ans,total;
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static PriorityQueue<Node>[][] arr;
	public static void main(String[] args) throws Exception{
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T; tc++) {
			sb.append("#").append(tc).append(" ");
			input();
			func();
			sb.append(total).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void func() {
		Queue<Node> q = new ArrayDeque<>();
		while(M-- > 0) {
			while(!q.isEmpty()) {
				Node now = q.poll();
				arr[now.x][now.y].add(now);
			}
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<N; j++) {
					if(arr[i][j].size() == 0) continue;
					
					Node now = arr[i][j].poll();
					while(!arr[i][j].isEmpty()) {
						Node temp = arr[i][j].poll();
						now.num+=temp.num;
					}
					int nx = i+dx[now.dir];
					int ny = j+dy[now.dir];
					// 미생물 /2
					// 방향 반대
					if(nx<1||nx>=N-1||ny<1||ny>=N-1) {
						total -= now.num;
						now.num/=2;
						total += now.num;
						
//						0 -> 1
//						1 -> 0
//						2 -> 3
//						3 -> 2
						if(now.dir%2==0)
							now.dir++;
						else
							now.dir--;;
						if(now.num == 0) continue;
					}
					now.x = nx;
					now.y = ny;
					q.add(now);
				}
			}
		}
	}

	static void input() throws Exception {
		total = 0;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new PriorityQueue[N][N];
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++)
				arr[i][j] = new PriorityQueue<>();
		}
		for(int i = 0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken())-1;
			arr[x][y].add(new Node(x,y,num,dir));
			total+=num;
		}
	}
	static class Node implements Comparable<Node>{
		int x;
		int y;
		int num;
		int dir;
		public Node(int x, int y, int num, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.num = num;
			this.dir = dir;
		}
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return o.num - num;
		}
	}
}	
