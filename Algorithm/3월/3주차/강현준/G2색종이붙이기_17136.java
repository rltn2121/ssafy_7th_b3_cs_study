package 삼성A형기출문제;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G2색종이붙이기_17136 {
	static int map[][];
	static int squareSize[][];
	static int numOf1;
	static int min = Integer.MAX_VALUE;
	static int count[] = new int[6];
	static boolean[][] isVisited;
	static boolean isOk = false;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new int[20][20];
		squareSize = new int[11][11];
		isVisited = new boolean[10][10];
		for (int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				// if(map[i][j] == 1 && !isVisited[i][j]) search(i, j, 1);
				if (map[i][j] == 0)
					continue;
				numOf1++;

				searchSquareSize(i, j, 1);
			}
		}
//		for (int i = 0; i < 10; i++) {
//			for (int j = 0; j < 10; j++) {
//				System.out.print(squareSize[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println(numOf1);
		dfs(0, 0, 0);
		if (min == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}
//		for (int xx = 0; xx < 10; xx++) {
//			for (int jj = 0; jj < 10; jj++) {
//				System.out.print(isVisited[xx][jj] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println(Arrays.toString(count));
		System.out.println(min);
	}

	static void dfs(int y, int x, int numofPaper) {
		// 색종이 5개 넘으면 아웃
		int summ = 0;
		for (int i = 1; i <= 5; i++) {
			if (count[i] > 5) {
				//System.out.println("zz");
				return;
			}
			summ += count[i];
		}
		if (min <= summ) return;
		// 색종이 크기 5개
		for (int i = 1; i <= 5; i++) {
			// 사이즈가 적으면 break
			if (i > squareSize[y][x])
				break;
			isOk = true;
			
			if(!isDoing(y, x, i)) break;
			count[i]++;
			if(count[i] > 5) {
				count[i]--;
				continue;
			}
			visit(y, x, i, true);
			
			
			
			
			
			
			
			
			// 색종이 다 덮으면 1 리턴
			if (numofPaper + i * i == numOf1) {
				int sum = 0;
				for (int ii = 1; ii < 6; ii++) {
					sum += count[ii];
				}

				min = Math.min(min, sum);
				visit(y, x, i, false);
				count[i]--;
				return;
			}
			
			outer: for (int ii = 0; ii < 10; ii++) {
				for (int jj = 0; jj < 10; jj++) {
					if (squareSize[ii][jj] != 0 && !isVisited[ii][jj]) {
						//System.out.println("ii " + ii + " jj " + jj + " size " + i);
						dfs(ii, jj, numofPaper + i * i);
						break outer;
					}
				}
			}

			visit(y, x, i, false);
			count[i]--;
		}
		// 초기 작업
		if (numofPaper == 0 && y == 0 && x== 0 && !isOk) {
			if (numofPaper == numOf1) {
				int sum = 0;
				for (int ii = 1; ii < 6; ii++) {
					sum += count[ii];
				}
				
				min = Math.min(min, sum);
				return;
			}

			for (int ii = 0; ii < 10; ii++) {
				for (int jj = 0; jj < 10; jj++) {
					if (squareSize[ii][jj] != 0 && !isVisited[ii][jj]) {
						dfs(ii, jj, 0);
						return;
					}
				}
			}

		}

	}
	
	static void searchSquareSize(int y, int x, int size) {
		if(size==6) {
			squareSize[y][x] = size - 1;
			return;
		}
		for (int i = y; i < y + size; i++) {
			if (map[i][x - 1 + size] == 0) {
				squareSize[y][x] = size - 1;
				return;
			}
		}

		for (int i = x; i < x + size; i++) {
			if (map[y - 1 + size][i] == 0) {
				squareSize[y][x] = size - 1;
				return;
			}
		}

		searchSquareSize(y, x, size + 1);
	}

	static void visit(int y, int x, int size, boolean tf) {
		for (int ii = y; ii < y + size; ii++) {
			for (int jj = x; jj < x + size; jj++) {
				isVisited[ii][jj] = tf;
				//System.out.println(ii + " " + jj);
			}
		}
	}
	
	static boolean isDoing(int y, int x, int size) {
		for (int ii = y; ii < y + size; ii++) {
			for (int jj = x; jj < x + size; jj++) {
				if(isVisited[ii][jj]) return false;
			}
		}
		return true;
	}

	public static void search(int y, int x, int size) {

		if (size == 6) {
			return;
		}
		// y 축 검사
		for (int i = y; i < y + size; i++) {
			// 0이 나오면 이전까지 단계 제출
			if (map[i][x - 1 + size] == 0) {

				for (int ii = y; ii < y + size - 1; ii++) {
					for (int jj = x; jj < x + size - 1; jj++) {
						isVisited[ii][jj] = true;
					}
				}
				count[size - 1]++;
				return;
			}
		}
		// x 축 검사
		for (int i = x; i < x + size; i++) {
			if (map[y - 1 + size][i] == 0) {

				for (int ii = y; ii < y + size - 1; ii++) {
					for (int jj = x; jj < x + size - 1; jj++) {
						isVisited[ii][jj] = true;
					}
				}
				count[size - 1]++;
				return;
			}
		}

		// 위에꺼 다 통과했다는 말은 정사각형 색종이로 다 채울수 있다는 거니 재귀호출
		search(y, x, size + 1);

	}

}
