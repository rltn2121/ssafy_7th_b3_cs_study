package algo.study.day0209;

import java.awt.print.Printable;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 전체 구현 로직
// 1. 주사위 이동
// 2. 이동 후 주사위 전개도와 방향 잡아주기
// 3. 이동 후 bfs로 점수 더해주기
// 반복


public class bj23288 {
	
	static int N, M, K; // N * M 지도, K 번 움직이기
	static int[][] map;
	
	static int[][] Dice = { {0, 2, 0}, {4, 1, 3}, {0, 5, 0}, {0, 6, 0}}; // 처음 주사위 전개도
	static int move = 2; // 처음 동쪽 방향 움직임
	static int row, col = 0; // 처음 주사위 위치
	
	static Queue<int[]> q = new LinkedList<int[]>(); // dfs 할때 큐에 넣어서 탐색하기
	static int[] dx = {-1, 1, 0, 0}; // dfs 용도
	static int[] dy = {0, 0, -1, 1}; // dfs 용도
	
	static int ans = 0; // 반환할 결과 값
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		score();
		System.out.println(ans);
		
		
	}
	public static void score() {
		
		int cnt = 0;
		int mapScore = 0;
		
		// k 번 만큼 반복하기
		while(cnt < K) {
			cnt++;
			moveDice(move); // 방향대로 움직이기
			mapScore = map[row][col];
			// 방향 반시계로 바꿔주기
			if (Dice[3][1] > mapScore) {
				move++;
				if(move == 5) move = 1;
			}
			// 방향 시계로 바꿔주기
			else if(Dice[3][1] < mapScore) {
				move--;
				if(move == 0) move = 4;
			}
			// 막다른길이면 방향 반대로
			if(move==1 && row == 0) move = 3;
			if(move==2 && col == M-1) move = 4;
			if(move==3 && row == N-1) move = 1;
			if(move==4 && col == 0) move = 2;
			
			// 점수 더해주기
			BFS(row, col);
		}
			
		
	}
	public static void BFS(int row, int col) {
		int cnt = 1;
		boolean[][] isVisted = new boolean[N][M]; // 방문한지 안한지 체크해주기 Bfs 콜할때마다 false로 초기화
		int scr = map[row][col]; // 현재 점수 저장
		//방문한적 있으면 더해주지 않기
		isVisted[row][col] = true;
		
		// 현재 인덱스에서 상하좌우 탐색
		// 큐에 집어넣어서 다 탐색하기
		q.offer(new int[] {row, col});
		while(!q.isEmpty()) {
			// 탐색하고 있는 위치 저장
			int temp_row = q.peek()[0];
			int temp_col = q.peek()[1];
			q.poll();
			// 상하좌우 무빙해주기
			for(int i = 0; i < 4; i++) {
				int new_row = temp_row + dx[i];
				int new_col = temp_col + dy[i];
				// 인덱스 에러 방지
				if(new_row < 0 || new_col < 0 || new_row >= N || new_col >= M) continue;
				// 이동한 좌표의 값이 현재 좌표의 값과 같고 방문한 적이 없으면
				if(map[new_row][new_col] == scr && !isVisted[new_row][new_col]) {
					isVisted[new_row][new_col] = true; // 방문했다고 바꿔주기
					cnt++; // 갯수 증가
					q.offer(new int[] {new_row, new_col}); // 다시 여기서부터 상하좌우 탐색하기
				}
			}
		}
		
		
		
		ans += cnt*scr;
	}
	
	// 주사위 전개도 
	// 2 : 동쪽  3 : 남쪽  4: 서쪽  1 : 북쪽
	public static void moveDice(int move) {
		int temp = 0;
		switch (move) {
		case 2:
			temp = Dice[3][1];
			Dice[3][1] = Dice[1][2];
			Dice[1][2] = Dice[1][1];
			Dice[1][1] = Dice[1][0];
			Dice[1][0] = temp;
			col += 1;
			break;
		case 4:
			temp = Dice[1][2];
			Dice[1][2] = Dice[3][1];
			Dice[3][1] = Dice[1][0];
			Dice[1][0] = Dice[1][1];
			Dice[1][1] = temp;
			col -= 1;
			break;
		case 3:
			temp = Dice[0][1];
			Dice[0][1] = Dice[3][1];
			Dice[3][1] = Dice[2][1];
			Dice[2][1] = Dice[1][1];
			Dice[1][1] = temp;
			row += 1;
			break;
		case 1:
			temp = Dice[1][1];
			Dice[1][1] = Dice[2][1];
			Dice[2][1] = Dice[3][1];
			Dice[3][1] = Dice[0][1];
			Dice[0][1] = temp;
			row -= 1;
			break;
		default:
			break;
		}
	}
	public static void printDice() {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 3; j++) {
				System.out.print(Dice[i][j]+" ");
			}
			System.out.println();
		}
	}
	
}
