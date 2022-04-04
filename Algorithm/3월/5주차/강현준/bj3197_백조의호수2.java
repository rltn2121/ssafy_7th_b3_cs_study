package study0305;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj3197_백조의호수2 {

	static int R, C;
	static char[][] map;
	static Position[][] positionMap;

	static Position p1, p2;
	static boolean oneCheck = false;
	static boolean end = false;

	static int[][] set;

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	static Queue<Position> ice = new ArrayDeque<Position>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][];
		positionMap = new Position[R][C];
		set = new int[R][C];

		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				positionMap[i][j] = new Position(i, j);
				if (map[i][j] == 'L') {
					if (!oneCheck) {
						p1 = positionMap[i][j];
						p1.num = 1;
						set[i][j] = 1;
						oneCheck = true;
					} else {
						p2 = positionMap[i][j];
						p2.num = 2;
						set[i][j] = 2;
					}
				}
				
			}
		}
		bfsInit(p1);
		bfsInit(p2);
		int tempNum = 3;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == '.' && positionMap[i][j].parrent.num == 0) {
					positionMap[i][j].num = tempNum++;
					bfsInit(positionMap[i][j]);
				}
			}
		}
		//System.out.println("!!!!!!!!!!!!!!!!!!!!!");
//		for(int i = 0; i < R; i++) {
//			for(int j = 0; j < C; j++) {
//				if(find(positionMap[i][j]) == p1) System.out.println("1 좌표 " + i + " " + j);
//				if(find(positionMap[i][j]) == p2) System.out.println("2좌표 " + i + " " + j);
//			}
//		}
		//printSet();
		int rst = 0;
		while(rst < 10) {
			rst++;
			melt();
			//System.out.println(rst + " -------------- ");
			//printSet();
			if(end) break;
		}
		System.out.println(rst);
		//System.out.println("===========");
		//System.out.println(p1.parrent.num + " " + p2.parrent.num);
	}

	static Position find(Position p) {
		if (p == p.parrent)
			return p;
		return p.parrent = find(p.parrent);
	}

	static void union(Position p1, Position p2) {
		Position p11 = find(p1);
		Position p22 = find(p2);
		
		if(p11 != p22) {
			p22.parrent = p11;
		}
	}

	static void melt() {
		
		ArrayList<Position> list = new ArrayList<>();
		ArrayList<ArrayList<Position>> matrix = new ArrayList<ArrayList<Position>>();
		ArrayList<Position> melting = new ArrayList<>();
		int size = ice.size();
		int idx = 0;
		for(int i = 0; i < size; i++) {
			matrix.add(new ArrayList<Position>());
			
			Position p = ice.poll();
			list.clear();
			int y = p.y;
			int x = p.x;
			for(int d = 0; d < 4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];
				if(ny < 0 || nx < 0 || ny >= R || nx >= C) continue;
				if(map[ny][nx] == 'X') {
					ice.add(positionMap[ny][nx]);
					continue;
				}
				matrix.get(idx).add(positionMap[ny][nx]);
			}
			idx++;
			
			melting.add(p);
//			// 백조 있는지 확인하기
//			boolean flag1 = false;
//			boolean flag2 = false;
//			
//			for(Position temp : list) {
//				if(find(temp) == p1) flag1 = true;
//				if(find(temp) == p2) flag2 = true;
//			}
//			if(flag1 && flag2) end = true;
//			else if(flag1) {
//				p.parrent = p1;
//				for(Position temp : list) {
//					union(p1, temp);
//				}
//			}
//			else if(flag2) {
//				p.parrent = p2;
//				for(Position temp : list) {
//					union(p2, temp);
//				}
//			}
//			else {
//				p.parrent = list.get(0);
//				for(Position temp : list) {
//					union(list.get(0), temp);
//				}
//			}
		}
		ArrayList<Position> fin = new ArrayList<>();
		//System.out.println(melting.size() + "!!!" + matrix.size());
		for(int i = 0; i < melting.size(); i++) {
			
			Position tp = melting.get(i);
			map[tp.y][tp.x] = '.';
			
			ArrayList<Position> list2 = matrix.get(i);
			//System.out.println(i + "번째 매트릭스 " + list2.size());
			boolean flag1 = false;
			boolean flag2 = false;
			//System.out.println(list2.size() + " @@ " + (i+1));
			for(Position temp : list2) {
				if(find(temp) == p1) flag1 = true;
				if(find(temp) == p2) flag2 = true;
			}
			if(flag1 && flag2) end = true;
			if(end) return;
			else if(flag1) {
				//System.out.println("1발견" + "좌표 : " + tp.y + " " + tp.x);
				tp.parrent = p1;
				for(Position temp : list2) {
					union(p1, temp);
				}
				fin.add(tp);
			}
			else if(flag2) {
				//System.out.println("2발견" + "좌표 : " + tp.y + " " + tp.x);
				tp.parrent = p2;
				for(Position temp : list2) {
					union(p2, temp);
				}
			}
			else {
				//System.out.println("플랙그없다");
				tp.parrent = list2.get(0);
				for(Position temp : list2) {
					union(list2.get(0), temp);
				}
			}
			
		}
		
		for(Position XX : fin) {
			for(int d = 0; d <4; d++) {
				int ny = XX.y + dy[d];
				int nx = XX.x + dx[d];
				if(ny < 0 || nx < 0 || ny >= R || nx >= C) continue;
				if(map[ny][nx] == 'X') continue;
				if(find(positionMap[ny][nx]) == p2) {
					end = true;
					break;
				}
			}
			if(end) break;
		}
		
	}

	static void bfsInit(Position L) {

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
				if (ny < 0 || nx < 0 || ny >= R || nx >= C)
					continue;
				if (isVistied[ny][nx]) continue;
				if ( map[ny][nx] == 'X') {
					ice.add(positionMap[ny][nx]);
					continue;
				}
				isVistied[ny][nx] = true;
				positionMap[ny][nx].parrent = L;
				q.add(new Position(ny, nx));
			}
		}
	}

	static void printSet() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(positionMap[i][j].parrent.num + " ");
			}
			System.out.println();
		}
	}

	static class Position {
		int y, x;
		int num = 0;
		Position parrent = this;

		public Position(int y, int x) {
			this.y = y;
			this.x = x;
		}

		@Override
		public String toString() {
			return "[" + y + "," + x + "]";
		}

	}

}
