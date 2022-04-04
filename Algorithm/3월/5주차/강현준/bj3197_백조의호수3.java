package study0305;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj3197_백조의호수3 {

	static int R, C;
	static char[][] map;
	static Position[][] positionMap;

	static Position p1, p2;
	static boolean oneCheck = false;
	static boolean end = false;
	static boolean isVistied[][];
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
		isVistied = new boolean[R][C];
		long beforeTime = System.currentTimeMillis();
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
		
		//System.out.println("bb");
		
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

		//printSet();
		int rst = 0;
		while(true) {
			rst++;
			melt();

			if(find(p1) == find(p2)) break;
		}
		System.out.println(rst);
		
		long afterTime = System.currentTimeMillis(); 
		long secDiffTime = (afterTime - beforeTime);
		//System.out.println("시간차이(m) : "+secDiffTime);

	}

	static Position find(Position p) {
		if (p == p.parrent)
			return p;
		return p.parrent = find(p.parrent);
	}

	static void union(Position p1, Position p2) {
		//System.out.println("??");
		Position p11 = find(p1);
		//System.out.println("zz");
		Position p22 = find(p2);
		
		if(p11 != p22) {
			p22.parrent = p11;
		}
	}

	static void melt() {
		
		ArrayList<ArrayList<Position>> matrix = new ArrayList<ArrayList<Position>>();
		boolean isVistied[][] = new boolean[R][C];
		int size = ice.size();
		for(int i = 0; i < size; i++) {
			matrix.add(new ArrayList<Position>());
			
			ArrayList<Position> aList = new ArrayList<Position>();
			
			Position p = ice.poll();
			int y = p.y;
			int x = p.x;
			for(int d = 0; d < 4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];
				if(ny < 0 || nx < 0 || ny >= R || nx >= C || isVistied[ny][nx]) continue;
				if(map[ny][nx] == 'X') {
					isVistied[ny][nx] = true;
					ice.add(positionMap[ny][nx]);
					continue;
				}
				aList.add(positionMap[ny][nx]);

			}

			map[p.y][p.x] = '.';
			p.parrent = find(aList.get(0));
			for(Position a : aList) {
				union(a, aList.get(0));
			}

		}

	}

	static void bfsInit(Position L) {

		Queue<Position> q = new ArrayDeque<>();
		q.add(L);
		
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
				isVistied[ny][nx] = true;
				if ( map[ny][nx] == 'X') {
					ice.add(positionMap[ny][nx]);
					continue;
				}
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
