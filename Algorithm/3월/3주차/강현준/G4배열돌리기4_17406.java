package 삼성A형기출문제;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G4배열돌리기4_17406 {
	static int N, M, K;
	static int[][] map;
	static int[][] copyMap;
	static int[][] rotate;
	static int[] idx;
	static int ans = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N+1][M+1];
		copyMap = new int[N+1][M+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}

		}
		rotate = new int[K][3];
		idx = new int[K];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			rotate[i][0] = r;
			rotate[i][1] = c;
			rotate[i][2] = s;
			idx[i] = i;
		}
		
		while(true) {
			//System.out.println(Arrays.toString(idx));
			
			
			for(int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					copyMap[i][j] = map[i][j];
				}
			}
			
			for(int i = 0; i < K; i++) {
				
				int[] temp = rotate[idx[i]];
				int r = temp[0];
				int c = temp[1];
				int s = temp[2];
				func(s, r, c);
				
			}
			int min = Integer.MAX_VALUE;
			for(int i = 1; i <= N; i++) {
				int sum = 0;
				for(int j = 1; j <= M; j++) {
					sum += copyMap[i][j];
				}
				min = Math.min(sum, min);
			}
//			for(int i = 1; i <= N; i++) {
//				for(int j = 1; j <= M; j++) {
//					System.out.print(copyMap[i][j] + " ");
//				}
//				System.out.println();
//			}
			ans = Math.min(min, ans);
			if(!np()) break;
		}
		System.out.println(ans);

	}
	
	static void func(int s, int r, int c) {
		if(s == 0) {
			//System.out.println("zz");
			return;
		}
		int temp = copyMap[r-s][c-s];
		//왼쪽
		for(int i = r-s; i < r+s; i++) {
			copyMap[i][c-s] = copyMap[i+1][c-s];
		}
		//아래쪽
		for(int i = c-s; i < c+s; i++) {
			copyMap[r+s][i] = copyMap[r+s][i+1];
		}
		//오른쪽
		for(int i = r+s; i > r-s; i--) {
			copyMap[i][c+s] = copyMap[i-1][c+s];
		}
		//위쪽
		for(int i = c+s; i > c-s; i--) {
			copyMap[r-s][i] = copyMap[r-s][i-1];
		}
		copyMap[r-s][c-s+1] = temp;
		func(s-1, r, c);
	}
	
	static boolean np() {
		
		int i = idx.length-1;
		
		while(i>0 && idx[i-1] >= idx[i]) i--;
		
		if(i==0) return false;
		
		int j = idx.length-1;
		
		while(idx[i-1] >= idx[j]) j--;
		
		swap(i-1, j);
		
		int k = idx.length -1;
		
		while(i < k) swap(i++, k--);
		
		return true;
	}
	
	static void swap(int i, int j) {
		int temp = idx[i];
		idx[i] = idx[j];
		idx[j] = temp;
	}
}
