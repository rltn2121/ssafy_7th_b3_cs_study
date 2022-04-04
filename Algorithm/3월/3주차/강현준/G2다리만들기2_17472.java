package 삼성A형기출문제;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class G2다리만들기2_17472 {

	static int N, M;
	static int[][] map;
	static boolean[][] isVisited;
	
	static int parents[];

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static ArrayList<Map> mapList = new ArrayList<>();
	static ArrayList<ArrayList<Edge>> edgeList = new ArrayList<ArrayList<Edge>>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		isVisited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int idx = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!isVisited[i][j] && map[i][j] == 1) {
					mapList.add(new Map());
					dfs(i, j, idx);
					idx++;
					edgeList.add(new ArrayList<Edge>());
				}
			}
		}
//		for(int i = 0; i < mapList.size(); i++) {
//			ArrayList<int[]> temp = mapList.get(i).yxList;
//			for(int j = 0; j < temp.size(); j++) {
//				System.out.print(temp.get(j)[0] + " "+temp.get(j)[1] + " / ");
//			}
//			System.out.println();
//		}
		for (int i = 0; i < mapList.size(); i++) {
			for (int j = i + 1; j < mapList.size(); j++) {
				int dis = calcDistance(i, j);
				if (dis != Integer.MAX_VALUE) {

					edgeList.get(i).add(new Edge(j, dis));
					edgeList.get(j).add(new Edge(i, dis));
				}
			}
		}
		
//		for(int i = 0; i < edgeList.size(); i++) {
//			
//			for(int j = 0; j < edgeList.get(i).size(); j++) {
//				System.out.print(i + " " + edgeList.get(i).get(j).v + " "+ edgeList.get(i).get(j).w + " / ");
//			}
//			System.out.println();
//		}
		
		
		
//		int[] minEdge = new int[mapList.size()];
//		Arrays.fill(minEdge, Integer.MAX_VALUE);

		PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.w - e2.w);
		boolean[] isVisitedLand = new boolean[mapList.size()];
		isVisitedLand[0] = true;
		pq.addAll(edgeList.get(0));
		int sum = 0;
		int cnt = 0;
		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			if (isVisitedLand[e.v])
				continue;
			isVisitedLand[e.v] = true;
			cnt++;
			sum += e.w;
			//System.out.println(e.v + " " + e.w);
			if (cnt == mapList.size() - 1) {
				System.out.println(sum);
				return;
			}
			pq.addAll(edgeList.get(e.v));
		}
		System.out.println(-1);

	}

	static int calcDistance(int a, int b) {
		ArrayList<int[]> aList = mapList.get(a).yxList;
		ArrayList<int[]> bList = mapList.get(b).yxList;

		int dis = Integer.MAX_VALUE;

		for (int i = 0; i < aList.size(); i++) {
			int aY = aList.get(i)[0];
			int aX = aList.get(i)[1];
			ct: for (int j = 0; j < bList.size(); j++) {
				int bY = bList.get(j)[0];
				int bX = bList.get(j)[1];

				// 가로 세로 일직선으로 연결하기 위해서는 y,x 중 하나는 같아야한다
				if (aY != bY && aX != bX)
					continue;
				// y좌표가 같을때
				if (aY == bY) {
					for (int ii = Math.min(aX, bX) + 1; ii < Math.min(aX, bX) + Math.abs(aX - bX); ii++) {
						int ny = aY;
						int nx = ii;
						if(isVisited[ny][nx]) continue ct;
						for (int jj = 0; jj < aList.size(); jj++) {
							if (ny == aList.get(jj)[0] && nx == aList.get(jj)[1]) {
								continue ct;
							}
						}
						for (int jj = 0; jj < bList.size(); jj++) {
							if (ny == bList.get(jj)[0] && nx == bList.get(jj)[1]) {
								continue ct;
							}
						}
					}
				}
				// X좌표가 같을 때
				if (aX == bX) {
					for (int ii = Math.min(aY, bY) + 1; ii < Math.min(aY, bY) + Math.abs(aY - bY); ii++) {
						int ny = ii;
						int nx = aX;
						if(isVisited[ny][nx]) continue ct;
						for (int jj = 0; jj < aList.size(); jj++) {
							if (ny == aList.get(jj)[0] && nx == aList.get(jj)[1]) {
								continue ct;
							}
						}
						for (int jj = 0; jj < bList.size(); jj++) {
							if (ny == bList.get(jj)[0] && nx == bList.get(jj)[1]) {
								continue ct;
							}
						}
					}
				}

				int temp = Math.max(Math.abs(aY - bY), Math.abs(aX - bX));
				if (temp > 2)
					dis = Math.min(dis, temp-1);

			}
		}
		return dis;
	}

	static void dfs(int y, int x, int idx) {
		mapList.get(idx).yxList.add(new int[] { y, x });
		isVisited[y][x] = true;

		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			if (ny < 0 || nx < 0 || ny >= N || nx >= M)
				continue;
			if (!isVisited[ny][nx] && map[ny][nx] == 1) {
				dfs(ny, nx, idx);
			}
		}

	}

	static class Map {
		ArrayList<int[]> yxList = new ArrayList<int[]>();
	}

	static class Edge {
		int v, w;

		public Edge(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}

	}
}
