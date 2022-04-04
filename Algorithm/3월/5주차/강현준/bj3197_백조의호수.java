package study0305;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj3197_백조의호수 {

	static int R, C;
	static char[][] map;

	static Position p1, p2;
	static boolean oneCheck = false;
	static boolean end = false;

	static Position[][] set;

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	static Queue<Position> ice = new ArrayDeque<Position>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][];
		set = new Position[R][C];

		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'L') {
					set[i][j] = new Position(i, j);
					if (!oneCheck) {
						p1 = set[i][j];
						p1.num = "1";
						oneCheck = true;
					} else {
						p2 = set[i][j];
						p2.num = "2";
					}
				} else
					set[i][j] = new Position(i, j);
				if(map[i][j] == 'X') {
					ice.add(set[i][j]);
				}
			}
		}
		bfsInit(p1);
		bfsInit(p2);
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(map[i][j] == '.' && set[i][j].num.equals("0")) {
					bfsInit(set[i][j]);
				}
			}
		}
		
		printSet();
		int rst = 0;
		while(true) {
			rst++;
			melt();
			printSet();
			if(end) break;
		}
		System.out.println(rst);
	}
	
	static Position find(Position p1) {
		Position p2 = set[p1.y][p1.x];
		if(p1.y == p2.y && p1.x == p2.x) {
			return p1;
		}
		
		return set[p1.y][p1.x] = find(set[p1.y][p1.x]);
	}
	static boolean union(Position p1, Position p2) {
		
		Position p11 = find(p1);
		Position p22 = find(p2);
		
		if(p11==p22) return false;
		
		p22 = p11;
		
		return true;
	}
	
	static void melt() {
		
		ArrayList<Position> list = new ArrayList<Position>();
		int size = ice.size();
		for(int i = 0; i < size; i++) {
			Position p = ice.poll();
			list.clear();
			int y = p.y;
			int x = p.x;
			for(int d = 0; d < 4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];
				if(ny < 0 || nx < 0 || ny >= R || nx >= C) continue;
				if(map[ny][nx] == 'X') continue;
				list.add(set[ny][nx]);
			}
			
			if(list.size() != 0) {
				boolean flag1 = false;
				boolean flag2 = false;
				
				for(Position temp : list) {
					
					if(find(temp) == p1) {
						flag1 = true;
						p = p1;
					}
					if(find(temp) == p2) {
						flag2 = true;
						p = p2;
					}
					
				}
				if(flag1 && flag2) {
					end = true;
				}
				else if(flag1) {
					for(Position temp : list) {
						union(p1, temp);
					}
				}
				else if(flag2) {
					for(Position temp : list) {
						union(p2, temp);
					}
				}
				else if(!flag1 && !flag2){
					p = list.get(0);
					for(Position temp : list) {
						union(p, temp);
					}
				}
			
			} else {
				ice.add(p);
			}
			
		}
		
	}
	static void bfsInit(Position L) {
		if(L.num.equals("0")) L.num = "3";
		Queue<Position> q = new ArrayDeque<>();
		q.add(L);
		boolean isVistied[][] = new boolean[R][C];
		isVistied[L.y][L.x] = true;
		while (!q.isEmpty()) {
			Position p = q.poll();
			int cy = p.y;
			int cx = p.x;
			for (int d = 0; d < 4; d++) {
				int ny = cy + dy[d];
				int nx = cx + dx[d];
				if(ny < 0 || nx < 0 || ny >= R || nx >= C) continue;
				if(isVistied[ny][nx] || map[ny][nx] == 'X') continue;
				isVistied[ny][nx] = true;
				set[ny][nx] = L;
				q.add(new Position(ny, nx));
			}
		}
	}
	static void printSet() {
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				System.out.print(set[i][j] + " ");
			}
			System.out.println();
		}
	}
	static class Position {
		int y, x;
		String num = "0";
		public Position(int y, int x) {
			this.y = y;
			this.x = x;
		}
		@Override
		public String toString() {
			return "["+y+","+x+"]";
		}
		
	}

}
