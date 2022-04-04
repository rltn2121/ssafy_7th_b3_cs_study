package 삼성A형기출문제;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G4야구_17281 {
	static int N;
	static int[][] map;
	// 1번 선수가 4번 타자
	static int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8 };
	static int max;
	static int score;
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][9];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (true) {
			score = 0;
			int[] tgt = new int[9];
			for(int i = 0; i < 9; i++) {
				if(i<=2) {
				tgt[i] = arr[i];
				}
				if(i==3) tgt[i] = 0;
				if(i>3) {
					tgt[i] = arr[i-1];
				}
			}
			
			game(tgt, 0, 0);
			max = Math.max(max, score);
			if(!np()) break;
		}
System.out.println(max);
	}

	static void game(int[] tgt, int n, int start) {
		// 9회 끝나면 끝
		if(n==N) {
			return;
		}
		
		int outCount = 0;
		int idx = 0;
		boolean[] base = new boolean[3];
		for(int i = start; i < 100; i++) {
			int x = map[n][tgt[i%9]];
			if(x  == 0) {
				outCount++;
				if(outCount == 3) {
					idx = (i+1) % 9;
					break;
				}
			}
			else if(x == 1) {
				for(int ii = 2; ii >= 0; ii--) {
					if(ii==2 && base[ii]) {
						score++;
						base[ii] = false;
					}
					else if(base[ii]) {
						base[ii+1] = true;
						base[ii] = false;
					}
				}
				base[0] = true;
			}
			else if(x==2) {
				for(int ii = 2; ii >= 0; ii--) {
					if(ii!=0 && base[ii]) {
						score++;
						base[ii] = false;
					}
					else if(base[ii]) {
						base[ii+2] = true;
						base[ii] = false;
					}
				}
				base[1] = true;
			}
			else if(x==3) {
				for(int ii = 0; ii < 3; ii++) {
					if(base[ii]) {
						base[ii] = false;
						score++;
					}
				}
				base[2] = true;
			}
			else if(x==4) {
				for(int ii = 0; ii < 3; ii++) {
					if(base[ii]) {
						base[ii] = false;
						score++;
					}
				}
				score++;
			}
			
			
			
			
		}
		game(tgt, n+1, idx);
		
	}
	
	static boolean np() {
		
		int i = 7;
		
		while(i > 0 && arr[i-1] >= arr[i]) i--;
		
		if(i==0) return false;
		
		int j = 7;
		
		while(arr[i-1] >= arr[j]) j--;
		
		swap(i-1, j);
		
		int k = 7;
		
		while(k > i) swap(k--, i++);
		
		return true;
	}
	
	static void swap(int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}
