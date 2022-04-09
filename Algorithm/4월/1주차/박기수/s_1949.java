package ps_java;


import java.io.*;
import java.util.*;

public class s_1949 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N,K,T,ans,MAX;
	static int dx[] = {-1,0,1,0};
	static int dy[] = {0,1,0,-1};
	static int arr[][];
	static List<Node> list;
	static boolean visited[][];
	public static void main(String[] args) throws Exception{
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T; tc++) {
			sb.append("#").append(tc).append(" ");
			input();
			func();
			sb.append(ans+1).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void func() {
		for(Node n : list) {
			visited = new boolean[N][N];
			visited[n.x][n.y] = true;
			dfs(n.x,n.y,-1,false,0);
		}
	}

	private static void dfs(int x, int y, int dir, boolean used, int cnt) {
		ans = Math.max(ans, cnt);
		for(int i = 0; i<4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(nx<0||nx>=N||ny<0||ny>=N||visited[nx][ny]) continue;
			
			// 왔던 방향으로 되돌아갈 수 없음
			if((i+2)%4 == dir) continue;
			
			// 땅 안 파고 가기
			if(arr[nx][ny] < arr[x][y]) {
				visited[nx][ny] = true;
				dfs(nx,ny,i,used, cnt+1);
				visited[nx][ny] = false;
			}
			
			// 땅 파야하는데 안 팠으면
			else if(!used && arr[nx][ny] >= arr[x][y] ){
				// 땅 파서 이동 가능하면
				for(int h = 1; h<=K; h++) {
					if(arr[nx][ny] - h < arr[x][y]) {
						arr[nx][ny] -= h;
						visited[nx][ny] = true;
						dfs(nx,ny,i,true, cnt+1);
						visited[nx][ny] = false;
						arr[nx][ny] += h;
						break;
					}
				}
//				if(arr[nx][ny] - K < arr[x][y]) {
//					int temp = arr[nx][ny];
//					arr[nx][ny] = arr[x][y] - 1;
//					dfs(nx,ny,i,true,cnt+1);
//					arr[nx][ny] = temp;
//				}
			}
		}
	}
	static void input() throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		ans = 0;
		MAX = 0;
		arr = new int[N][N];
		list = new ArrayList<>();
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				MAX = Math.max(MAX, arr[i][j]);
			}
		}
		
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				if(arr[i][j] == MAX)
					list.add(new Node(i,j));
			}
		}
	}
	static class Node{
		int x;
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}	
