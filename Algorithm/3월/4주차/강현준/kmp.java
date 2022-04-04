package study0304;

import java.util.Scanner;

public class kmp {

	static int cnt;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		char[] src = sc.next().toCharArray();
		char[] pattern = sc.next().toCharArray();

		KMP(src, pattern);
		System.out.println(cnt);
	}

	static void KMP(char[] src, char[] pattern) {

		int[] pi = getPi(pattern);
		int j = 0;
		for (int i = 0; i < src.length; i++) {

			while (j > 0 && src[i] != pattern[j])
				j = pi[j - 1];

			if (src[i] == pattern[j]) {

				if (j == pattern.length-1) {
					cnt++;
					sb.append(i-j+1).append(" ");
					j = pi[j];
				} else {
					j++;
				}
			}

		}

	}

	static int[] getPi(char[] pattern) {

		int[] pi = new int[pattern.length];

		int j = 0;

		for (int i = 1; i < pi.length; i++) {

			while (j > 0 && pattern[i] != pattern[j])
				j = pi[j - 1];

			if (pattern[i] == pattern[j]) {
				j++;
				pi[i] = j;
			}
		}

		return pi;
	}

}
