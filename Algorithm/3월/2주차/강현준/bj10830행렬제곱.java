package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj10830행렬제곱 {
	static int N;
	static long B;
	static int[][] matrix;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		B = Long.parseLong(st.nextToken());
		matrix = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[][] ans = func(B);
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(ans[i][j] + " ");
			}
			System.out.println();
		}
	}

	static int[][] func(long n) {
		
		if(n==1) {
			return matrix;
		}
		
		int[][] rst = func(n/2);
		
		rst = multiply(rst, rst);
		
		if(n % 2 == 1) {
			rst = multiply(rst, matrix);
		}
		
		return rst;
		
	}
	
	static int[][] multiply(int[][] mat1, int[][] mat2){
		int[][] rst = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				for(int k = 0; k <N; k++) {
					rst[i][j] += mat1[i][k] * mat2[k][j];
					rst[i][j] %= 1001;
				}
			}
		}
		
		return rst;
	}

}
