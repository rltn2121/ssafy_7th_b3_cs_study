package 삼성A형기출문제;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class G4캐슬디펜스_17135 {
	static int N, M, D;
	static int[] col;
	static int[] tgt = new int[3];
	static int[][] map;
	
	static int[] dy = {0, -1, 0};
	static int[] dx = {-1, 0, 1};
	
	static int ans;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		col = new int[M];
		for(int i = 0; i < M; i++) col[i] = i;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		comb(0,0);
		System.out.println(ans);
	}
	
	
	static void comb(int tgtIdx, int srcIdx) {
		if(tgtIdx==3) {
			int[][] copyMap = new int[N][M];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					copyMap[i][j] = map[i][j];
				}
			}
			game(copyMap);
			return;
		}
		if(srcIdx==M) return;
		tgt[tgtIdx] = col[srcIdx];
		comb(tgtIdx +1 , srcIdx+1);
		comb(tgtIdx , srcIdx+1);
	}
	
	
	static void game(int[][] copyMap) {
		int count = 0;
		// 궁수 위치는 N에서 한턴씩 위로 올라가는 식으로 진행
		int row = N;
		// 최대 N턴 까지 진행
		//System.out.println("================================");
		for(int i = 0; i < N; i++) {
			ArrayList<int[]> list = new ArrayList<int[]>();
			for(int j = 0; j < 3; j++) {
				
				// 궁수 세명 각각의 col 위치
				int col = tgt[j];
				
				// BFS 방식으로 탐색
				
				bfs(row, col, copyMap, list);
				
			}
			// 적 동시에 제거
			for(int ii = 0; ii < list.size(); ii++) {
				int temp[] = list.get(ii);
				if(copyMap[temp[0]][temp[1]] == 1) {
					copyMap[temp[0]][temp[1]] = 0;
					count++;
					//System.out.println(temp[0] + "  " + temp[1]);
				};
				
			}
			// 적이 한칸 내려오는 걸 궁수가 한칸 위로 올라가는 것으로 대체
			row--;
		}
		
		ans = Math.max(ans, count);
	}
	
	static void bfs(int yy, int xx, int[][] copyMap, ArrayList<int[]> list) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {yy, xx, 0}); // y좌표, x좌표, distance
		boolean[][] isVisited = new boolean[N+1][M];
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			int y = temp[0];
			int x = temp[1];
			int dis = temp[2];
			isVisited[y][x] = true;
			// dis가 D보다 크면 리턴
			if(dis >= D) return;
			for(int d = 0; d< 3; d++) {
				int ny = y +dy[d];
				int nx = x +dx[d];
				if(ny < 0 || nx <0 || ny >= N || nx >=M) continue;
				if(isVisited[ny][nx]) continue;
				// 죽일 적 찾았으면 리턴, 어레이리스트에 추가
				if(ny < yy && copyMap[ny][nx] == 1) {
					list.add(new int[] {ny, nx});
					return;
				}
				q.offer(new int[] {ny, nx, dis+1});
			}
			
			
		}
	} 
	
	static int dis(int y1, int x1, int y2, int x2) {
		return Math.abs(y1-y2) + Math.abs(x1-x2);
	}
	
}
