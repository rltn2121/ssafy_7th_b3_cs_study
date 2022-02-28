package algo.study.day0208;

import java.util.Scanner;

public class bj2447 {
	static int n;
	static char[][] star;

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		star = new char[n][n];
		print(n, 0, 0);
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < n; j++) {
//				System.out.print(star[i][j]);
//			}
//			System.out.println();
//		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sb.append(star[i][j]);
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}

	public static void print(int num, int x, int y) {
		if (num == 3) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (i == 1 && j == 1)
						continue;
					star[i + x][j + y] = '*';
				}
			}
		} else {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (i == 1 && j == 1)
						continue;
					//System.out.println("print" + (num / 3) + " " + (x + i * num / 3) + " " + (y + j * num / 3));
					print(num / 3, x + i * num / 3, y + j * num / 3);
				}
			}
		}
	}

}
