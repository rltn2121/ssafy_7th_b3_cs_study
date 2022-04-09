package ps_java;


import java.io.*;
import java.util.*;

public class s_1953 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static boolean visited[][];
	static int TC,N,M,x,y,T,ans;
	static int arr[][];
	static int dx[] = {-1,0,1,0};
	static int dy[] = {0,1,0,-1};
	public static void main(String[] args) throws Exception{
		TC = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=TC; tc++) {
			sb.append("#").append(tc).append(" ");
			input();
			ans = func();
			sb.append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static int func() {
		int t = 0;
		int cnt = 0;
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(x,y));
		visited[x][y] = true;
		cnt++;
		while(!q.isEmpty()) {
			int size = q.size();
			while(size-- > 0) {
				Node now = q.poll();
				if(t == T-1)
					return cnt;
				
				boolean move_now[] = getMove(now);
				
//				int mask_now = getMove(now);
				for(int i = 0; i<4; i++) {
					int nx = now.x+dx[i];
					int ny = now.y+dy[i];
					if(nx<0||nx>=N||ny<0||ny>=M||visited[nx][ny]||arr[nx][ny] == 0) continue;
					
					
					// 현재 칸의 파이프와 다음 칸의 파이프가 이어지지 않았다면 continue;
					boolean move_next[] = getMove(new Node(nx,ny));
                    if(!(move_now[i]&&move_next[(i+2)%4])) continue;
                    
//					int mask_next = getMove(new Node(nx,ny));
//					if(! ( ( (mask_now & (1<<i)) != 0) && ( (mask_next & (1<<(i+2)%4)) != 0))) continue;
                    
					visited[nx][ny] = true;
					cnt++;
					q.add(new Node(nx,ny));
				}
			}
			t++;
		}
		return cnt;
	}
    private static boolean[] getMove(Node now) {
        boolean move[] = new boolean[4];
        switch(arr[now.x][now.y]) {
        case 1:
            move[0] = true;
            move[1] = true;
            move[2] = true;
            move[3] = true;
            break;
        case 2:
            move[0] = true;
            move[2] = true;
            break;
        case 3:
            move[1] = true;
            move[3] = true;
            break;
        case 4:
            move[0] = true;
            move[1] = true;
            break;
        case 5:
            move[1] = true;
            move[2] = true;
            break;
        case 6:
            move[2] = true;
            move[3] = true;
            break;
        case 7:
            move[0] = true;
            move[3] = true;
            break;
        }
        return move;
    }
//	private static int getMove(Node now) {
//		int n = arr[now.x][now.y];
//		if(n==1) return 15;
//		else if(n==2) return 5;
//		else if(n==3) return 10;
//		else if(n==4) return 3;
//		else if(n==5) return 6;
//		else if(n==6) return 12;
//		else if(n==7) return 9;
//			
//		return -1;
//	}

	static void input() throws Exception {
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		visited = new boolean[N][M];
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<M; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
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
