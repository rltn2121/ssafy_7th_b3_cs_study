package ps_java;
import java.io.*;
import java.util.*;
public class b_16954 {
	static Queue<Node> q = new ArrayDeque<>();
	static char arr[][] = new char[8][8];
	static int dx[] = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
	static int dy[] = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
	static int first_line=10;
	public static void main(String[] args) throws Exception{
		input();
		System.out.println(bfs());
	}
	
	// 계속 제자리에 있어야 하는 경우가 있는데
	// visited를 써버리면 제자리에 있지 못함
	/*
	 * ...
	 * #.#
	 * .##
	 * .##
	 */
	static int bfs() {
		q.add(new Node(7,0));
		while(!q.isEmpty()) {
			int size = q.size();
			while(size-- > 0) {
				Node now = q.poll();
				// 가장 위의 벽을 지나치면 더 이상 벽 없음. 자유롭게 이동 가능
				if(now.x<=first_line) return 1;
				
				for(int i = 0; i<9; i++) {
					int nx = now.x+dx[i];
					int ny = now.y+dy[i];
					if(nx<0||nx>=8||ny<0||ny>=8||arr[nx][ny]=='#') continue;
					
					// 이동 후에 벽이 바로 위에 있을 경우, 벽이 내려오면 더 이상 이동 불가
					if(nx==0||arr[nx-1][ny] !='#')
						q.add(new Node(nx,ny));
				}
			}
			moveWall();
		}
		return 0;
	}

	static void moveWall() {
		for(int i = 6; i>=0; i--) {
			for(int j = 0; j<8; j++) 
				arr[i+1][j] = arr[i][j];
		}
		for(int j = 0; j<8; j++)
			arr[0][j] = '.';
		first_line++;
	}

	static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 0; i<8; i++) {
			arr[i] = br.readLine().toCharArray();
			for(int j = 0; j<8; j++) {
				if(first_line==10&&arr[i][j]=='#')
					first_line = i;
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
